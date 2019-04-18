package com.nbfox.component_me.demo.refactorcode;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallBack<Result> implements ICallBack {


    @Override
    public void onSuccess(String response) {
        Gson gson = new Gson();

 /*       HttpResult baseResult =gson.fromJson(response, HttpResult.class);
        if(baseResult.getCode()==200){

            Class<?> clz = analysisClassInfo(this);
            Result result = (Result) gson.fromJson(response, clz);
            onSuccess(result);
        }*/

    }

    protected Class<?> analysisClassInfo(Object object) {

        //getSuperclass()获得该类的父类
        //getGenericSuperclass()获得带有泛型的父类

        //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type genType = object.getClass().getGenericSuperclass();


        //ParameterizedType参数化类型，即泛型   (ParameterizedType) genType
        Type[] actualTypeArguments = ((ParameterizedType) genType).getActualTypeArguments();
        //获取参数化类型的数组，泛型可能有多个,如现在只有一个Result泛型 所以actualTypeArguments[0]，
        //当多少泛型的时候，这个数组就是多个
        return (Class<?>) actualTypeArguments[0];
    }

    protected abstract void onSuccess(Result result);

    @Override
    public void onFail(Throwable e) {

        onFailure(e);
    }

    protected abstract void onFailure(Throwable e);
}
