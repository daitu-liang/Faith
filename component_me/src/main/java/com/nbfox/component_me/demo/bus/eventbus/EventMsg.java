package com.nbfox.component_me.demo.bus.eventbus;

public abstract class EventMsg<P> extends IMsg {

    public EventMsg(String name) {
        super(name);
    }
    public  abstract  void function(P p);
}
