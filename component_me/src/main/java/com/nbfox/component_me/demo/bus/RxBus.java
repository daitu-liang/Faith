package com.nbfox.component_me.demo.bus;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;



/**
 * //rxjava2
 * 首先Subject既可以作为被观察者发送事件，也可以作为观察者接收事件，而RxJava内部的响应式的支持实现了事件总线的功能。
 * 可以使用PublishSubject.create().toSerialized();生成一个Subject对象
 */
public class RxBus {
    private final Subject<Object> bus;

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    private static class SingletonHolder {
        private static final RxBus defaultRxBus = new RxBus();
    }

    public static RxBus getInstance() {
        return SingletonHolder.defaultRxBus;
    }

    public void post(Object object) {
        if (bus != null) {
            bus.onNext(object);
        }
    }

    public <T> Observable<T> tObservable(Class<T> tClass) {
        return bus.ofType(tClass);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public boolean hasObservers(){
        return bus.hasObservers();
    }
}
