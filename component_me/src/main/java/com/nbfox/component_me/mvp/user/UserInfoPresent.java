package com.nbfox.component_me.mvp.user;

import com.nbfox.component_base.base.mvp.BasePresenter;
import com.nbfox.component_base.module.UserInfo;
import com.nbfox.component_base.network.ApiSubscriber;
import com.nbfox.component_base.network.HttpConfig;
import com.nbfox.component_base.network.RetrofitClient;
import com.nbfox.component_base.network.api.commom.ApiCommom;
import com.nbfox.component_base.utils.ToastUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserInfoPresent extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {

    @Override
    public void sendUserInfo(String name) {
        if (isViewAttached()) {

//            getView().getUserInfo();

            getViewContext().getUserInfo();


            Map<String, String> paramsmap = new LinkedHashMap<>();
            paramsmap.put("userName", "liang1");
            paramsmap.put("password", "123456");
            paramsmap.put("deviceType", "1");
            paramsmap.put("deviceNo", "");
            RetrofitClient.getInstance()
                    .builder(ApiCommom.class)
                    .getLogin(paramsmap)
                    .compose(HttpConfig.<UserInfo>toTransformer())

                    .subscribe(new ApiSubscriber<UserInfo>() {
                        @Override
                        protected void onSuccess(UserInfo bean) {
                            ToastUtils.showToast(bean.getFullName());

                        }

                        @Override
                        protected void onErrorMsg(Throwable e) {

                        }
                    });
        }
    }
}
