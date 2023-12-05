package com.example.b07_project.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.AnnouncementModule.AnnouncementAdapter;
import com.example.b07_project.EventModule.Event;
import com.example.b07_project.EventModule.EventAdapter;
import com.example.b07_project.EventModule.EventDataRetrieve;
import com.example.b07_project.EventModule.RSVPEventAdapter;
import com.example.b07_project.R;
import com.example.b07_project.UserName;

import java.util.ArrayList;
import java.util.List;

public class RSVPEventActivity extends AppCompatActivity {
    AnnouncementAdapter adapter;
    Button btn;
    TextView text;
    private List<Event> RSVPEventsSet = new ArrayList<>();
    private UserName userName = UserName.getInstance();
    private final String userID = userName.name;
    private EventDataRetrieve retriever;
    private static final String TAG = "EventRecyclerViewFragment";
    protected TextView more_btn;
    protected RecyclerView mRecyclerView;
    protected RSVPEventAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsvp_events_display);
        btn = findViewById(R.id.back_bottom);
        Intent intent = getIntent();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Handler handler = new Handler();

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                RSVPEventsSet = retriever.getRSVPEventsSet();
                mAdapter = new RSVPEventAdapter(RSVPEventsSet);
                mRecyclerView.setAdapter(mAdapter);
                Log.d("RSVP","3 | have RSVP size" + retriever.getIds().size());
            }

        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                retriever.retrieveRSVPEvents(retriever.getIds(),mAdapter);
                Log.d("RSVP","2 | have RSVP size" + retriever.getIds().size());

            }
        };
        mAdapter = new RSVPEventAdapter();
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        retriever = new EventDataRetrieve();
        retriever.updateIDs(userID);
        mRecyclerView.setLayoutManager(mLayoutManager);
        handler.postDelayed(runnable1, 400);
        Log.d("RSVP","1 | have RSVP size" + retriever.getIds().size());
        handler.postDelayed(runnable2, 600);
    }


    //    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.activity_rsvp_events_display, container, false);
//        rootView.setTag(TAG);
//        Handler handler = new Handler();
//
//        // Create a Runnable that calls the doSomething() method
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                mRecyclerView.setAdapter(mAdapter);
//            }
//        };
//
//        Runnable runnable1 = new Runnable() {
//            @Override
//            public void run() {
//                mAdapter = new RSVPEventAdapter(RSVPEventsSet);
//                retriever.retrieveRSVPEvents(retriever.getIds(),mAdapter);
//                RSVPEventsSet = retriever.getRSVPEventsSet();
//                Log.d("RSVP","have RSVP size" + retriever.getIds().size());
//            }
//        };
//        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        handler.postDelayed(runnable1, 200);
//        Log.d("RSVP","!have RSVP size" + retriever.getIds().size());
//        handler.postDelayed(runnable, 200);
//        return rootView;
//    }
}
