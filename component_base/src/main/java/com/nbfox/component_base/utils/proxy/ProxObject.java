package com.nbfox.component_base.utils.proxy;

import android.util.Log;

import com.nbfox.component_base.constants.BaseConfig;

public class ProxObject implements InterfaceTop {
    InterfaceTop mProxObject;
    public ProxObject (InterfaceTop mProxObject){
        this.mProxObject=mProxObject;
    }
    @Override
    public void getMyName() {
        Log.i(BaseConfig.BASE_TAG,"---->ProxObject--getMyName");
        mProxObject.getMyName();
    }

    @Override
    public String getNameById(String id) {
        Log.i(BaseConfig.BASE_TAG,"---->ProxObject-getNameById-id="+id);
        String tip = mProxObject.getNameById(id);
        return tip;
    }
}
