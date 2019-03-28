package com.nbfox.component_me.demo.bus.livedatabus;

public interface LifecycleListener {

     void onCreate(int activityCode);
     void onDetach(int activityCode);
     void onPause(int activityCode);
     void onStop(int activityCode);
     void onStart(int activityCode);
}
