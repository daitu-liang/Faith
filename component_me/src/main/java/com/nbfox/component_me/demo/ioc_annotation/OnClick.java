package com.nbfox.component_me.demo.ioc_annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerSetter = "setOnClickListener",
        listenerType =  View.OnClickListener.class,
        callBackMethod = "onClick")
public @interface OnClick {
    int [] value();
}
