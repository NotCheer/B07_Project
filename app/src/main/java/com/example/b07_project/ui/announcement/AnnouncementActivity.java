package com.example.b07_project.ui.announcement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07_project.AnnouncementModule.Announcement;
import com.example.b07_project.AnnouncementModule.AnnouncementAdapter;
import com.example.b07_project.StudentsMain;
import com.example.b07_project.R;

import java.util.ArrayList;
import java.util.Date;

public class AnnouncementActivity extends AppCompatActivity {

    AnnouncementAdapter adapter;
    Button btn;
    TextView text;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_display);
        btn = findViewById(R.id.back_bottom);
        text = findViewById(R.id.detail_content);
        Intent intent = getIntent();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnouncementActivity.this, StudentsMain.class);
                startActivity(intent);
            }
        });

        text.setText(intent.getExtras().getString("content"));
    }

}
