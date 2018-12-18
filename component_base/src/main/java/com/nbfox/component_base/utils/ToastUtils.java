package com.nbfox.component_base.utils;

import android.app.Activity;
import android.widget.Toast;

import com.nbfox.component_base.app.BaseApplicattion;


/**
 * Created by Administrator on 2017/9/9.
 */

public class ToastUtils {

    private static Toast toast,toastby;
    public  static  void showToast( String message){
        if(toast==null){// 不用getApplicationContext的话,导致内存泄露
            toast= Toast.makeText(BaseApplicattion.getBaseApplicattion(), message, Toast.LENGTH_SHORT);
            toast.show();
        }else {
            toast.setText(message);
            toast.show();
        }

    }

    public static void ToastMessage(Activity context, String messages) {
     /*   //LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
        LayoutInflater inflater = context.getLayoutInflater();//调用Activity的getLayoutInflater()
        View view = inflater.inflate(R.layout.toast_style, null); //加載layout下的布局
        TextView tvMessage = view.findViewById(R.id.tv_message);
        tvMessage.setText(messages);
        if (toastby == null) {
            toastby = new Toast(App.getContext());
            toastby.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
            toastby.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
            toastby.setView(view); //添加视图文件
            toastby.show();
        } else {
            toastby.setView(view);
            toastby.show();
        }*/

    }
}
