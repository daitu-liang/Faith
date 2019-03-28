package com.nbfox.component_me.demo.bus.livedatabus;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiveData<T> {

    //消息通道存放许多订阅者
    private Map<Integer, Observer<T>> map = new HashMap<>();

    private Map<Integer, List<T>> mPendingDelayList = new HashMap<>();

    public void observe(Activity activity, Observer<T> observer) {

        FragmentManager fm = activity.getFragmentManager();
        HoldFragment current = (HoldFragment) fm.findFragmentByTag("com.nbfox.component_me.demo.bus.livedatabus");

        if (current == null) {
            current = new HoldFragment();
            fm.beginTransaction().add(current, "com.nbfox.component_me.demo.bus.livedatabus").commitAllowingStateLoss();
        }
        current.setLifecycleListener(lifecycleListener);
        map.put(activity.hashCode(), observer);
    }


    public void setValue(T value) {
        List<Observer> destoryList = new ArrayList<>();
        for (Map.Entry<Integer, Observer<T>> entry : map.entrySet()) {
            Observer<T> observer = entry.getValue();
            Integer activityCode = entry.getKey();
            if (observer.getState() == Observer.SATE_ACTIVE) {
                observer.onChanged(value);
            }

            if (observer.getState() == Observer.SATE_ONPAUSE) {
                if(mPendingDelayList.get(activityCode)==null){
                    mPendingDelayList.put(activityCode,new ArrayList<T>());
                }
                if(!mPendingDelayList.get(activityCode).contains(value)){
                    mPendingDelayList.get(activityCode).add(value);
                }

            }
            if (observer.getState() == Observer.SATE_DESTROY) {
                destoryList.add(observer);
            }
        }

        for (Observer item : destoryList) {
            destoryList.remove(item);
        }
    }

    Handler handle = new Handler(Looper.getMainLooper());

    //子线程
    public void postValue(final T value) {
        synchronized (this) {
            handle.post(new Runnable() {
                @Override
                public void run() {
                    setValue(value);
                }
            });
        }
    }

    private LifecycleListener lifecycleListener = new LifecycleListener() {
        @Override
        public void onCreate(int activityCode) {

            map.get(activityCode).setState(Observer.SATE_INIT);
        }

        @Override
        public void onDetach(int activityCode) {
            map.remove(activityCode);
        }

        @Override
        public void onPause(int activityCode) {
            map.get(activityCode).setState(Observer.SATE_ONPAUSE);
        }

        @Override
        public void onStop(int activityCode) {
            map.get(activityCode).setState(Observer.SATE_ONPAUSE);
        }

        @Override
        public void onStart(int activityCode) {
            map.get(activityCode).setState(Observer.SATE_ACTIVE);
            if(mPendingDelayList.get(activityCode)==null||mPendingDelayList.get(activityCode).size()==0){
                return;
            }

            for(T t:mPendingDelayList.get(activityCode)){
                map.get(activityCode).onChanged(t);
            }

            mPendingDelayList.clear();

        }

    };
}