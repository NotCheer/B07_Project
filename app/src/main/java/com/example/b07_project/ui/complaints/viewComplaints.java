package com.example.b07_project.ui.complaints;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.b07_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewComplaints extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.fragment_complaints);

        ScrollView allComplaints = findViewById(R.id.ComplaintView);
        TextView no_complaints = findViewById(R.id.noComplaints);

        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb.firebaseio.com/");
        userRef = db.getReference().child("Complaints");

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    allComplaints.removeView(no_complaints);
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String complaint = dataSnapshot.getValue().toString();
                        TextView textView = (TextView) findViewById(R.id.text_box);
                        textView.setText(complaint);
                        allComplaints.addView(textView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
