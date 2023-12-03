package com.example.b07_project.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.ViewModule.ViewAdapter;
import com.example.b07_project.ui.schedule.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.example.b07_project.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFragment extends Fragment {

    private List<Event> Set = new ArrayList<Event>();

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    protected RecyclerView mRecyclerView;
    protected ViewAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference EventsRef;
    private FirebaseDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataset();
    }

    public void setEventsSet(List<Event> view) {
        this.Set = view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
        mAdapter = new ViewAdapter(Set);
        mRecyclerView.setAdapter(mAdapter);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        rootView.setTag(TAG);
        //init RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ViewAdapter(Set);
        Log.d("haha", "haha");
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }


    private void initDataset() {
        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        EventsRef = db.getReference();
        EventsRef.child("Event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Event> ViewList = new ArrayList<Event>();
                long index = 0;
                long max = dataSnapshot.getChildrenCount();
                index = max - 1;

                Log.d("for Events", "retrieving");

                while(index>=0){
                    // Get the values from the dataSnapshot and create an Events object
                    Log.d("For Events", "looping");
                    String ID = Long.toString(index);
                    String Event_name = dataSnapshot.child(ID).child("eventName").getValue(String.class);
                    Log.d("name", "got: " + Event_name);
                    String Detail = dataSnapshot.child(ID).child("detail").getValue(String.class);
                    Log.d("Detail", "got: " + Detail);
                    String date = dataSnapshot.child(ID).child("time").getValue(String.class);
                    Log.d("Date", "got: " + date);
                    String Location = dataSnapshot.child(ID).child("location").getValue(String.class);
                    int Limit = dataSnapshot.child(ID).child("limit").getValue(Integer.class);
//                    int Limit = 1;
//                    String Detail = "1";
//                    String date = "1";
//                    String Location = "1";

                    Event view = new Event(Event_name, Location, Detail, date, Limit, ID);
                    Log.d("retriever", "got: " + view.getDetail());
                    ViewList.add(view);
                    index--;
                }
                setEventsSet(ViewList);
                if(mAdapter != null){
                    mAdapter.notifyItemChanged(0);
                }

            }


            public void onCancelled(DatabaseError databaseError) {
                // Handle the error case, if any
            }
        });
    }
}
