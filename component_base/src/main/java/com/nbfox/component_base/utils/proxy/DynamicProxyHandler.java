package com.nbfox.component_base.utils.proxy;

import android.util.Log;

import com.nbfox.component_base.constants.BaseConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DynamicProxyHandler implements InvocationHandler {
    private Object obj;

    public DynamicProxyHandler(Object obj) {
        Log.i(BaseConfig.BASE_TAG, "----DynamicProxyHandler构造---->>>>" + obj.getClass());
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i(BaseConfig.BASE_TAG, "----DynamicProxyHandler--invoke---->>>>"
                + "\nproxy=" + proxy.getClass()
                + "\nmethod=" + method.getName()
                + "\nargs=" + Arrays.toString(args)
        );
        Object invokeObject = method.invoke(obj, args);
        Log.i(BaseConfig.BASE_TAG, "--1-->DynamicProxyHandler--invoke->>\ninvokeObject="+invokeObject);

        if(invokeObject!=null){
            Log.i(BaseConfig.BASE_TAG, "---->DynamicProxyHandler--invoke->>\ninvokeObject="+invokeObject.getClass());

        }else{
            Log.i(BaseConfig.BASE_TAG, "--2-->DynamicProxyHandler--invoke->>\ninvokeObject="+invokeObject);

        }
        return invokeObject;
    }
}
