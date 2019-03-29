package com.nbfox.component_me.demo.net;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class JsonCallBackListener<T> implements CallBackListener {
    private Class<T> responseClass;
    private IJsonDataListener mJsonDataListener;

    Handler handler = new Handler(Looper.getMainLooper());

    public  JsonCallBackListener(Class<T> responseClass, IJsonDataListener jsonDataListener) {
        this.mJsonDataListener = jsonDataListener;
        this.responseClass = responseClass;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String response = getContent(inputStream);
        final T clazz = JSON.parseObject(response, responseClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                mJsonDataListener.onSuccess(clazz);
            }
        });
    }

    @Override
    public void onFailure() {

    }

    private String getContent(InputStream inputStream) {
        {
            String str = "";
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuffer sb = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    sb.append(str).append("\n");
                }
                return sb.toString();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;


        }

    }
}