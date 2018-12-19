package com.nbfox.component_base.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.nbfox.component_base.utils.Logger;
import com.nbfox.component_base.utils.ToastUtils;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by leixiaoliang on 2017/1/6.
 */
public abstract class ApiSubscriber<T> extends DisposableObserver<T> {
    private String dialogTip;
    private Logger log = Logger.getLogger("ApiSubscriber");


    private boolean showDialog = false;
    private Context context;
    private ProgressDialog pd;

    private boolean cancelable;

    public ApiSubscriber() {
        showDialog = false;
    }

    public ApiSubscriber(Context context) {
        this.context = context;
        showDialog = true;
    }

    public ApiSubscriber(Context context, String dialogTip) {
        this.context = context;
        showDialog = true;
        this.dialogTip = dialogTip;
    }

    private boolean toastTip = true;

    public ApiSubscriber(Context context, boolean isShowDialog, boolean toastTip) {
        this.context = context;
        this.showDialog = isShowDialog;
        this.toastTip = toastTip;

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (showDialog) {
            initProgressDialog();
        }

    }

    @Override
    public void onNext(T t) {
//        log.e("", "onNext-ApiSubscriber=" + this.isDisposed());
        onSuccess(t);

    }

    protected abstract void onSuccess(T bean);

    protected abstract void onErrorMsg(Throwable e);

    @Override
    public void onError(Throwable e) {
        log.e("", "onError---ApiSubscriber-请求出错=" + e.getMessage());
       /* if (ConstUtils.logoutMsg(e)) {//209失效
            Activity isTopStack = ScreenManager.getScreenManager().getShownActivity();
            if(isTopStack!=null){
                ToastUtils.showToast(e.getMessage());
                UserUtil.logout(isTopStack);
                isTopStack.startActivity(LoginActivity.getIntent(isTopStack));
                isTopStack.finish();
            }
        } else */
        if (e instanceof SocketTimeoutException) {
            ToastUtils.showToast("请求超时");
        } else if ((e instanceof ConnectException)
                || (e instanceof UnknownHostException)
                || (e instanceof SocketException)
                || (e instanceof EOFException)
                ) {
            ToastUtils.showToast("连接失败");
        }
        ToastUtils.showToast(e.getMessage());
        dismissProgressDialog();
        onErrorMsg(e);
        doCanlSubscribe();
    }

    @Override
    public void onComplete() {
        if (showDialog) {
            dismissProgressDialog();
        }
        doCanlSubscribe();
    }

    /**
     * 断开上下流，解绑
     */
    public void doCanlSubscribe() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    private void initProgressDialog() {
        if (pd == null && context != null) {
            pd = new ProgressDialog(context);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            if (TextUtils.isEmpty(dialogTip)) {
                pd.setMessage("   正在加载....");
            } else {
                pd.setMessage(dialogTip);
            }

            pd.setCancelable(cancelable);// 设置是否可以通过点击Back键取消
            pd.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                    }
                });
            }

            if (!pd.isShowing()) {
                if (TextUtils.isEmpty(dialogTip)) {
                    pd.setMessage("   正在加载....");
                } else {
                    pd.setMessage(dialogTip);
                }
                pd.show();
            }
        }


    }

    private void dismissProgressDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

}