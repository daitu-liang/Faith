package com.nbfox.component_base.app;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.constants.ARouterConfig;

public class BaseApplicattion extends Application {
    public    static Context baseApplicattion;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
        initARouterSDK();

    }
    private static void setContext(Context mContext) {
        baseApplicattion = mContext;
    }

    public static Context getBaseApplicattion(){
        return baseApplicattion;
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
