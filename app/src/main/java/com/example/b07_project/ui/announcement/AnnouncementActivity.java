package com.example.b07_project.ui.announcement;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.AnnouncementModule.Announcement;
import com.example.b07_project.AnnouncementModule.AnnouncementAdapter;
import com.example.b07_project.R;

import java.util.ArrayList;
import java.util.Date;

public class AnnouncementActivity extends AppCompatActivity {

    AnnouncementAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_display);

        // data to populate the RecyclerView with
        ArrayList<Announcement> animalNames = new ArrayList<>();
        Announcement announcement = new Announcement("aaa","bbb",new Date(2003,12,4));
        animalNames.add(announcement);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list_announcement);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnnouncementAdapter(this, animalNames);
        recyclerView.setAdapter(adapter);
    }

}
