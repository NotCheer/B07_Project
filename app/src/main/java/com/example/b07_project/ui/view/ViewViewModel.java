package com.example.b07_project.ui.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;

import com.example.b07_project.ui.schedule.Event;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ViewViewModel extends ViewModel {

    private MutableLiveData<List<Event>> views;

    private List<Event> EventsList;

    public ViewViewModel() {
        views = new MutableLiveData<>();
    }

    public LiveData<List<Event>> getviews() {
        if (views == null) {
            views = new MutableLiveData<>();
            //retrieveAnnouncementData();
        }
        return views;
    }

    private void retrieveViewData() {
        DatabaseReference announcementsRef = FirebaseDatabase.getInstance().getReference("views");

        announcementsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Event> ViewList = null;

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Event view = childSnapshot.getValue(Event.class);

                    ViewList.add(view);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occurred
            }
        });
    }

    public MutableLiveData<List<Event>> getText() {
        return views;
    }
}