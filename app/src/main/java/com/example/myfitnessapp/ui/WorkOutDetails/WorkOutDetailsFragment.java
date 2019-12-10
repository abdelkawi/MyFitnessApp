package com.example.myfitnessapp.ui.WorkOutDetails;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.data.local.SharedPreferneceUtils;
import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.ui.widget.AppWidget;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.myfitnessapp.ui.Constants.SHARED_PREF;
import static com.example.myfitnessapp.ui.Constants.WORKOUT_SHARED_PREF;

public class WorkOutDetailsFragment extends Fragment implements Player.EventListener, View.OnClickListener {
    static String name = "";
    static int currentIndex = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static List<WorkOut> currentList = new ArrayList<>();


    TextView mNextStepTV;

    TextView mPrevStepTV;


    public static void setCurrentList(List<WorkOut> currentList) {
        WorkOutDetailsFragment.currentList = currentList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        name = getArguments().getString("name");
    }

    public static String getName() {
        return name;
    }

    WorkoutDetailsViewModel workoutDetailsViewModel;
    TextView workOutTitle, workOutAffectedMuscle, workoutFunctionTV;
    private SimpleExoPlayer mExoplayer;
    CollapsingToolbarLayout appBarLayout;
    PlayerView mPlayerView;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;
    ImageView addToFavIV;
    private int resumeWindow;
    private long resumePosition;
    private WorkOut mWorkOut;
    SharedPreferneceUtils sharedPreferneceUtils;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_details, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mExoplayer == null && mWorkOut != null) {
            initializePlayer(mWorkOut.getVideo());
        }
    }


    void updateWidget() {
        Intent intent = new Intent(getContext(), AppWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int ids[] = AppWidgetManager.getInstance(getActivity().getApplication()).
                getAppWidgetIds(new ComponentName(getActivity().getApplication()
                        , AppWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        getActivity().sendBroadcast(intent);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mPlayerView.getLayoutParams();
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            params.width = params.MATCH_PARENT;
            params.height = params.MATCH_PARENT;
            mPlayerView.setLayoutParams(params);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        workOutTitle = view.findViewById(R.id.tv_workout_title);
        sharedPreferneceUtils = new SharedPreferneceUtils(getActivity());
        workOutAffectedMuscle = view.findViewById(R.id.tv_workout_affected_muscle);
        workoutFunctionTV = view.findViewById(R.id.tv_workout_detail);
        mPlayerView = view.findViewById(R.id.ep_video_view);
        addToFavIV = view.findViewById(R.id.add_to_fav);
        mNextStepTV = view.findViewById(R.id.tv_next_step);
        mPrevStepTV = view.findViewById(R.id.tv_prev_step);
        mPrevStepTV.setOnClickListener(this);
        mNextStepTV.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        clearResumePosition();
        setRetainInstance(true);
        workoutDetailsViewModel = new WorkoutDetailsViewModel();
        loadData();


    }

    void loadData() {
        workoutDetailsViewModel.getWorkOut(name).observe(this, new Observer<WorkOut>() {
            @Override
            public void onChanged(WorkOut workOut) {
                editor.putString(WORKOUT_SHARED_PREF, workOut.getName());
                editor.commit();
                mWorkOut = workOut;
                workOutTitle.setText(workOut.getName());
                workOutAffectedMuscle.setText(workOut.getAffectedMuscle());
                workoutFunctionTV.setText(workOut.getDescription());
                if (workOut.isIsFav()) {
                    addToFavIV.setImageResource(R.drawable.ic_add_to_favorites_on);
                } else addToFavIV.setImageResource(R.drawable.ic_add_to_favorites_off);

                initializePlayer(workOut.getVideo());
            }
        });
        addToFavIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWorkOut.isIsFav()) {
                    mWorkOut.setIsFav(false);
                    addToFavIV.setImageResource(R.drawable.ic_add_to_favorites_off);
                } else {
                    mWorkOut.setIsFav(true);
                    addToFavIV.setImageResource(R.drawable.ic_add_to_favorites_on);
                }

                workoutDetailsViewModel.updateFavorites(sharedPreferneceUtils.getUserName(), mWorkOut);
            }
        });
    }

    private void releasePlayer() {
        if (mExoplayer != null) {
            updateResumePosition();
            mExoplayer.stop();
            mExoplayer.release();
            mExoplayer = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        releasePlayer();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady && stateBuilder != null) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, mExoplayer.getCurrentPosition(), 1f);
        } else if (stateBuilder != null && (playbackState == ExoPlayer.STATE_READY)) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, mExoplayer.getCurrentPosition(), 1f);
        }
        if (mediaSession != null)
            mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next_step:
                currentIndex++;
                if (currentIndex < currentList.size()) {
                    mWorkOut = currentList.get(currentIndex);
                    name = mWorkOut.getName();
                    releasePlayer();
                    clearResumePosition();
                    loadData();
                } else currentIndex--;
                break;
            case R.id.tv_prev_step:
                currentIndex--;
                if (currentIndex >= 0) {
                    mWorkOut = currentList.get(currentIndex);
                    releasePlayer();
                    clearResumePosition();
                    name = mWorkOut.getName();
                    loadData();
                }
                else currentIndex++;
                break;
        }
    }

    private void initializePlayer(String videoUrl) {
        if (mExoplayer == null) {
            // 1. Create a default TrackSelector
            LoadControl loadControl = new DefaultLoadControl();
//            LoadControl loadControl = new DefaultLoadControl(
//                    new DefaultAllocator(true, 16),
//                    VideoPlayerConfig.MIN_BUFFER_DURATION,
//                    VideoPlayerConfig.MAX_BUFFER_DURATION,
//                    VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,
//                    VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER, -1, true);
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);
            // 2. Create the player
            mExoplayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultRenderersFactory(getContext())
                    , trackSelector, loadControl);
            mPlayerView.setPlayer(mExoplayer);
            buildMediaSource(Uri.parse(videoUrl));
            mExoplayer.setPlayWhenReady(true);
            updateWidget();
            boolean haveResumePosition = resumeWindow != C.INDEX_UNSET;
            if (haveResumePosition) {
                mExoplayer.seekTo(resumeWindow, resumePosition);

            }

        }
    }

    private void updateResumePosition() {
        resumeWindow = mExoplayer.getCurrentWindowIndex();
        resumePosition = mExoplayer.isCurrentWindowSeekable() ? Math.max(0, mExoplayer.getCurrentPosition())
                : C.TIME_UNSET;
    }

    private void clearResumePosition() {
        resumeWindow = C.INDEX_UNSET;
        resumePosition = C.TIME_UNSET;
    }

    private void buildMediaSource(Uri mUri) {

        if (mUri != null) {
            mPlayerView.setVisibility(View.VISIBLE);
            // Measures bandwidth during playback. Can be null if not required.
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getActivity(), getString(R.string.app_name)), bandwidthMeter);
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mUri);
            // Prepare the player with the source.
            mExoplayer.prepare(videoSource);


        }

    }

}
