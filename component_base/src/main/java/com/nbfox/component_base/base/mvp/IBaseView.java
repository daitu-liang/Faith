package com.nbfox.component_base.base.mvp;

public interface IBaseView {
    /**
     * 数据为空
     * @param tag
     */
    void onEmpty(Object tag);

    /**
     * 错误数据
     * @param tag      TAG
     * @param errorMsg 错误信息
     */
    void onError(Object tag, String errorMsg);
}
