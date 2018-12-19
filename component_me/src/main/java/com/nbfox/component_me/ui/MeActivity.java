package com.nbfox.component_me.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.base.BaseMvpActivity;
import com.nbfox.component_base.constants.ARouterConfig;
import com.nbfox.component_base.constants.BaseConfig;
import com.nbfox.component_base.utils.ToastUtils;
import com.nbfox.component_base.utils.proxy.DynamicProxyHandler;
import com.nbfox.component_base.utils.proxy.InterfaceTop;
import com.nbfox.component_base.utils.proxy.ProxObject;
import com.nbfox.component_base.utils.proxy.RealObject;
import com.nbfox.component_me.R;
import com.nbfox.component_me.mvp.user.UserInfoContract;
import com.nbfox.component_me.mvp.user.UserInfoPresent;

import java.lang.reflect.Proxy;

@Route(path = ARouterConfig.AROUTER_ME_MAIN)
public class MeActivity extends BaseMvpActivity<UserInfoPresent> implements UserInfoContract.View {

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
        Log.i(BaseConfig.BASE_TAG, "-----开始-----");
        Log.i(BaseConfig.BASE_TAG, "--1-->RealObject--------");

        RealObject realObject = new RealObject();
//        realObject.getMyName();
//        realObject.getNameById("自己调用realObject-getNameById");



        Log.i(BaseConfig.BASE_TAG, "--2-->ProxObject--------");
        ProxObject proxObject=new ProxObject(realObject);
        proxObject.getMyName();
        String tipPro = proxObject.getNameById("代理调用realObject中getNameById方法");
        Log.i(BaseConfig.BASE_TAG, "---ProxObject->tipPro"+tipPro);

        Log.i(BaseConfig.BASE_TAG, "--2-->动态代理--------");
        InterfaceTop mproxy = (InterfaceTop) Proxy.newProxyInstance(
                InterfaceTop.class.getClassLoader(),
                new Class[]{InterfaceTop.class},
                new DynamicProxyHandler(realObject)

        );
        Log.i(BaseConfig.BASE_TAG, "---->动态代理--mproxy=" + mproxy.getClass());
        //代理对象相关联的InvocationHandler，只有在代理对象调用方法时，才会执行它的invoke方法
        Log.i(BaseConfig.BASE_TAG, "---->动态代理--mproxy调用mproxy.getMyName()");
        mproxy.getMyName();
        Log.i(BaseConfig.BASE_TAG, "---->动态代理--mproxy调用 mproxy.getNameById(啦啦啦啦参数参数)");
        String name = mproxy.getNameById("啦啦啦啦参数参数");
        Log.i(BaseConfig.BASE_TAG, "name=" + name);


    }
}
