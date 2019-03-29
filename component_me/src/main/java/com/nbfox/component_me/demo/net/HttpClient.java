package com.nbfox.component_me.demo.net;

public class HttpClient {
    public static<T,M> void sendRequstData(String url, T requsetData,
                                           Class<M> response,
                                           IJsonDataListener iJsonDataListener
                                           ) {

        IhttpRequest ihttpRequest=new JsonHttpRequest();
        CallBackListener callBackListener=new JsonCallBackListener<>(response,iJsonDataListener);
        HttpTask httpTask=new HttpTask(url,requsetData,ihttpRequest,callBackListener);
        ThreadPoolManager.getThreadPoolManager().addTask(httpTask);

    }

}
