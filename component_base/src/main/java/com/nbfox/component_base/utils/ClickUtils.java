package com.nbfox.component_base.utils;

import android.os.SystemClock;


public class ClickUtils {
    private static final String TAG = ClickUtils.class.getSimpleName();
    private static long lastClickTime = 0L;
    /**
     * 用于处理频繁点击问题, 如果两次点击小于500毫秒则不予以响应
     * @return true:是连续的快速点击
     */
    public static boolean isFastDoubleClick() {
        //从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        long nowTime = SystemClock.elapsedRealtime();
        if ((nowTime - lastClickTime) < 5000) {
            return true;
        } else {
            lastClickTime = nowTime;
            return false;
        }
    }
}
