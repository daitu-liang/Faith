package com.nbfox.component_me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.constants.ARouterConfig;

@Route(path = ARouterConfig.AROUTER_ME_MAIN)
public class MeActivity extends AppCompatActivity {

    @Autowired()
    String key1;
    @Autowired
    int key2;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity);
        ARouter.getInstance().inject(this);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("值=" + key1 + " - " + key2 + "\n" +
                "key1=" + key1);
    }


}
