package com.nbfox.component_me.demo.bus.eventbus;

public class ToastEvent {
    private String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public ToastEvent(String des) {
        this.des = des;
    }
}
