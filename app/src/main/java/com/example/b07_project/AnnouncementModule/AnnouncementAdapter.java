package com.example.b07_project.AnnouncementModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.R;

import java.util.List;
public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {
    private List<Announcement> announcementList;
    private LayoutInflater mInflater;

    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        private final TextView contentTextView;

        public TextView getContentTextView() {
            return contentTextView;
        }

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            contentTextView = itemView.findViewById(R.id.list_announcement);
            //itemView.setOnClickListener((View.OnClickListener) this);
        }

        public TextView getTextView() {
            return contentTextView;
        }

        public void bind(Announcement announcement) {
            contentTextView.setText(announcement.getContent());
        }
    }

    public AnnouncementAdapter(Context context,List<Announcement> announcementList) {
        this.mInflater = LayoutInflater.from(context);
        this.announcementList = announcementList;
    }

    public void setAnnouncements(Context context, List<Announcement> announcements) {
        this.announcementList = announcements;
    }
    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                         int viewType) {
        View view = mInflater.inflate(R.layout.text_row_item, viewGroup, false);

        return new AnnouncementViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, final int position) {
        Announcement a = announcementList.get(position);
        holder.contentTextView.setText(String.format("%s\n%s\n%s", a.getTitle(), a.getContent(), a.getDate()));
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }



}
