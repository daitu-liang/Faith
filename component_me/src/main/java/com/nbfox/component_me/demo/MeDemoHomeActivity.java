package com.nbfox.component_me.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.constants.ARouterConfig;
import com.nbfox.component_me.R;
import com.nbfox.component_me.demo.bus.RxBus;
import com.nbfox.component_me.demo.refactorcode.DemoNetHttpActivity;
import com.nbfox.component_me.mvp.ResposneResult;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = ARouterConfig.AROUTER_ME_DEMO)
public class MeDemoHomeActivity extends AppCompatActivity {

    TextView tv;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_demo_home_activity);
        ARouter.getInstance().inject(this);
        tv = (TextView) findViewById(R.id.tv);
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeDemoHomeActivity.this, DemoNetHttpActivity.class));
            }
        });
        compositeDisposable = new CompositeDisposable();
        RxBus.getInstance()
                .tObservable(ResposneResult.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResposneResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResposneResult resposneResult) {
                        Log.i("nbfox", "onNext=" + resposneResult.getMsg());
                        tv.setText("info=" + resposneResult.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();//避免内存泄露
        if(compositeDisposable!=null&&!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
