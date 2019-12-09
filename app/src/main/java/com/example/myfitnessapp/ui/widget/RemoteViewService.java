package com.example.myfitnessapp.ui.widget;

import android.content.Context;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import com.example.myfitnessapp.R;

import java.util.List;

public class RemoteViewService implements RemoteViewsService.RemoteViewsFactory {

    String name;
    Context mContext;

    public RemoteViewService(String name, Context mContext) {
        this.name = name;
        this.mContext = mContext;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();
        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {

        name= "";
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.now_playing_widget);
        rv.setTextViewText(R.id.tv_workout_title, name);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
