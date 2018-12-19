package com.nbfox.component_base.utils.proxy;

import android.util.Log;

import com.nbfox.component_base.constants.BaseConfig;

public class RealObject implements InterfaceTop {
    @Override
    public void getMyName() {
        Log.i(BaseConfig.BASE_TAG, "---->RealObject--getMyName="+this.getClass());
    }

    @Override
    public String getNameById(String id) {
        Log.i(BaseConfig.BASE_TAG, "---->RealObject-getNameById=id" + id);
        return id;
    }
}
