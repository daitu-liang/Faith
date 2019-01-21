package com.nbfox.component_me.mvp.user;

import com.nbfox.component_me.mvp.Apple;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    Apple provideApple() {
        return new Apple();
    }
}
