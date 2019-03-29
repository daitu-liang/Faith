package com.nbfox.component_me.demo.net;

import com.nbfox.component_base.utils.Logger;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonHttpRequest implements IhttpRequest {
    private String url;
    private byte[] data;
    private CallBackListener callBackListener;
    private HttpURLConnection httpURLConnection;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    @Override
    public void execute() {
        Logger.getLogger("JsonHttpRequest").i(" execute()", "HttpURLConnection--execute()");

        URL url = null;
        try {
            url = new URL(this.url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(3000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);


            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.connect();

            //使用字节流发送数据
            OutputStream out = httpURLConnection.getOutputStream();

            BufferedOutputStream bos = new BufferedOutputStream(out);
            bos.write(data);

            bos.flush();
            out.close();
            bos.close();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = httpURLConnection.getInputStream();
                if (callBackListener != null) {
                    Logger.getLogger("JsonHttpRequest").i(" execute()", "callBackListener回调callBackListener.onSuccess(in)");
                    callBackListener.onSuccess(in);
                }
            }else {
                throw new RuntimeException("请求失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("请求失败");
        }finally {
            httpURLConnection.disconnect();
        }


    }
}
