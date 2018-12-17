package com.nbfox.component_base.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.constants.ARouterConfig;

public class BaseApplicattion extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initARouterSDK();
    }

    private void initARouterSDK() {
        if(ARouterConfig.isARouterDebug){
            ARouter.openLog();     // Print log
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);
    }
}
