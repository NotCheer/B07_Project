package com.example.b07_project.ui.feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b07_project.R;
import com.google.firebase.database.DatabaseReference;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b07_project.databinding.FragmentFeedbacksBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FeedbackFragment extends Fragment {

    private FragmentFeedbacksBinding binding;

    private RatingBar ratingBar;
    private EditText commentInput;
    private EditText event;
    private Button submitButton;
    private DatabaseReference databaseReference;
    private Map<String, String> optionMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //FeedbackViewModel feedbackViewModel =
        //        new ViewModelProvider(this).get(FeedbackViewModel.class);
//
        //binding = FragmentFeedbacksBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
//
        //final TextView textView = binding.textFeedback;
        //feedbackViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //return root;
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_feedbacks, container, false);
        ratingBar = view.findViewById(R.id.ratingBar);
        event = view.findViewById(R.id.event);
        commentInput = view.findViewById(R.id.feedback);
        submitButton = view.findViewById(R.id.buttonSubmit);
        databaseReference = FirebaseDatabase.getInstance().getReference("Event");
        optionMap=new HashMap<>();
        optionMap.put("hhh","0");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
        return view;
    }
    private void submitFeedback() {
        float rating = ratingBar.getRating();
        String comment = commentInput.getText().toString();
        String eventName = event.getText().toString();
        String idx = optionMap.get(eventName);
        idx="0";

        // Create a feedback object. Assuming you have a Feedback class that matches the structure in the database.
        Feedback feedback = new Feedback(comment, (int) rating); // Cast rating to int if your rating is an integer in Firebase

        DatabaseReference feedbackRef = databaseReference.child(idx).child("feedbacks").push();

        // Save the feedback under the event's 'feedbacks' node
        feedbackRef.setValue(feedback)
                .addOnSuccessListener(aVoid -> {
                    // Handle success
                    Toast.makeText(getActivity(), "Feedback submitted", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Toast.makeText(getActivity(), "Failed to submit feedback", Toast.LENGTH_SHORT).show();
                });
    }

    // Feedback data model class to match your Firebase structure
    public static class Feedback {
        public String comment;
        public int rate; // Assuming rate is an integer as shown in your screenshot

        public Feedback(String comment, int rate) {
            this.comment = comment;
            this.rate = rate;
        }
    }
}
