package com.nbfox.component_me.demo.bus.eventbus;

public abstract class EventMsg2<T,P> extends IMsg {

    public EventMsg2(String name) {
        super(name);
    }
    public  abstract  T function(P p);
}
