package com.example.b07_project.ui.announcement;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.example.b07_project.AnnouncementModule.Announcement;
import com.example.b07_project.AnnouncementModule.AnnouncementAdapter;
import com.example.b07_project.R;
import com.example.b07_project.databinding.FragmentAnnouncementBinding;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AnnouncementFragment extends Fragment {

    private FragmentAnnouncementBinding binding;
    private AnnouncementAdapter announcementAdapter;
    private List<Announcement> announcementList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AnnouncementViewModel announcementViewModel =
                new ViewModelProvider(this).get(AnnouncementViewModel.class);

        binding = FragmentAnnouncementBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textAnnoucement;
        //announcementViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    /**
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AnnouncementViewModel announcementViewModel =
                new ViewModelProvider(this).get(AnnouncementViewModel.class);


        View view = inflater.inflate(R.layout.fragment_announcement, container, false);

        // Initialize the AnnouncementList and AnnouncementAdapter
        List<Announcement> announcementList = null;
        announcementAdapter = new AnnouncementAdapter(announcementList);

        // Set up RecyclerView and attach the adapter
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(announcementAdapter);

        // Retrieve announcement data from Firebase
        retrieveAnnouncementData();

        return view;
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

                announcementAdapter.setAnnouncements(announcementList);
                // announcementAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occurred
            }
        });
    }
    */

        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}