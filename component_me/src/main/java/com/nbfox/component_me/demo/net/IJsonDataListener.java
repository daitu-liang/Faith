package com.nbfox.component_me.demo.net;

public interface IJsonDataListener<T> {
    void onSuccess(T clazz);
    void onFailure();
}
