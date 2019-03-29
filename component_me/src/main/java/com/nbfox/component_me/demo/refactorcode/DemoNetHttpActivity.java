package com.nbfox.component_me.demo.refactorcode;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nbfox.component_base.network.HttpConfig;
import com.nbfox.component_base.utils.Logger;
import com.nbfox.component_me.R;
import com.nbfox.component_me.demo.bus.livedatabus.LiveDataBusSelf;
import com.nbfox.component_me.demo.net.HttpClient;
import com.nbfox.component_me.demo.net.IJsonDataListener;
import com.nbfox.component_me.mvp.ResposneResult;

import java.util.LinkedHashMap;
import java.util.Map;


public class DemoNetHttpActivity extends AppCompatActivity {


    String url = HttpConfig.BASE_URL + "/super_service/api/user/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_net_http_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        HttpHelper.getInstance().setiHttpProcess(new OkhttpPro());
        HttpHelper.getInstance().setiHttpProcess(new VollyPro(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                doNetOkhttpSel();

//                doBus();
//                multiHttpInterface(view);

            }
        });
    }

    String url2 = "http://co-api.51wnl.com/calendar/vacations?token=A2E0C3CDEA081D3BFC34F8FE23A15886&type=1&timestamp=1462377600&client=ceshi";

    private void doNetOkhttpSel() {
        HttpClient.sendRequstData(url, null, ResposneResult.class, new IJsonDataListener<ResposneResult>() {
            @Override
            public void onSuccess(ResposneResult clazz) {
                Logger.getLogger("DemoNetHttpActivity").i("ResposneResult", "网络响应回调成功=" + clazz.getStatus());


            }

            @Override
            public void onFailure() {

            }
        });
    }

    private void doBus() {


 /*       ResposneResult result = new ResposneResult();
        result.setCode(3888);
        result.setMsg("kakaxi");
        RxBus.getInstance().post(result);*/


        //google 的 LiveData
//      LiveDataBus.get().getChannel("kakaxi").setValue("鸣人最近心情不美丽");


        //自己写的LiveData
        LiveDataBusSelf.get().getChannel("test_sel").setValue("雏田鸣人结婚了");

        LiveDataBusSelf.get().getChannel("test_sel").setValue("搬家有一种难以描述的滋味");
    }

    private void multiHttpInterface(final View view) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("userName", "liang1");
        map.put("password", "123456");
        map.put("deviceType", "1");
        map.put("deviceNo", "13065ffa4e59a53cd05");
        HttpHelper.getInstance().post(url, map, new HttpCallBack<ResposneResult>() {
            @Override
            protected void onSuccess(ResposneResult apple) {
                Logger.getLogger("DemoNetHttpActivity").i("ResposneResult", "网络响应回调成功=" + apple.getCode());
                Snackbar.make(view, "网络响应回调成功=" + apple.getCode(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            protected void onFailure(Throwable e) {

            }
        });
    }

}
