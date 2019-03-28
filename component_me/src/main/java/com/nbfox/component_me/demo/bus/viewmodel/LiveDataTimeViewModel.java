package com.nbfox.component_me.demo.bus.viewmodel;


import com.nbfox.component_me.demo.bus.livedatabus.LiveData;

import java.util.Timer;
import java.util.TimerTask;
//extends ViewModel
public class LiveDataTimeViewModel {
    private String data;
    private LiveData<String> mTime = new LiveData<>();
    static int index = 0;

    public LiveData<String> getmTime() {
        return mTime;
    }

    public void setmTime(LiveData<String> mTime) {
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
