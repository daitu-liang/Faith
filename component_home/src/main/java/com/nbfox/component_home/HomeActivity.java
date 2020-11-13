package com.nbfox.component_home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.constants.ARouterConfig;

import butterknife.ButterKnife;

@Route(path = ARouterConfig.AROUTER_HOME_MAIN)
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.component_home_home_activity);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

    }
}
