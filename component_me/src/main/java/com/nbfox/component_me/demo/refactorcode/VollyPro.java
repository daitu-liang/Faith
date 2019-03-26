package com.nbfox.component_me.demo.refactorcode;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class VollyPro implements IHttpProcess {
    private  RequestQueue mVollyQueue;

    public  VollyPro(Context context){
        mVollyQueue= Volley.newRequestQueue(context);
    }
    @Override
    public void post(String url, Map<String, Object> params,final ICallBack callBack) {

        StringRequest stringRequest=new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callBack.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                callBack.onFail(volleyError);
            }
        });
        mVollyQueue.add(stringRequest);
    }
}
