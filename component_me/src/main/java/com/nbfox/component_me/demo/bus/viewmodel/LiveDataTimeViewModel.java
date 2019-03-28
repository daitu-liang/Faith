package com.nbfox.component_me.demo.bus.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class LiveDataTimeViewModel extends ViewModel {
    private String data;
    private MutableLiveData<String> mTime = new MutableLiveData<>();
    static int index = 0;

    public MutableLiveData<String> getmTime() {
        return mTime;
    }

    public void setmTime(MutableLiveData<String> mTime) {
        this.mTime = mTime;
    }

    public LiveDataTimeViewModel() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                index++;
                mTime.postValue("---->=" + index);
            }
        }, 1000, 1000);
    }
}
