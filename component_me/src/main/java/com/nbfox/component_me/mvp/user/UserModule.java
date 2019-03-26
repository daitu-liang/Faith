package com.nbfox.component_me.mvp.user;

import com.nbfox.component_me.mvp.ResposneResult;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    ResposneResult provideApple() {
        return new ResposneResult();
    }
}
