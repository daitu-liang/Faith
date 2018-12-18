package com.nbfox.component_me.mvp.user;

import com.nbfox.component_base.base.mvp.IBaseView;

public interface UserInfoContract {

    interface View extends IBaseView {
        void getUserInfo();
    }

    interface Presenter {
        void sendUserInfo(String name);
    }
}
