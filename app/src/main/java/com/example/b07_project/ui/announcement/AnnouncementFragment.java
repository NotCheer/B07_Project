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

import java.util.ArrayList;
import java.util.List;

public class AnnouncementFragment extends Fragment {

    private List<Announcement> announcementSet;

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    protected RecyclerView mRecyclerView;
    protected AnnouncementAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference announcementRef;
    private FirebaseDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    public void setAnnouncementSet (List<Announcement> ann) {
        this.announcementSet = ann;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        rootView.setTag(TAG);
        //init RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AnnouncementAdapter(announcementSet);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    private void initDataset() {
        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        announcementRef = db.getReference();
        announcementRef.child("announcements").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Announcement> announcementList = new ArrayList<Announcement>();
                long index = 0;
                long max = dataSnapshot.getChildrenCount();
                index = max - 1;

                Log.d("retriever", "retrieving");

                while (index>=0) {
                    // Get the values from the dataSnapshot and create an Announcement object
                    Log.d("retriever", "looping");
                    String ID = Long.toString(index);
                    String title = dataSnapshot.child(ID).child("title").getValue(String.class);
                    String content = dataSnapshot.child(ID).child("content").getValue(String.class);
                    String date = dataSnapshot.child(ID).child("date").getValue(String.class);

                    Announcement announcement = new Announcement(title, content, date);
                    Log.d("retriever", "have: "+announcement.getContent());
                    announcementList.add(announcement);
                    index--;
                }
                setAnnouncementSet(announcementList);

            }

            public void onCancelled(DatabaseError databaseError) {
                // Handle the error case, if any
            }
        });
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


    }
    */
}