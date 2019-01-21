package com.nbfox.component_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nbfox.component_base.base.mvp.BasePresenter;
import com.nbfox.component_base.base.mvp.IBaseView;


public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建present

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

    }


    @Override
    public void onEmpty(Object tag) {

    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }


    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();
}
