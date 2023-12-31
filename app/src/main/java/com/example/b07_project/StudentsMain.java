package com.example.b07_project;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.b07_project.databinding.ActivityStudentsMainBinding;

public class StudentsMain extends AppCompatActivity {

    private ActivityStudentsMainBinding binding;

    private UserName userName = UserName.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("UserName",userName.name);

        binding = ActivityStudentsMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_student_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_announcement, R.id.navigation_feedback, R.id.navigation_events,
                R.id.navigation_CheckPOSt).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_students);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navStudentView, navController);
    }




}