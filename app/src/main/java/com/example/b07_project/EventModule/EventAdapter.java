package com.example.b07_project.EventModule;


import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.EventModule.Event;
import com.example.b07_project.R;
import com.example.b07_project.EventModule.EventActivity;
import com.example.b07_project.ui.events.EventActivity;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<com.example.b07_project.EventModule.EventAdapter.EventViewHolder> {
    private List<Event> EventList;

    public void setEvents(List<Event> EventList) {
        this.EventList = EventList;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView detailTextView;
        private final TextView locationTextView;
        private final TextView timeTextView;
        private final Context context;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            detailTextView = itemView.findViewById(R.id.lineEventDetail);
            nameTextView = itemView.findViewById(R.id.lineEventName);
            timeTextView = itemView.findViewById(R.id.lineEventTime);
            locationTextView = itemView.findViewById(R.id.lineEventLocation);

        }


        public TextView getContentTextView() {
            return contentTextView;
        }
        public TextView getTitleTextView() {
            return titleTextView;
        }
        public TextView getDateTextView() {
            return dateTextView;
        }
    }

    public EventAdapter(List<Event> EventList) {
        this.EventList = EventList;
    }

    @Override
    public com.example.b07_project.EventModule.EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                                                                    int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new com.example.b07_project.Event()Module.EventAdapter.EventViewHolder(view);

    }
    private int mExpandedPosition = -1;
    private RecyclerView recyclerView;
    @Override
    public void onBindViewHolder(@NonNull com.example.b07_project.EventModule.EventAdapter.EventViewHolder holder, final int position) {
        Event a = EventList.get(position);
        holder.getTitleTextView().setText(a.getTitle());
        holder.getContentTextView().setText(a.getContent());
        holder.getDateTextView().setText(a.getDate());

        holder.getContentTextView().setOnClickListener(new View.OnClickListener() {
            TextView itemView = holder.getContentTextView();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), EventActivity.class);
                Log.d("Events","clicked"+Integer.toString(holder.getBindingAdapterPosition()));
                intent.putExtra("content", EventList.get(holder.getBindingAdapterPosition()).getContent());
                itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (EventList==null) return 0;
        return EventList.size();
    }



}

