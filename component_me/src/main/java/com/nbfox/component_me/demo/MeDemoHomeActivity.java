package com.nbfox.component_me.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.nbfox.component_base.constants.ARouterConfig;
import com.nbfox.component_me.R;
import com.nbfox.component_me.demo.annotation_test.Colunm;
import com.nbfox.component_me.demo.annotation_test.Person;
import com.nbfox.component_me.demo.annotation_test.Table;
import com.nbfox.component_me.demo.bus.eventbus.IMsg;
import com.nbfox.component_me.demo.bus.livedatabus.LiveDataBus;
import com.nbfox.component_me.demo.bus.rxbus.RxBus;
import com.nbfox.component_me.demo.design_mode.chain_mode.FaceFilter;
import com.nbfox.component_me.demo.design_mode.chain_mode.FilterChain;
import com.nbfox.component_me.demo.design_mode.chain_mode.HTMLFilter;
import com.nbfox.component_me.demo.design_mode.chain_mode.Request;
import com.nbfox.component_me.demo.design_mode.chain_mode.Response;
import com.nbfox.component_me.demo.design_mode.chain_mode.SensitiveFilter;
import com.nbfox.component_me.demo.ioc_annotation.IOCActivity;
import com.nbfox.component_me.demo.refactorcode.DemoBusActivity;
import com.nbfox.component_me.demo.refactorcode.DemoNetHttpActivity;
import com.nbfox.component_me.mvp.ResposneResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeDemoHomeActivity.this, DemoNetHttpActivity.class));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeDemoHomeActivity.this, DemoBusActivity.class));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doTestDesginMode_chain();
                //doTestAnnotation();
                doTestAnnotationReflect();
            }
        });
        testBus();
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeDemoHomeActivity.this, IOCActivity.class));

            }
        });
    }

    private void testBus() {
        //RxBus实现 消息总线
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

        //LiveDataBus显示消息总线
        LiveDataBus.get().getChannel("kakaxi", String.class)
                .observe(this, new android.arch.lifecycle.Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Log.i("nbfox", "onChanged=" + s);
                        tv.setText("info=" + s);
                    }
                });
    }

    private void doTestAnnotationReflect() {

        try {
            Class<IMsg> claz = (Class<IMsg>) Class.forName("com.nbfox.component_me.demo.bus.eventbus.IMsg");
//            Constructor<IMsg> cons = claz.getConstructor();
//            IMsg obj = (IMsg) cons.newInstance();
            IMsg obj = (IMsg)  claz.newInstance();
            Method method = claz.getMethod("doIMsg");
            method.invoke(obj,new Object[]{"我就反射了怎么了"});
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static String query(Person person) {
        StringBuilder sb = new StringBuilder();
        Class pclaz = person.getClass();

        boolean existP = pclaz.isAnnotationPresent(Table.class);
        if (!existP) {
            return null;
        }

        Table table = (Table) pclaz.getAnnotation(Table.class);
        String tabName = table.value();

        sb.append("select * from ").append(tabName).append("where pwd=123456 ");
        Field[] fiedAar = pclaz.getDeclaredFields();
        for (Field field : fiedAar) {
            boolean fieldExist = field.isAnnotationPresent(Colunm.class);
            if (!fieldExist) {
                return null;
            }
            Colunm colunm = field.getAnnotation(Colunm.class);
            String colunmName = colunm.value();
            String fieldName = field.getName();
            Object fieldValue = null;

            String str1 = fieldName.substring(0, 1);
            String str2 = fieldName.substring(1);
            String getMethodName = "get" + str1.toUpperCase() + str2;

            try {
                Method method = pclaz.getMethod(getMethodName);
                fieldValue = method.invoke(person);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.append(" and ").append(colunmName).append("=").append(fieldValue);
        }
        return sb.toString();
    }

    /**
     * 注解测试
     */
    private void doTestAnnotation() {
        Person person = new Person();
        person.setName("kakaxi");
        person.setPwd("zileiye");

        String str = query(person);
        Log.i("nbfox", "str=" + str);
    }

    /**
     * 责任链模式
     */
    private void doTestDesginMode_chain() {
        //设定过滤规则，对msg字符串进行过滤处理
        String msg = ":):,<script>,敏感,被就业,网络授课";
        //过滤请求
        Request request = new Request();
        //set方法，将待处理字符串传递进去
        request.setRequest(msg);
        //处理过程结束，给出的响应
        Response response = new Response();
        //设置响应信息
        response.setResponse("response:");
        //FilterChain,过滤规则形成的拦截链条
        FilterChain fc = new FilterChain();
        //规则链条添加过滤规则，采用的是链式调用
        fc.addFilter(new HTMLFilter())
                .addFilter(new SensitiveFilter())
                .addFilter(new FaceFilter());
        //按照FilterChain的规则顺序，依次应用过滤规则
        fc.doFilter(request, response, fc);
        //打印请求信息
        Log.i("nbfox", "onChanged=" + request.getRequest());

        //打印响应信息
        Log.i("nbfox", "onChanged=" + response.getResponse());

        /*
         * 处理器对数据进行处理
         * --|----|---数据--|-----|---
         * 规则1      规则2                 规则3       规则4
         */
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();//避免内存泄露
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
