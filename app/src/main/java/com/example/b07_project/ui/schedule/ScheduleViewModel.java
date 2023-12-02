package com.example.b07_project.ui.schedule;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScheduleViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ScheduleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Schedule Event");
    }

    public LiveData<String> getText() {
        return mText;
    }
}