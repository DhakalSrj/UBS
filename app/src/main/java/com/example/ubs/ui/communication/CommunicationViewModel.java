package com.example.ubs.ui.communication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CommunicationViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CommunicationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is communication fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}