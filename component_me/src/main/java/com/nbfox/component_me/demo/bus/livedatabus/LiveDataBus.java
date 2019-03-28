package com.nbfox.component_me.demo.bus.livedatabus;

import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * UI和实时数据保持一致，因为LiveData采用的是观察者模式，这样一来就可以在数据发生改变时获得通知，更新UI。
 *
 * 避免内存泄漏，观察者被绑定到组件的生命周期上，当被绑定的组件销毁（destroy）时，观察者会立刻自动清理自身的数据。
 *
 * 不会再产生由于Activity处于stop状态而引起的崩溃，例如：当Activity处于后台状态时，是不会收到LiveData的任何事件的。
 *
 * 不需要再解决生命周期带来的问题，LiveData可以感知被绑定的组件的生命周期，只有在活跃状态才会通知数据变化。
 *
 * 实时数据刷新，当组件处于活跃状态或者从不活跃状态到活跃状态时总是能收到最新的数据。
 *
 * 解决Configuration Change问题，在屏幕发生旋转或者被回收再次启动，立刻就能收到最新的数据。
 *
 */
public class LiveDataBus {
    private Map<String, MutableLiveData<Object>> bus;
    private LiveDataBus() {
        bus = new HashMap<>();
    }

    private static class LiveDataBusInstance {
        private static final LiveDataBus LIVE_DATA_BUS = new LiveDataBus();
    }

    public static LiveDataBus get() {
        return LiveDataBusInstance.LIVE_DATA_BUS;
    }


    public <T> MutableLiveData<T> getChannel(String target, Class<T> type) {
        if (!bus.containsKey(target)) {
            bus.put(target, new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) bus.get(target);
    }

    public MutableLiveData<Object> getChannel(String target){
        return getChannel(target,Object.class);
    }

}
