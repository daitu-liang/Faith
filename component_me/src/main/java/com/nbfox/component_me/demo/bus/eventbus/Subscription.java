package com.nbfox.component_me.demo.bus.eventbus;

import java.lang.reflect.Method;

public class Subscription {
    public Method method;
    public Object subscrible;
    public ThreadModeSel mode;

    public Subscription(Method method, Object subscrible, ThreadModeSel mode) {
        this.method = method;
        this.subscrible = subscrible;
        this.mode = mode;
    }
}
