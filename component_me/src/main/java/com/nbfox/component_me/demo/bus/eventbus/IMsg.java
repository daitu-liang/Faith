package com.nbfox.component_me.demo.bus.eventbus;

import android.util.Log;

import com.nbfox.component_base.utils.ToastUtils;

public class IMsg {

    public String name;

    public IMsg(String name) {
        this.name = name;
    }
    public IMsg() {

    }
    public void doIMsg(String str) {
        ToastUtils.showToast("str=" + str);
        Log.i("nbfox", "IMsg=str=" + str);
    }


}
