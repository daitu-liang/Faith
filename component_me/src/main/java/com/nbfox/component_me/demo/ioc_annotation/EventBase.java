package com.nbfox.component_me.demo.ioc_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)//定义在注解上使用
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    String listenerSetter();//事件监听方法
    Class<?> listenerType();//事件类型
    String callBackMethod();//事件回调方法
}
