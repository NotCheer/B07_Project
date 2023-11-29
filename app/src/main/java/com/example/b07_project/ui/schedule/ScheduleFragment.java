package com.example.b07_project.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b07_project.databinding.FragmentScheduleBinding;
import com.example.b07_project.ui.complaints.ComplaintsViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.time.format.DateTimeFormatter;


public class ScheduleFragment extends Fragment {
    private FragmentScheduleBinding binding;
    TextInputEditText editText_eventName,editText_location,editText_time,editText_detail,limits;
    Button btn_send;
    FirebaseDatabase db;
    DatabaseReference userRef;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        userRef = db.getReference();
        editText_eventName = binding.EventName;
        editText_location = binding.Location;
        editText_time = binding.time;
        editText_detail = binding.detail;
        limits = binding.limit;
        btn_send = binding.btnSchedule;
        final TextView textView = binding.titleSchedule;
        scheduleViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName,location,time,detail,limiter;
                int limit;
                eventName=editText_eventName.getText().toString();
                location = editText_location.getText().toString();
                detail = editText_detail.getText().toString();
                checkString(eventName,location,detail);
                if(validateDate(editText_time.getText().toString())){
                    time = editText_time.getText().toString();
                }else{
                    Toast.makeText(getActivity(), "Time invalid",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                limiter = limits.getText().toString();
                if(validInteger(limiter)){
                    limit = Integer.parseInt(limiter);
                }else{
                    Toast.makeText(getActivity(), "Participant limit invalid",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                userRef.child("Event").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Event event = new Event(eventName,location,detail,time,limit);
                        long max = snapshot.getChildrenCount();

                        userRef.child("Event").child(Long.toString(max)).setValue(event);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void showToast(String message){
        if(getContext() == null) {
            Toast.makeText(getContext(), message + " is empty", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkString(String eventName, String location,String detail){
        if(eventName == null && eventName.isEmpty()){
            showToast("Event Name");
        }
        if(location == null && location.isEmpty()){
            showToast("Location");
        }
        if(detail == null && detail.isEmpty()){
            showToast("Event Detail");
        }
    }

    private boolean validateDate(String time){
        return time.matches("^\\d{4}/\\d{2}/\\d{2}$");
    }
    private boolean validInteger(String limit){
        try{
            int limits = Integer.parseInt(limit);
            if(limits<1){
                return false;
            }
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
