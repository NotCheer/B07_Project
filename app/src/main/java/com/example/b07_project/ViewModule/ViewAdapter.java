package com.example.b07_project.ViewModule;
import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.b07_project.R;
import com.example.b07_project.ui.schedule.Event;
import com.example.b07_project.ui.view.ViewActivity;

import java.util.List;
public class ViewAdapter extends RecyclerView.Adapter<com.example.b07_project.ViewModule.ViewAdapter.ViewViewHolder> {
    private List<Event> EventsList;
    private LayoutInflater mInflater;


    public void setEvents(List<Event> EventList) {
        this.EventsList = EventList;
    }

    public static class ViewViewHolder extends RecyclerView.ViewHolder {
        private final TextView DetailTextView;
        private final TextView NameTextView;
        private final TextView dateTextView;


        public MediaRouteButton details;
        private final Context context;

        public ViewViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            DetailTextView = itemView.findViewById(R.id.EventDetail);
            NameTextView = itemView.findViewById(R.id.lineEventName);
            dateTextView = itemView.findViewById(R.id.lineEventDate);
        }


        public TextView getDetailTextView() {
            return DetailTextView;
        }

        public TextView getNameTextView() {
            return NameTextView;
        }

        public TextView getDateTextView() {
            return dateTextView;
        }

    }

    public ViewAdapter(List<Event> viewList) {
        this.EventsList = viewList;
    }

    @Override
    public com.example.b07_project.ViewModule.ViewAdapter.ViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                                            int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item_for_view_events, viewGroup, false);

        return new com.example.b07_project.ViewModule.ViewAdapter.ViewViewHolder(view);

    }

    private int mExpandedPosition = -1;
    private RecyclerView recyclerView;

    @Override
    public void onBindViewHolder(@NonNull com.example.b07_project.ViewModule.ViewAdapter.ViewViewHolder holder, final int position) {
        Event a = EventsList.get(position);
        holder.getNameTextView().setText(a.getEventName());
        holder.getDetailTextView().setText(a.getDetail());
        holder.getDateTextView().setText(a.getTime());


        holder.getDetailTextView().setOnClickListener(new View.OnClickListener() {
            TextView itemView = holder.getDetailTextView();

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), ViewActivity.class);
                Log.d("Events", "clicked" + Integer.toString(holder.getBindingAdapterPosition()));
                intent.putExtra("ID", a.getID());

                itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (EventsList == null) return 0;
        return EventsList.size();
    }
}
