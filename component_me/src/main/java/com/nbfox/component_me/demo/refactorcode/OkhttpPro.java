package com.nbfox.component_me.demo.refactorcode;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpPro implements IHttpProcess {

    private OkHttpClient mOkHttpClient;

    private Handler handle;

    public OkhttpPro() {
        mOkHttpClient = new OkHttpClient();

        handle = new Handler();
    }

    @Override
    public void post(String url, final Map<String, Object> params, final ICallBack callBack) {
        //请求body
/*        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "json");
       //请求header的添加
        Headers headers = new Headers.Builder().add("test", "12")
                .add("test1", "456").build();
        //请求组合创建
        Request request = new Request.Builder()
                .url("https://free-api.heweather.com/s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116")
                .post(body)
                .headers(headers)
                .build();*/
        RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseStr = response.body().string();
                    if (handle != null && callBack != null) {
                        handle.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(responseStr);
                            }
                        });
                    }
                }
            }
        });
    }

    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return builder.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue().toString());
        }
        return builder.build();
    }
}
