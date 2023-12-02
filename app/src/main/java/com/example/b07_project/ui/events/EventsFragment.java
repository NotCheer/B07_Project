package com.example.b07_project.ui.events;

import android.os.Bundle;
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
import com.example.b07_project.R;

import java.util.List;

public class EventsFragment extends Fragment {

    private List<Event> eventsSet;

    private static final String TAG = "EventRecyclerViewFragment";
    protected RecyclerView mRecyclerView;
    protected EventAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //initDataset();
    }

    public void setEventsSet(List<Event> eventsSet) {
        this.eventsSet = eventsSet;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.event_recycler_view_fragment, container, false);
        rootView.setTag(TAG);

        //init RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.allEventsList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventAdapter(eventsSet);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}