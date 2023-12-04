package com.example.b07_project.ui.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.example.b07_project.R;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitComplaints extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.fragment_feedbacks);

        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb.firebaseio.com/");
        userRef = db.getReference();

        EditText text_complaints = (EditText) findViewById(R.id.text_complaints);
        Button button_submit = findViewById(R.id.feedback_submit);

        button_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String complaint = text_complaints.getText().toString();

                if(TextUtils.isEmpty(complaint)){
                    Toast.makeText(SubmitComplaints.this, "Cannot be empty.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    userRef.child("Complaints").push().setValue(complaint);
                    text_complaints.setText("");
                    Toast.makeText(SubmitComplaints.this, "Complaint submit successfully",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
