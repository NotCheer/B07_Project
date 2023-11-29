package com.example.b07_project.ui.post;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b07_project.AnnouncementModule.Announcement;
import com.example.b07_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.b07_project.databinding.FragmentPostannouncementBinding;
import com.google.firebase.database.ValueEventListener;

public class PostFragment extends Fragment {

    private EditText editTextTitle, editTextContent,editTextDate;
    private Button buttonPost;

    FirebaseDatabase db;
    DatabaseReference announcementRef;

    public PostFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_postannouncement, container, false);

        // Initialize views
        editTextTitle = view.findViewById(R.id.text_title);
        editTextContent = view.findViewById(R.id.text_content);
        editTextDate = view.findViewById(R.id.text_date);
        buttonPost = view.findViewById(R.id.buttonPost);
        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        announcementRef = db.getReference();
        // Set click listener for the post button
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title,content,date;
                title = editTextTitle.getText().toString();
                content = editTextContent.getText().toString();
                if(valid_date(editTextDate.getText().toString())){
                    date = editTextDate.getText().toString();
                }else{
                    Toast.makeText(getActivity(), "Date invalid",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                announcementRef.child("announcements").addListenerForSingleValueEvent
                        (new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Announcement announcement = new Announcement(title,content,date);
                        long max = snapshot.getChildrenCount();
                        announcementRef.child("announcements").child(Long.toString(max)).
                                setValue(announcement);
                        Toast.makeText(getActivity(), "Post Successfully",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }

    private boolean valid_date(String date){
        return date.matches("^(?!0000)\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$");
    }
}
