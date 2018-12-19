package com.nbfox.component_base.network;


import com.nbfox.component_base.app.BaseApplicattion;
import com.nbfox.component_base.utils.NetWorkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    private static class OkHttpClientBuilder {
        private static final OkHttpClient.Builder BUILDER = create();
        private static OkHttpClient.Builder create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

            if(HttpConfig.IS_CERTIFICATE){
                builder.sslSocketFactory(HttpConfig.getSSLSocketFactory(),HttpConfig.getTrustManager());
                builder.hostnameVerifier(HttpConfig.getHostnameVerifier());
            }
            if(HttpConfig.IS_CACHE){
                builder.addInterceptor(interceptor);
                builder.addNetworkInterceptor(interceptor);
                builder.cache(HttpConfig.getCache());
            }


            builder.addInterceptor(new BaseInterceptor());
            builder.readTimeout(HttpConfig.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(HttpConfig.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
            builder.connectTimeout(HttpConfig.DEFAULT_CONNECT_TIME_OUT, TimeUnit.SECONDS);
            builder.connectionPool(HttpConfig.getConnectionPoolSize());
//            builder.cookieJar()


            return builder;
        }
    }

    public static final OkHttpClient getClient() {
        return OkHttpClientBuilder.BUILDER.build();
    }


    /**
     *  网络拦截器
     *    进行网络操作的时候进行拦截
     */
    public static final Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            /**
             *  断网后是否加载本地缓存数据
             *
             */
            if (!NetWorkUtil.isNetworkConnected(BaseApplicattion.getBaseApplicattion())&&HttpConfig.IS_LOAD_DISK_CACHE) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
//            加载内存缓存数据
            else if(HttpConfig.IS_LOAD_MEMORY_CACHE)
            {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            /**
             *  加载网络数据
             */
            else
            {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            }
            Response response = chain.proceed(request);
//            有网进行内存缓存数据
            if (NetWorkUtil.isNetworkConnected(BaseApplicattion.getBaseApplicattion())&&HttpConfig.IS_MEMORY_CACHE) {
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + HttpConfig.MEMORY_CACHE_TIME)
                        .removeHeader("Pragma")
                        .build();
            } else {
//              进行本地缓存数据
                if(HttpConfig.IS_DISK_CACHE) {
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + HttpConfig.DISK_CACHE_TIME)
                            .removeHeader("Pragma")
                            .build();
                }
            }
            return response;
        }
    };
}
