package com.nbfox.component_me.demo.net;

public interface IhttpRequest {
    void setUrl(String url);

    void setData(byte[] data);

    void setListener(CallBackListener callBackListener);

    void execute();
}
