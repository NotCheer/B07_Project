package com.example.b07_project.ui.post;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PostViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Please Edit your Announcement!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
