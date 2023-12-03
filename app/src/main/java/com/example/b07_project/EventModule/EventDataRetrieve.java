package com.example.b07_project.EventModule;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.b07_project.AnnouncementModule.Announcement;
import com.example.b07_project.EventModule.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventDataRetrieve {
    private List<Event> eventsSet;

    public EventDataRetrieve() {
        eventsSet = new ArrayList<Event>();
    }

    public List<Event> getEventsSet() {
        return eventsSet;
    }
    public List<Event> initData(EventAdapter adapter) {
        eventsSet.clear();
        //eventsSet.add(new Event("a","a","a","a",1,null));
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        DatabaseReference eventRef = db.getReference();
        eventRef.child("Event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventsSet.clear();
                long index = 0;
                long max = dataSnapshot.getChildrenCount();
                index = max - 1;

                Log.d("retriever", "retrieving");

                while (index >= 0) {
                    // Get the values from the dataSnapshot and create an Announcement object
                    Log.d("EventRetriever", "getting event #" + index);
                    String ID = Long.toString(index);
                    String eventName = dataSnapshot.child(ID).child("eventName").getValue(String.class);
                    String detail = dataSnapshot.child(ID).child("detail").getValue(String.class);
                    String location = dataSnapshot.child(ID).child("location").getValue(String.class);
                    String time = dataSnapshot.child(ID).child("time").getValue(String.class);
                    Integer limit = dataSnapshot.child(ID).child("limit").getValue(Integer.class);

                    Event event = new Event(eventName,detail,location,time,limit, null);

                    Log.d("retriever", "have: " + event.getEventName());
                    eventsSet.add(event);
                    Log.d("retriever","eventSet now have element #:"+eventsSet.size());
                    index--;
                }
                if(adapter != null) {
                    adapter.setEvents(eventsSet);
                    adapter.notifyItemChanged(0);
                }

            }

            public void onCancelled (DatabaseError databaseError){
                // Handle the error case, if any
            }
        });
        return eventsSet;
    }

}
