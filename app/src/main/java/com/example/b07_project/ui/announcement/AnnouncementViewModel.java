package com.example.b07_project.ui.announcement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.b07_project.AnnouncementModule.Announcement;
import com.example.b07_project.AnnouncementModule.AnnouncementAdapter;
import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class AnnouncementViewModel extends ViewModel {

    private MutableLiveData<List<Announcement>> announcements;

    private List<Announcement> announcementList;

    public AnnouncementViewModel() {
        announcements = new MutableLiveData<>();
    }

    public LiveData<List<Announcement>> getAnnouncements() {
        if (announcements == null) {
            announcements = new MutableLiveData<>();
            //retrieveAnnouncementData();
        }
        return announcements;
    }
    private void retrieveAnnouncementData() {
        DatabaseReference announcementsRef = FirebaseDatabase.getInstance().getReference("announcements");

        announcementsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Announcement> announcementList = null;

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Announcement announcement = childSnapshot.getValue(Announcement.class);
                    announcementList.add(announcement);
                }

                //AnnouncementAdapter.setAnnouncements(announcementList);
                // announcementAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occurred
            }
        });
    }
    public MutableLiveData<List<Announcement>> getText() {
        return announcements;
    }
}