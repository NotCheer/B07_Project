package com.example.b07_project.AnnouncementModule;

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

    public AnnouncementAdapter(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                         int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item,
                        viewGroup,
                        false);

        return new AnnouncementViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position) {
        holder.getTitleTextView().setText(announcementList.get(position).getTitle());
        holder.getContentTextView().setText(announcementList.get(position).getContent());
        holder.getDateTextView().setText((CharSequence) announcementList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }


    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView contentTextView;
        private TextView dateTextView;

        public TextView getContentTextView() {
            return contentTextView;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getDateTextView() {
            return dateTextView;
        }

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.announcement_wrapper);
            contentTextView = itemView.findViewById(R.id.announcement_wrapper);
            dateTextView = itemView.findViewById(R.id.announcement_wrapper);
        }

        public void bind(Announcement announcement) {
            titleTextView.setText(announcement.getTitle());
            contentTextView.setText(announcement.getContent());
            dateTextView.setText((CharSequence) announcement.getDate());
        }
    }
}
