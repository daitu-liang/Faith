package com.nbfox.component_me.demo.bus.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nbfox.component_me.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    private TextView tv, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tb);
        tv2 = (TextView) findViewById(R.id.tb2);
        tv3 = (TextView) findViewById(R.id.tb3);
     /*   EventManager.getInstance().addMsg(new EventMsg<UserInfo>("kakaxi"){

            @Override
            public void function(UserInfo userInfo) {

                Log.i("==MainActivity","输出"+userInfo.toString());
            }

        });*/
//        EventManager.getInstance().addMsg(new EventMsg2<UserInfo,UserInfo>("kakaxi"){
//
//            @Override
//            public UserInfo function(UserInfo userInfo) {
//
//                Log.i("==MainActivity","参数输出"+userInfo.toString());
//                return new UserInfo("返回值类型来源","返回值pwd");
//            }
//
//        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        Log.i("==MainActivity", "create注册");
//        EventManager.getInstance().register(this);
//        EventBus.getDefault().register(this);
    }


    public void onEvent(ToastEvent event) {
        Log.d("==MainActivity", "onEvent=: " + Thread.currentThread().getName());
        tv.setText("onEvent===" + event.getDes());
    }

    public void onEventMainThread(ToastEvent event) {
        Toast.makeText(this, "Haha on MainThread", Toast.LENGTH_SHORT).show();
        Log.d("==MainActivity", "onEventMainThread: " + Thread.currentThread().getName());
        tv.setText("onEventMainThread=" + event.getDes());
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false,priority = 12)
    public void onMessageTv1(ToastEvent toastEvent) {
        Log.d("==MainActivity", "onMessageTv1: " );
        tv.setText("1=" + toastEvent.getDes());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageTv2(ToastEvent toastEvent) {
        Log.d("==MainActivity", "onMessageTv2: " + toastEvent.getDes());

        tv2.setText("2=" + toastEvent.getDes());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageTv3(UserInfo toastEvent) {
//        tv2.setText("3=" + toastEvent.getDes());
        Log.d("==MainActivity", "MainActivity-UserInfo: " + toastEvent.toString() + " thread name=" + Thread.currentThread().getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.i("==MainActivity", "onStart反注册");
//        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("==MainActivity", "onDestroy反注册");
//        EventBus.getDefault().unregister(this);
//        EventManager.getInstance().unregister(this);
    }
}
