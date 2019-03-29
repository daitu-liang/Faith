package com.nbfox.component_me.demo.net;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.nbfox.component_base.utils.Logger;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class HttpTask<T> implements Runnable, Delayed {

    private  IhttpRequest mHttpRequest;

    public HttpTask(String url, T requsetData, IhttpRequest httpRequest, CallBackListener listener){
        mHttpRequest=httpRequest;
        mHttpRequest.setUrl(url);
        mHttpRequest.setListener(listener);
        String content= JSON.toJSONString(requsetData);
        try {
            mHttpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try{
            Logger.getLogger("HttpTask").i("run()", "HttpTask任务-执行execute()");
            mHttpRequest.execute();
        }catch (Exception e){
            Logger.getLogger("HttpTask").i("run()", "HttpTask任务-执行execute()失败---添加重制任务队列");
            ThreadPoolManager.getThreadPoolManager().addDelayTask(this);
        }
    }

    private long delayTime;

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime =System.currentTimeMillis()+delayTime;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    private int retryCount;
    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        return unit.convert(this.delayTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed o) {
        return 0;
    }
}
