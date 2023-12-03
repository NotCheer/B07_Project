package com.example.b07_project.ui.view;


import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.AdminMain;
import com.example.b07_project.AnnouncementModule.Announcement;
import com.example.b07_project.AnnouncementModule.AnnouncementAdapter;
import com.example.b07_project.StudentsMain;
import com.example.b07_project.R;
import com.example.b07_project.ViewModule.Feedback;
import com.example.b07_project.ViewModule.ViewAdapter;
import com.example.b07_project.ViewModule.ViewFeedBackAdapter;
import com.example.b07_project.ui.schedule.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ViewActivity extends AppCompatActivity {
    private List<Feedback> FeedbackSet = new ArrayList<Feedback>();
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    protected RecyclerView mRecyclerView;
    protected ViewFeedBackAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    Button btn;
    TextView text;
    TextView rate;

    private DatabaseReference FeedbackRef;
    private FirebaseDatabase db;

    private double mean_rate = 0.0;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_display);
        btn = findViewById(R.id.back_bottom_view);
        text = findViewById(R.id.detail_content_view);
        rate = findViewById(R.id.mean_rate);
        initDataset();
        Log.d("1", "1");
        for (int index = 0; index < FeedbackSet.size(); index++){
            mean_rate = mean_rate + FeedbackSet.get(index).rate;
        }
        mean_rate = mean_rate / FeedbackSet.size();
        String mRate = Double.toString(mean_rate);
        rate.setText(mRate);

        Intent intent = getIntent();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.b07_project.ui.view.ViewActivity.this, AdminMain.class);
                startActivity(intent);
            }
        });

        text.setText(intent.getExtras().getString("ID"));
    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        rootView.setTag(TAG);
        //init RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ViewFeedBackAdapter(FeedbackSet);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }


    public void setFeedbackSet(List<Feedback> view) {
        this.FeedbackSet = view;
    }



    public double getMeanRate(DataSnapshot dataForFeedback){
        long max = dataForFeedback.getChildrenCount();
        long index = max - 1;

        double rate = 0;

        while(index >= 0){
            int score = dataForFeedback.child("rate").getValue(Integer.class);
            rate = rate + score;
        }
        rate = rate / max;

        return rate;
    }


    private void initDataset() {
        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        FeedbackRef = db.getReference();
        Intent intent = getIntent();
        String ID = intent.getExtras().getString("ID");
        FeedbackRef.child("Event").child(ID).child("feedbacks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Feedback> ViewList = new ArrayList<Feedback>();

                Log.d("retriever", "retrieving");

               for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the values from the dataSnapshot and create an Events object
                    Log.d("retriever", "looping");
                    String ID = snapshot.getKey();
                    String comment = dataSnapshot.child(ID).child("comment").getValue(String.class);
                    int rate = dataSnapshot.child(ID).child("rate").getValue(Integer.class);

                    Feedback view = new Feedback(comment, rate);
                    ViewList.add(view);
               }
               setFeedbackSet(ViewList);

            }

            public void onCancelled(DatabaseError databaseError) {
                // Handle the error case, if any
            }
        });
    }
}
