package com.nbfox.component_me.mvp.user;

import com.nbfox.component_me.ui.MeActivity;

import dagger.Component;

@Component(modules = {UserModule.class})
public interface UserComponent {
    void inject(MeActivity activity);
}
