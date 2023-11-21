package com.example.b07_project.ui.announcement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnnouncementViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AnnouncementViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Announcement fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}