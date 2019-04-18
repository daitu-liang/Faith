package com.nbfox.component_me.demo.ioc_annotation;

import android.content.Context;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * author : daiwenbo
 * e-mail : daiwwenb@163.com
 * date   : 2017/4/29
 * description   : xxxx描述
 */

public class InjectUtils {
    public static void inject(Context context){
        injectLayout(context);
        injectView(context);
        injectEvents(context);
    }

    private static void injectEvents(Context context) {
        Class<?> clazz=context.getClass();
        //保存函数对应的事件回调方法
        Map<String,Method> methodMap=new HashMap<>();
        //获取Activtiy里面所有的方法
        Method[] methods=clazz.getDeclaredMethods();
        //遍历
        for(Method method:methods){
            //获取该函数上的所有注解
            Annotation[] annotations=method.getAnnotations();
            //遍历该函数注解
            for (Annotation annotation:annotations){

                //获取该注释的注释类型
                Class<?> anntionType=annotation.annotationType();
                //获取注解上面的EventBase的注解
                EventBase eventBase=anntionType.getAnnotation(EventBase.class);
                //判空
                if(null==eventBase) {continue;}
                //获取EventBase注解3个函数的返回值
                // 也就是事件三要素(监听的方法,事件类型，回调函数)
                String listenerSetter = eventBase.listenerSetter();
                //事件类型 长按 还是点击
                Class<?> listenerType = eventBase.listenerType();
                //事件回调--onClick()
                String backMethod = eventBase.callBackMethod();

                //将该函数与对应的事件回调方法保存到map中
                methodMap.put(backMethod,method);
                try {
                    //(getDeclaredMethod)获取的是类自身声明的所有方法，包含public、protected和private方法
                    Method valueMethod = anntionType.getDeclaredMethod("value");
                    //获取函数注解的返回值(view id)
                    int[] viewIds = (int[]) valueMethod.invoke(annotation);
                    for(int viewId:viewIds){
                        //(getMethod)类的所有共有方法 获取findViewById函数
                        Method findViewById = clazz.getMethod("findViewById", int.class);
                        //执行findViewById得到View
                        View view = (View) findViewById.invoke(context, viewId);
                        if (null==view){continue;}
                        //反射获取view的事件监听方法（事件函数，事件类型）
                        Method setListener = view.getClass().getMethod(listenerSetter, listenerType);
                        //创建代理类对象
                        ListenerInvocationHandler handler=new ListenerInvocationHandler(context,methodMap);

                        //proxyInstance实现listenerType(事件类型)接口
                        Object proxyInstance = Proxy.newProxyInstance(anntionType.getClassLoader(),
                                new Class[]{listenerType}, handler);

                        //执行事件回调方法
                        setListener.invoke(view,proxyInstance);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注解Layout
     * @param context
     */
    private static void injectLayout(Context context) {
        int layoutId=0;
        Class<?> clazz=context.getClass();
        //获取类对象的 ContentView注解
        ContentView contentView=clazz.getAnnotation(ContentView.class);
        if (contentView!=null){
            //获取ContentView注解的返回值--->R.layout.xxx
            layoutId=contentView.valueLayout();
            try {
                //反射获取 context下的setContentView方法
                Method method=clazz.getMethod("setContentView",int.class);
                //执行该方法 ---->setContentView(layoutId)
                method.invoke(context,layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注解view
     * @param context
     */
    public  static  void injectView(Context context){
        Class<?> aClass=context.getClass();
        //获取该类下的所有成员变量
        Field[] fields=aClass.getDeclaredFields();
        for(Field field:fields){
            //遍历所有带有ViewInject注解的成员变量
            ViewInject annotation = field.getAnnotation(ViewInject.class);
            if(annotation!=null){
                //获取注解的返回值---》R.id.xxx
                int valueID = annotation.value();
                try {
                    //反射获取findViewById函数
                    Method findViewById = aClass.getMethod("findViewById", int.class);
                    //执行findViewById函数获取--->View=findViewById(R.id.xxx)
                    View view = (View) findViewById.invoke(context, valueID);
                    //允许改变该成员变量的值
                    field.setAccessible(true);
                    //给该成员变量赋值--->xxxActivity.textView=view
                    field.set(context,view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
