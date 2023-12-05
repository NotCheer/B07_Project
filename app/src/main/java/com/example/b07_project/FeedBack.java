package com.example.b07_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.b07_project.databinding.FragmentFeedbacksBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FeedBack extends AppCompatActivity {

    //private FragmentFeedbacksBinding binding;

    private RatingBar ratingBar;
    private EditText commentInput;
    private EditText event;
    private Button submitButton, backButton;
    private DatabaseReference databaseReference;
    private Map<String, String> optionMap;

    public void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_feedback);
        ratingBar = findViewById(R.id.ratingBar);
        event = findViewById(R.id.event);
        commentInput = findViewById(R.id.feedback);
        submitButton = findViewById(R.id.buttonSubmit);
        backButton = findViewById(R.id.button_back);
        databaseReference = FirebaseDatabase.getInstance().getReference("Event");
        optionMap=new HashMap<>();
        optionMap.put("hhh","0");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void submitFeedback() {
        float rating = ratingBar.getRating();
        String comment = commentInput.getText().toString();
        String eventName = event.getText().toString();
        String idx = optionMap.get(eventName);
        idx=UserName.getInstance().EventID;

        // Create a feedback object. Assuming you have a Feedback class that matches the structure in the database.
        com.example.b07_project.FeedBack.Feedback feedback = new com.example.b07_project.FeedBack.Feedback(comment, (int) rating); // Cast rating to int if your rating is an integer in Firebase

        DatabaseReference feedbackRef = databaseReference.child(idx).child("feedbacks").push();

        // Save the feedback under the event's 'feedbacks' node
        feedbackRef.setValue(feedback)
                .addOnSuccessListener(aVoid -> {
                    // Handle success
                    Toast.makeText(this, "Feedback submitted", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
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
