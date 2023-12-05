package com.example.b07_project.ui.complaints;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.b07_project.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ComplaintsFragment extends Fragment {

    ScrollView ComplaintView;
    LinearLayout linearLayout;
    TextView textView;
    Drawable drawable;
    FirebaseDatabase db;
    DatabaseReference userRef;

    @SuppressLint("UseCompatLoadingForDrawables")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_complaints, container, false);
        ComplaintView = view.findViewById(R.id.ComplaintView);
        linearLayout = view.findViewById(R.id.displayComplaints);
        textView = view.findViewById(R.id.noComplaints);
        drawable = ContextCompat.getDrawable(getContext(),R.drawable.text_box);

        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params. setMargins(10,0,10,20);

        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb.firebaseio.com/");
        userRef = db.getReference().child("Complaints");

        userRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                checkNoComplaints();

                String complaint = snapshot.getValue().toString();
                TextView textview = new TextView(getActivity());
                textview.setText(complaint);
                textview.setTextSize(22);
                textview.setBackground(drawable);
                textview.setPaddingRelative(20,10,20,50);
                textview.setLayoutParams(params);
                linearLayout.setGravity(Gravity.NO_GRAVITY);
                linearLayout.addView(textview);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void checkNoComplaints(){
        if(textView!=null)
            linearLayout.removeView(textView);
    }
}
