package com.nbfox.component_base.network;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpCertificateUtils
{


    public static HostnameVerifier getHostnameVerifier() {
        HostnameVerifier  do_not_verify = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        return do_not_verify;
    }


    public static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{getX509TrustManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    public static X509TrustManager getX509TrustManager(){
       return  new X509TrustManager() {
           @Override
           public void checkClientTrusted(X509Certificate[] chain, String authType) {

           }

           @Override
           public void checkServerTrusted(X509Certificate[] chain, String authType) {

           }

           @Override
           public X509Certificate[] getAcceptedIssuers() {
               return new X509Certificate[0];
           }
       } ;
    }



}
