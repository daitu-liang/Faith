package com.nbfox.component_me.demo.bus.livedatabus;

public abstract class Observer<T> {


    //活跃状态  只有在活跃状态下  才更新
    static final int SATE_ACTIVE = 1;

    //暂停状态
    static final int SATE_ONPAUSE = 2;

    //销毁化状态
    static final int SATE_DESTROY = 3;

    //初始化状态
    static final int SATE_INIT = 0;


    private int state=SATE_INIT;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //当前组件状态
    public abstract void onChanged(T t);
}
