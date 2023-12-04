package com.example.b07_project.ui.feedback;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.b07_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackFragment extends Fragment {

    private EditText complaint;
    FirebaseDatabase db;
    DatabaseReference userRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedbacks, container, false);
        complaint = view.findViewById(R.id.text_complaints);

        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb.firebaseio.com/");
        userRef = db.getReference();

        Button button_submit = (Button) view.findViewById(R.id.feedback_submit);

        button_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String complaints = complaint.getText().toString();

                if(TextUtils.isEmpty(complaints)){
                    Toast.makeText(getContext(), "Cannot be empty.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    userRef.child("Complaints").push().setValue(complaints);
                    complaint.setText("");
                    Toast.makeText(getContext(), "Complaint submit successfully",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
