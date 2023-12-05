package com.example.b07_project.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.b07_project.R;
import com.example.b07_project.UserName;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentEventActivity extends AppCompatActivity {
    Button btn_back;
    Button btn_RSVP;
    TextView text;
    private final String name = UserName.getInstance().name;
    private String eventID = "1";
    Intent intent;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
            "firebaseio.com/");
    DatabaseReference reference = db.getReference();

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_event_display);
        btn_back = findViewById(R.id.back_bottom);
        text = findViewById(R.id.detail_content);
        btn_RSVP = findViewById(R.id.RSVP_button);
        intent = getIntent();
        eventID = intent.getExtras().getString("eventID");

        text.setText(intent.getExtras().getString("eventInfo"));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_RSVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot s = snapshot.child("users").child(name);
                        boolean flag = true;
                        if (s.hasChild("RSVP")) {
                            int index = 0;
                            while(s.child("RSVP").hasChild(Integer.toString(index))) {
                                String registered = s.child("RSVP").child(Integer.toString(index)).getValue(String.class);
                                Log.d("checkID","reg:"+registered);
                                if (registered.equals(eventID)) {
                                    Log.d("RSVP","get id"+s.child("RSVP").child(Integer.toString(index)).getValue(String.class));
                                    Toast.makeText(StudentEventActivity.this, "You have registered already", Toast.LENGTH_SHORT).show();
                                    flag = false;
                                    btn_RSVP.setText("Registered");
                                }
                                index++;
                            }
                            if(flag) {
                                reference.child("users").child(name)
                                        .child("RSVP").child(Integer.toString(index))
                                        .setValue(eventID);
                                btn_RSVP.setText("Success");
                            }
                        }
                        // First time RSVP
                        if (!s.hasChild("RSVP")) {
                            reference.child("users").child(name)
                                    .child("RSVP").child("0")
                                    .setValue(eventID);
                            btn_RSVP.setText("Success");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

}
