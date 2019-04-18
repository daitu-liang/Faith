package com.nbfox.component_me.demo.ioc_annotation;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class ListenerInvocationHandler implements InvocationHandler {

    public ListenerInvocationHandler(Context context, Map<String, Method> methodMap) {
        this.context = context;
        this.methodMap = methodMap;
    }

    private Context context;
    private Map<String,Method> methodMap;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName=method.getName();//获取绑架事件监听的函数
        Method methodListener=methodMap.get(methodName);//根据函数名获取事件监听方法
        if(methodListener!=null){
            return methodListener.invoke(context,args);
        }else {
            return method.invoke(proxy,args);
        }
    }
}
