package com.example.b07_project.EventModule;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventDataRetrieve {
    private List<Event> eventsSet;
    private List<Event> RSVPEventsSet;
    private List<String> ids = new ArrayList<String>();

    public EventDataRetrieve() {
        eventsSet = new ArrayList<Event>();
        RSVPEventsSet = new ArrayList<Event>();
    }

    public List<String> getIds() {
        return ids;
    }

    public List<String> getRSVPInfo (String ID) {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        DatabaseReference userRef = db.getReference();

        userRef.child("users").child(ID).child("RSVP").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) return;
                List<String> ids = new ArrayList<String>();
                long size = snapshot.getChildrenCount();
                for(int i=0; i<size; i++) {
                    ids.add(snapshot.child(Integer.toString(i)).getValue(String.class));
                    Log.d("SRetriever",ids.get(i)+"eventID got");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return ids;

    }

    public List<Event> retrieveRSVPEvents(List<String> RSVPEventsID,RSVPEventAdapter adapter) {
        Log.d("SRetriever", "size of IDs: "+RSVPEventsID.size());
        RSVPEventsSet.clear();
        //eventsSet.add(new Event("a","a","a","a",1,null));
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        DatabaseReference eventRef = db.getReference();
        eventRef.child("Event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventsSet.clear();
                int index = 0;
                int max = RSVPEventsID.size();
                index = max - 1;

                Log.d("SRetriever", "retrieving");

                while (index >= 0) {
                    // Get the values from the dataSnapshot and create an Announcement object
                    Log.d("EventRetriever", "getting event #" + index);
                    String ID = RSVPEventsID.get(index).toString();
                    String eventName = dataSnapshot.child(ID).child("eventName").getValue(String.class);
                    String detail = dataSnapshot.child(ID).child("detail").getValue(String.class);
                    String location = dataSnapshot.child(ID).child("location").getValue(String.class);
                    String time = dataSnapshot.child(ID).child("time").getValue(String.class);
                    Integer limit = dataSnapshot.child(ID).child("limit").getValue(Integer.class);

                    Event event = new Event(eventName,detail,location,time,limit, null);

                    Log.d("SRetriever", "have: " + event.getEventName());
                    eventsSet.add(event);
                    Log.d("SRetriever","RSVPEventSet now have element #:"+RSVPEventsSet.size());
                    index--;
                }
                if(adapter != null) {
                    adapter.setEvents(RSVPEventsSet);
                    adapter.notifyItemChanged(0);
                }

            }

            public void onCancelled (DatabaseError databaseError){
                // Handle the error case, if any
            }
        });
        return eventsSet;
    }

    public List<Event> getAllEventsSet() {
        return eventsSet;
    }
    public List<Event> getRSVPEventsSet() {
        return RSVPEventsSet;
    }
    public List<Event> retrieveAllEvents(EventAdapter adapter) {
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
