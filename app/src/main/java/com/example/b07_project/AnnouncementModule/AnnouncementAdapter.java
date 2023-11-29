package com.example.b07_project.AnnouncementModule;

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

import com.example.b07_project.R;
import com.example.b07_project.ui.announcement.AnnouncementActivity;

import java.util.List;
public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {
    private List<Announcement> announcementList;

    public void setAnnouncements(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        private final TextView contentTextView;
        private final TextView titleTextView;
        private final TextView dateTextView;
        public MediaRouteButton details;
        private final Context context;

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            contentTextView = itemView.findViewById(R.id.lineAnnouncementContent);
            titleTextView = itemView.findViewById(R.id.lineAnnouncementTitle);
            dateTextView = itemView.findViewById(R.id.lineAnnouncementDate);
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

    public AnnouncementAdapter(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                         int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new AnnouncementViewHolder(view);

    }
    private int mExpandedPosition = -1;
    private RecyclerView recyclerView;
    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, final int position) {
        Announcement a = announcementList.get(position);
        holder.getTitleTextView().setText(a.getTitle());
        holder.getContentTextView().setText(a.getContent());
        holder.getDateTextView().setText(a.getDate());

        holder.getContentTextView().setOnClickListener(new View.OnClickListener() {
            TextView itemView = holder.getContentTextView();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), AnnouncementActivity.class);
                Log.d("announcements","clicked"+Integer.toString(holder.getBindingAdapterPosition()));
                intent.putExtra("content", announcementList.get(holder.getBindingAdapterPosition()).getContent());
                itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (announcementList==null) return 0;
        return announcementList.size();
    }



}
