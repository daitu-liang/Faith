package com.nbfox.component_me.demo.net;


import java.io.InputStream;

public interface CallBackListener {
    void onSuccess(InputStream inputStream);

    void onFailure();
}
