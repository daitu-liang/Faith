package com.nbfox.component_me.mvp.user;

import com.nbfox.component_base.base.mvp.BasePresenter;

public class UserInfoPresent extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {

    @Override
    public void sendUserInfo(String name) {
        if (isViewAttached()) {

            getView().getUserInfo();
        }
    }
}
