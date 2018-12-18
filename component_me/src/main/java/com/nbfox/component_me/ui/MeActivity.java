package com.nbfox.component_me.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.base.BaseMvpActivity;
import com.nbfox.component_base.constants.ARouterConfig;
import com.nbfox.component_base.utils.ToastUtils;
import com.nbfox.component_me.R;
import com.nbfox.component_me.mvp.user.UserInfoContract;
import com.nbfox.component_me.mvp.user.UserInfoPresent;

@Route(path = ARouterConfig.AROUTER_ME_MAIN)
public class MeActivity extends BaseMvpActivity<UserInfoPresent>implements UserInfoContract.View {

    @Autowired()
    String key1;
    @Autowired
    int key2;

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("值=" + key1 + " - " + key2 + "\n" +
                "key1=" + key1);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.sendUserInfo("ppppp");
            }
        });
    }

    @Override
    protected UserInfoPresent createPresenter() {
        return new UserInfoPresent();
    }


    @Override
    public void getUserInfo() {
        ToastUtils.showToast("哈哈哈哈哈");

    }
}
