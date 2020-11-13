package com.nbfox.component_me.demo.bus.livedatabus;

import android.app.Activity;
import android.app.Fragment;

import java.util.HashMap;


/**
 * 手写LiveData  生命周期的绑定 利用fragment 和acitvity的生命周期关联
 *  Glide也是根据fragment生命周期进行管理
 */
public class HoldFragment extends Fragment {
    private int activityCode;

    public void setLifecycleListener(LifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
        HashMap<String,String> k=new HashMap<>();

    }

    private LifecycleListener lifecycleListener;
    @Override
    public void onAttach(Activity context) {
        activityCode=context.hashCode();
        super.onAttach(context);
        if(lifecycleListener!=null){
            lifecycleListener.onCreate(activityCode);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if(lifecycleListener!=null){
            lifecycleListener.onDetach(activityCode);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(lifecycleListener!=null){
            lifecycleListener.onPause(activityCode);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(lifecycleListener!=null){
            lifecycleListener.onStop(activityCode);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(lifecycleListener!=null){
            lifecycleListener.onStart(activityCode);
        }
    }


}
