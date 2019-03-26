package com.nbfox.component_me.demo.refactorcode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpHelper implements IHttpProcess{
    private static volatile HttpHelper httpHelper;
    private HttpHelper(){}
    public static HttpHelper getInstance(){
        if(httpHelper==null){
            synchronized (HttpHelper.class){
                if(httpHelper==null){
                    httpHelper=new HttpHelper();
                }
            }
        }
        return httpHelper;
    }

    private static IHttpProcess iHttpProcess;

    public IHttpProcess getiHttpProcess() {
        return iHttpProcess;
    }

    public void setiHttpProcess(IHttpProcess iHttpProcess) {
        this.iHttpProcess = iHttpProcess;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {

        String finalUrl=appendParmas(url,params);
        if(iHttpProcess!=null){
            iHttpProcess.post(finalUrl,params,callBack);
        }
    }

    private String appendParmas(String url, Map<String, Object> params) {
        if(params==null||params.isEmpty()){
            return url;
        }
        StringBuilder urlBuilder=new StringBuilder(url);
        if(urlBuilder.indexOf("?")<=0){
            urlBuilder.append("?");
        }else {
            if(!urlBuilder.toString().endsWith("?")){
                urlBuilder.append("&");
            }
        }
        for(Map.Entry<String,Object> entry:params.entrySet()){
            urlBuilder.append("&"+entry.getKey()).append("=").append(decode(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }

    private static String decode(String str){

        try {
           return URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw   new RuntimeException();
        }

    }
}
