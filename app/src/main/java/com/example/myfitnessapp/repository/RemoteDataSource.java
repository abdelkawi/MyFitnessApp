package com.example.myfitnessapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.myfitnessapp.data.local.WorkOut;
import com.example.myfitnessapp.data.remote.FireBaseMuscle;
import com.example.myfitnessapp.data.remote.FireBaseWorkOut;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteDataSource {
    FirebaseFirestore firebaseFirestore;
    CollectionReference workOutRef, usersRef, musclesRef;

    public RemoteDataSource() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        workOutRef = firebaseFirestore.collection("workouts");
        usersRef = firebaseFirestore.collection("users");
        musclesRef = firebaseFirestore.collection("muscles");
    }

    public MutableLiveData<List<WorkOut>> getWorkouts() {
        final MutableLiveData<List<WorkOut>> mutableLiveData = new MutableLiveData<>();
        final List<WorkOut> fireBaseWorkOuts = new ArrayList<>();
        workOutRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                } else {
                    for (DocumentSnapshot documentReference : queryDocumentSnapshots.getDocuments()) {
                        if (documentReference.exists())
                            fireBaseWorkOuts.add(
                                    new FireBaseWorkOut(
                                            documentReference.getData().get("name").toString(),
                                            documentReference.getData().get("affectedMuscle").toString(),
                                            documentReference.getData().get("function").toString(),
                                            documentReference.getData().get("image").toString(),
                                            documentReference.getData().get("video").toString(),
                                            documentReference.getData().get("description").toString(),
                                            Boolean.valueOf(documentReference.getData().get("isFav").toString())
                                    ).toWorkout());
                    }
                }
                mutableLiveData.setValue(fireBaseWorkOuts);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<Boolean> verifyOrAddUser(final String userName) {
        final MutableLiveData<Boolean> userIsLogged = new MutableLiveData<>();
        usersRef.document(userName).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                    userIsLogged.setValue(true);
                else {
                    Map<String, String> map = new HashMap<>();
                    map.put("id", userName);
                    usersRef.document(userName).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            userIsLogged.setValue(true);
                            Log.d("xxxxxx", "user added");

                        }
                    });
                }
                Log.d("xxxxxx", documentSnapshot.toString());
            }
        });
        return userIsLogged;

    }

    public void addToUserFavorites(String userName, WorkOut workOut) {
        usersRef.document(userName)
                .collection("favorites")
                .document(workOut.getName())
                .set(workOut).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("xxxxxxxxUserFav", "added");
            }
        });
    }

    public void deleteFromUserFavorites(String userName, WorkOut workOut) {
        usersRef.document(userName).collection("favorites").
                document(workOut.getName()).delete().
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("xxxxxxxxUserFav", "deleted");
                    }
                });
    }

    public MutableLiveData<List<FireBaseMuscle>> getMuscles() {
        final MutableLiveData<List<FireBaseMuscle>> mutableLiveData = new MutableLiveData<>();
        final List<FireBaseMuscle> fireBaseMuscles = new ArrayList<>();
        musclesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {

                } else {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                        if (documentSnapshot.exists()) {
                            fireBaseMuscles.
                                    add(new FireBaseMuscle(
                                            documentSnapshot.getData().get("name").toString(),
                                            documentSnapshot.getData().get("image").toString()
                                    ));
                        }
                    }
                }
                mutableLiveData.setValue(fireBaseMuscles);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<WorkOut>> getUserWorkouts(String userName) {
        final MutableLiveData<List<WorkOut>> mutableLiveData = new MutableLiveData<>();
        final List<WorkOut> fireBaseWorkOuts = new ArrayList<>();
        usersRef.document(userName).collection("favorites").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                } else {
                    for (DocumentSnapshot documentReference : queryDocumentSnapshots.getDocuments()) {
                        if (documentReference.exists())
                            fireBaseWorkOuts.add(
                                    new FireBaseWorkOut(
                                            documentReference.getData().get("name").toString(),
                                            documentReference.getData().get("affectedMuscle").toString(),
                                            documentReference.getData().get("function").toString(),
                                            documentReference.getData().get("image").toString(),
                                            documentReference.getData().get("video").toString(),
                                            documentReference.getData().get("description").toString(),
                                            Boolean.valueOf(documentReference.getData().get("isFav").toString())
                                    ).toWorkout());
                    }
                }
                mutableLiveData.setValue(fireBaseWorkOuts);
            }
        });
        return mutableLiveData;
    }
}