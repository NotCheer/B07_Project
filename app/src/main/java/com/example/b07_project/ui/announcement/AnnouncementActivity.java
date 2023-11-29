package com.example.b07_project.ui.announcement;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
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

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            AnnouncementFragment fragment = new AnnouncementFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
        /*
        // data to populate the RecyclerView with
        ArrayList<Announcement> announcements = new ArrayList<>();
        Announcement announcement = new Announcement("aaa","bbb","2003/12/4)");
        announcements.add(announcement);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list_announcement);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnnouncementAdapter(announcements);
        recyclerView.setAdapter(adapter);

         */
    }

}
