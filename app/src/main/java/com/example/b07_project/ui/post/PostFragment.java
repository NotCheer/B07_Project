package com.example.b07_project.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.b07_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.b07_project.databinding.FragmentPostBinding;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class PostFragment extends Fragment {

    private EditText editTextTitle, editTextContent;
    private Button buttonPost;

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        // Initialize views
        editTextTitle = view.findViewById(R.id.text_title);
        editTextContent = view.findViewById(R.id.text_content);
        buttonPost = view.findViewById(R.id.buttonPost);

        // Set click listener for the post button
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAnnouncement();
            }
        });

        return view;
    }

    private void postAnnouncement() {
        // Get the title and content from the editText views
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();
    }
}
