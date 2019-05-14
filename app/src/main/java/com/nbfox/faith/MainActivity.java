package com.nbfox.faith;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.constants.ARouterConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_des)
    TextView tvDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvDes.setText(BuildConfig.HOST_URL);
    }


    @OnClick({R.id.button, R.id.button2, R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                ARouter.getInstance().build(ARouterConfig.AROUTER_HOME_MAIN).navigation();
//                startActivity(HomeActivity.getIntent(this));
                break;
            case R.id.button2:
                ARouter.getInstance().build(ARouterConfig.AROUTER_ME_MAIN)
                        .withString("key1", "apple")
                        .withInt("key2", 100)
                        .navigation();

//                startActivity(MeActivity.getIntent(this));
                break;
            case R.id.button3:
                ARouter.getInstance().build(ARouterConfig.AROUTER_ME_DEMO)
                        .navigation();
        }
    }
}
