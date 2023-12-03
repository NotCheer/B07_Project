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
public class ViewFeedBackAdapter extends RecyclerView.Adapter<com.example.b07_project.ViewModule.ViewFeedBackAdapter.FeedbackViewHolder> {
    private List<Feedback> FeedbackList;
    private LayoutInflater mInflater;


    public void setFeedback(List<Feedback> FeedbackList) {
        this.FeedbackList = FeedbackList;
    }

    public static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        private final TextView FeedbackTextView;
        private final TextView feedbackRate;


        public MediaRouteButton details;
        private final Context context;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            FeedbackTextView = itemView.findViewById(R.id.lineFeedBack);
            feedbackRate = itemView.findViewById(R.id.lineScore);
        }


        public TextView getFeedbackTextView() {
            return FeedbackTextView;
        }
        public TextView getFeedbackScore() {
            return feedbackRate;
        }

    }

    public ViewFeedBackAdapter(List<Feedback> FeedbackList) {
        this.FeedbackList = FeedbackList;
    }

    @Override
    public com.example.b07_project.ViewModule.ViewFeedBackAdapter.FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                                            int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_for_events_feedback, viewGroup, false);

        return new com.example.b07_project.ViewModule.ViewFeedBackAdapter.FeedbackViewHolder(view);

    }

    private int mExpandedPosition = -1;
    private RecyclerView recyclerView;

    @Override
    public void onBindViewHolder(@NonNull com.example.b07_project.ViewModule.ViewFeedBackAdapter.FeedbackViewHolder holder, final int position) {
        Feedback a = FeedbackList.get(position);
        holder.getFeedbackTextView().setText(a.getComment());
        holder.getFeedbackScore().setText(Double.toString(a.getRate()));

    }

    @Override
    public int getItemCount() {
        if (FeedbackList == null) return 0;
        return FeedbackList.size();
    }
}
