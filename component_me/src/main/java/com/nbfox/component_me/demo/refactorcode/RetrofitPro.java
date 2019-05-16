package com.nbfox.component_me.demo.refactorcode;

import android.content.Context;

import com.nbfox.component_base.network.ApiSubscriber;
import com.nbfox.component_base.network.RetrofitClient;
import com.nbfox.component_me.net.ApiUser;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;

public class RetrofitPro implements IHttpProcess {


    public RetrofitPro(Context context){

    }
    @Override
    public void post(String url, Map<String, Object> params,final ICallBack callBack) {

        RetrofitClient.getInstance()
                .builder(ApiUser.class)
                .getLoginTest(params)
                .subscribe(new ApiSubscriber<ResponseBody>() {
                    @Override
                    protected void onSuccess(ResponseBody bean) {
                        try {
                            callBack.onSuccess(bean.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void onErrorMsg(Throwable e) {
                        callBack.onFail(e);
                    }
                });

    }
}
