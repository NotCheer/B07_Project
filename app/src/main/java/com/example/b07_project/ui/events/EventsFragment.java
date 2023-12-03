package com.example.b07_project.ui.events;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.AnnouncementModule.AnnouncementAdapter;
import com.example.b07_project.EventModule.Event;
import com.example.b07_project.EventModule.EventAdapter;
import com.example.b07_project.EventModule.EventDataRetrieve;
import com.example.b07_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    private List<Event> eventsSet = new ArrayList<>();
    private EventDataRetrieve retriever = new EventDataRetrieve();

    private static final String TAG = "EventRecyclerViewFragment";
    protected RecyclerView mRecyclerView;
    protected EventAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;



    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //initDataset();
        retriever.initData(mAdapter);
        this.eventsSet = retriever.getEventsSet();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        rootView.setTag(TAG);

        //init RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.event_recyclerView_EventAvailable);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.d("event","eventSet set, #="+eventsSet.size());
        mAdapter = new EventAdapter(eventsSet);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyItemChanged(0);
        Log.d("event","# of element by start"+mAdapter.getItemCount());
    }

}