package com.example.b07_project.EventModule;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.R;
import com.example.b07_project.ui.events.StudentEventActivity;

import java.util.List;

public class RSVPEventAdapter extends RecyclerView.Adapter<RSVPEventAdapter.EventViewHolder> {
    private List<Event> EventList;

    public void setEvents(List<Event> EventList) {
        this.EventList = EventList;
    }

    public List<Event> getEventList() {
        return EventList;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView locationTextView;
        private final TextView timeTextView;
        private final Button button;
        private final Context context;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            nameTextView = itemView.findViewById(R.id.lineEventName);
            timeTextView = itemView.findViewById(R.id.lineEventTime);
            locationTextView = itemView.findViewById(R.id.lineEventLocationAndMaxNumber);
            button = itemView.findViewById(R.id.buttonEvent);

        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getLocationTextView() {
            return locationTextView;
        }

        public TextView getTimeTextView() {
            return timeTextView;
        }

        public Context getContext() {
            return context;
        }
        public Button getButton() {
            return button;
        }
    }

    public RSVPEventAdapter(List<Event> EventList) {
        this.EventList = EventList;
    }



    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                              int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.student_events_text_row_item, viewGroup, false);

        return new EventViewHolder(view);

    }
    private int mExpandedPosition = -1;
    private RecyclerView recyclerView;
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        Log.d("eventAdapter","creating View");
        Event a = EventList.get(holder.getBindingAdapterPosition());
        if(a==null) Log.d("adapter","empty event at eventAdapter");
        holder.getNameTextView().setText(a.getEventName());
        holder.getTimeTextView().setText(a.getTime());
        holder.getLocationTextView().setText(String.format("%s(max attendee:%d)", a.getLocation(), a.getLimit()));



        holder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = holder.getBindingAdapterPosition();
                Intent intent = new Intent(holder.getContext(), StudentEventActivity.class);
                Event eventInfo = EventList.get(p);
                String id = Integer.toString(EventList.size()-p-1);
                Log.d("Events","clicked"+Integer.toString(p));
                intent.putExtra("eventInfo", eventInfo);
                intent.putExtra("eventID", id);
                holder.getButton().getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (EventList==null) return 0;
        return EventList.size();
    }



}

