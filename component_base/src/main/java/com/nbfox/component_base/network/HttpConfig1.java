package com.nbfox.component_base.network;


import com.google.gson.Gson;
import com.nbfox.component_base.app.BaseApplicattion;

import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.ResponseBody;


public class HttpConfig1 {
    //是否开始日志
    public static final boolean DEBUG = true;

    // Base地址


    //    //------------测试环境------------
    //测试环境-主模块
    public static final String BASE_URL = "";



    /**
     * 响应的返回key
     */
    public class Code {

        public static final String MSG = "msg";
        public static final String CODE = "code";
        public static final String DATA = "data";
    }

    //------------------------------------------------------------------------------
    public static final int DEFAULT_CONNECT_TIME_OUT = 16;
    public static final int DEFAULT_READ_TIMEOUT = 16;
    public static final int DEFAULT_WRITE_TIMEOUT = 16;
    //     设置超时时间
    private int connectTimeout;


    public static boolean IS_CERTIFICATE = true;

    //    是否进行磁盘缓存
    public static boolean IS_CACHE = false;

    //是否加载本地缓存数据 默认为TRUE
    public static boolean IS_LOAD_DISK_CACHE = true;

    //针对有网络情况是否加载内存缓存数据默认为False
    public static boolean IS_LOAD_MEMORY_CACHE = false;


    public static boolean IS_DISK_CACHE = false;
    //    是否进行内存缓存
    public static boolean IS_MEMORY_CACHE = false;
    //    内存缓存时间单位S （默认为60s）
    public static int MEMORY_CACHE_TIME = 60;

    //    本地缓存时间单位S (默认为4周)
    public static int DISK_CACHE_TIME = 60 * 60 * 24 * 28;

    //    缓存本地大小 单位字节（默认为30M）
    private static int MAX_DISK_CACHE_SIZE = 30 * 1024 * 1024;


    //    设置HttpS客户端带证书访问
    private InputStream[] certificates;


    //      缓存路径
    public static Cache getCache() {
        return new Cache(new File(BaseApplicattion.getBaseApplicattion().getCacheDir(), "network_zone"), MAX_DISK_CACHE_SIZE);
    }

    //    设置网络最大连接数
    public static ConnectionPool getConnectionPoolSize() {
        return new ConnectionPool(10, 20, TimeUnit.SECONDS);
    }


    public static SSLSocketFactory getSSLSocketFactory() {
        return HttpCertificateUtils.createSSLSocketFactory();
    }

    public static X509TrustManager getTrustManager() {
        return HttpCertificateUtils.getX509TrustManager();
    }

    public static HostnameVerifier getHostnameVerifier() {
        return HttpCertificateUtils.getHostnameVerifier();
    }

    /**
     * rxjava进行线程切换复用，以及数据流转化
     *
     * @param <T>
     * @return
     */

    protected static int code = -1;
    protected static String msg = "";
    protected static String dataStr = "";
    public static <T> ObservableTransformer<ResponseBody, T > toTransformer() {
        return new ObservableTransformer<ResponseBody, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseBody> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new Function<ResponseBody, T>() {//在这里把HttpResult<T>类型处理，返回T类型
                            @Override
                            public T apply(@NonNull ResponseBody responseBody) {
                                  T dataResponse=null;
                                try {
                                    if (responseBody != null) {
                                        String response = responseBody.string();
//                                        String response = new String(responseBody.bytes());
                                        JSONObject jsonObject = new JSONObject(response);
                                        code = jsonObject.optInt(Code.CODE);
                                        msg = jsonObject.optString(Code.MSG);
                                        dataStr = jsonObject.opt(Code.DATA).toString();
                                    }
                                    Class<T> classOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                                    if (classOfT == String.class) {
                                        dataResponse = (T) dataStr;
                                    } else {
                                        //对象
                                        dataResponse = (new Gson()).fromJson(dataStr, classOfT);
                                    }

                                   /* if (dataStr.charAt(0) == '{') {
                                        //获取泛型类型
                                        Class<T> classOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                                        if (classOfT == String.class) {
                                            dataResponse = (T) dataStr;
                                        } else {
                                            //对象
                                            dataResponse = (new Gson()).fromJson(dataStr, classOfT);
                                        }
                                    } else if (dataStr.charAt(0) == '[') {
                                        //数组
                                        Type collectionType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                                        dataResponse = new Gson().fromJson(dataStr, collectionType);
                                    }*/
                                    return dataResponse;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return dataResponse;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
