package com.nbfox.component_me.demo.refactorcode;

public interface ICallBack {

    void onSuccess(String response);

    void onFail(Throwable e);
}
/**
 *
 * 网络接口的封装架构，通过代理模式，来支持不同网络框架的的切换 ，降低耦合度，开发成本
 * HttpHelper 代理类  OkhttpPro被代理类采用okhttp实现网络请求 （volley  okhttp等）
 *
 * 在Applicattion中 初始化 被代理者=何种网络框架
 * HttpHelper.getInstance().setiHttpProcess(new OkhttpPro());
 *
 * 抽象类HttpCallBack实现ICallBack网络回调接口，做响应数据的统一处理
 * 采用反射获取类的参数类型 即泛型，
 *
 *
 */
