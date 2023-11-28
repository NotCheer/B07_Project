package com.example.b07_project.ui.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ViewViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ViewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ViewEvents fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
