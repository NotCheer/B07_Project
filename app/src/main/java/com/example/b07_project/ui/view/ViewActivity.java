package com.example.b07_project.ui.view;


import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

        mRecyclerView = findViewById(R.id.recycle_feedback);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));




        btn = findViewById(R.id.back_bottom_view);
        text = findViewById(R.id.detail_content_view);
        rate = findViewById(R.id.mean_rate);

        Log.d("0", "0");
        initDataset();
//        FeedbackSet.add(new Feedback("2", 2));
//        FeedbackSet.add(new Feedback("1", 6));
        Log.d("1", "1");

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAdapter = new ViewFeedBackAdapter(FeedbackSet);
                mRecyclerView.setAdapter(mAdapter);
                Log.d("2", "2");
                rate.setText("Mean Rate: " + getMeanRate(FeedbackSet));
            }
        };

        handler.postDelayed(runnable, 500);


        Intent intent = getIntent();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(com.example.b07_project.ui.view.ViewActivity.this, AdminMain.class);
//                startActivity(intent);
                finish();
            }
        });

        text.setText("Event Name: " + intent.getExtras().getString("ID"));
    }



    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter != null){
            mAdapter.notifyDataSetChanged();
            mAdapter = new ViewFeedBackAdapter(FeedbackSet);
            Log.d("AAAsize", "size"+ mAdapter.getItemCount());
            mRecyclerView.setAdapter(mAdapter);

        }

    }


//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
//        rootView.setTag(TAG);
//        //init RecyclerView
//        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new ViewFeedBackAdapter(FeedbackSet);
//        mRecyclerView.setAdapter(mAdapter);
//        return rootView;
//    }


    public void setFeedbackSet(List<Feedback> view) {
        this.FeedbackSet = view;
    }



    public String getMeanRate(List<Feedback> feedbackSet){
        for (int index = 0; index < FeedbackSet.size(); index++){
            mean_rate = mean_rate + FeedbackSet.get(index).rate;
        }
        mean_rate = mean_rate / FeedbackSet.size();
        String mRate = Double.toString(mean_rate);
        return mRate;

    }


    private void initDataset() {
        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        FeedbackRef = db.getReference();
        Intent intent = getIntent();
        String ID = intent.getExtras().getString("ID");
        Log.d("retriever", "ID" + ID);
        FeedbackRef.child("Event").child(ID).child("feedbacks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Feedback> ViewList = new ArrayList<Feedback>();

                Log.d("retriever", "retrieving");
                Log.d("count", "count" + dataSnapshot.getChildrenCount());
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the values from the dataSnapshot and create an Events object
                    Log.d("1", "ss");
//                    String ID = snapshot.getKey();
                    Log.d("ID", "ss");
                    String comment = snapshot.child("comment").getValue(String.class);
                    Log.d("success", "comment" + comment);
                    int rate = snapshot.child("rate").getValue(Integer.class);
                    Log.d("success", "rate"+ rate);
                    Feedback view = new Feedback(comment, rate);
                    ViewList.add(view);
               }
                Log.d("size", "size"+ ViewList.size());
                setFeedbackSet(ViewList);
                Log.d("ffffsize", "fsize"+ FeedbackSet.size());
                if(mAdapter != null){
                    mAdapter.notifyDataSetChanged();
                    Log.d("asize", "size"+ mAdapter.getItemCount());
                }


            }

            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error:" + databaseError.getMessage());
            }
        });
    }
}
