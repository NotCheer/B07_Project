package com.example.b07_project.ui.events;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.EventModule.Event;
import com.example.b07_project.EventModule.EventAdapter;
import com.example.b07_project.EventModule.EventDataRetrieve;
import com.example.b07_project.EventModule.RSVPEventAdapter;
import com.example.b07_project.R;
import com.example.b07_project.UserName;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    private List<Event> allEventsSet = new ArrayList<>();
    private EventDataRetrieve retriever = new EventDataRetrieve();
    private UserName userName = UserName.getInstance();
    private final String userID = userName.name;
    private static final String TAG = "EventRecyclerViewFragment";
    protected TextView more_btn;
    protected RecyclerView mRecyclerView;
    protected EventAdapter allEventAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //initDataset();


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        rootView.setTag(TAG);
        Handler handler = new Handler();

        // Create a Runnable that calls the doSomething() method
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setAdapter(allEventAdapter);
            }
        };


        more_btn = rootView.findViewById(R.id.more_RSVP);
        more_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });


        //init RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.event_recyclerView_EventAvailable);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        allEventAdapter = new EventAdapter(allEventsSet);
        retriever.retrieveAllEvents(allEventAdapter);

        this.allEventsSet = retriever.getAllEventsSet();
        handler.postDelayed(runnable, 200);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        allEventAdapter.notifyItemChanged(0);
        Log.d("event","# of element by start"+ allEventAdapter.getItemCount());
    }

}