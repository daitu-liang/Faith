package com.nbfox.component_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.TextView;

import com.nbfox.component_base.R;
import com.nbfox.component_base.utils.ScreenManager;


public abstract class BaseActivity extends AppCompatActivity {

    private ViewStub emptyView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this, true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (isNeedActionBar()) {
            setContentView(R.layout.activity_base);
            ((ViewGroup) findViewById(R.id.fl_content)).addView(getLayoutInflater().inflate(getLayoutId(), null));
        } else {
            setContentView(getLayoutId());
        }
    }

    private boolean isNeedActionBar() {
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        //将Activity从管理器移除
        ScreenManager.getScreenManager().popActivity(this);

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }


    protected void showEmptyView(String text) {
        showEmptyOrErrorView(text, R.drawable.base_page_ic_empty);
    }

    protected void showEmptyView() {
        showEmptyView(getString(R.string.base_no_data));
    }

    protected void showErrorView(String text) {
        showEmptyOrErrorView(text, R.drawable.base_ic_network);
    }

    protected void showErrorView() {
        showErrorView(getString(R.string.base_error_data));
    }

    public void showEmptyOrErrorView(String text, int img) {

        if (emptyView == null) {
            emptyView = findViewById(R.id.vs_empty);
        }
        emptyView.setVisibility(View.VISIBLE);
        findViewById(R.id.iv_empty).setBackgroundResource(img);
        ((TextView) findViewById(R.id.tv_empty)).setText(text);
        findViewById(R.id.ll_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPageClick();
            }
        });
    }

    protected void hideEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    /**
     * 空页面被点击
     */
    protected void onPageClick() {

    }


    protected abstract int getLayoutId();

    protected abstract void initView();

}
