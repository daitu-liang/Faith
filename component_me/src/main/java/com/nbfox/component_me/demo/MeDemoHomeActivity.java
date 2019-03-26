package com.nbfox.component_me.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.constants.ARouterConfig;
import com.nbfox.component_me.R;
import com.nbfox.component_me.demo.refactorcode.DemoNetHttpActivity;

@Route(path = ARouterConfig.AROUTER_ME_DEMO)
public class MeDemoHomeActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_demo_home_activity);
        ARouter.getInstance().inject(this);
      tv = (TextView) findViewById(R.id.textView);
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeDemoHomeActivity.this, DemoNetHttpActivity.class));
            }
        });
    }

}
