package com.example.b07_project.ui.checkpost;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CheckPOStViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CheckPOStViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}