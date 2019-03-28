package com.nbfox.component_me.demo.refactorcode;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nbfox.component_base.utils.ToastUtils;
import com.nbfox.component_me.R;
import com.nbfox.component_me.demo.bus.livedatabus.LiveDataBusSelf;
import com.nbfox.component_me.demo.bus.livedatabus.Observer;
import com.nbfox.component_me.demo.bus.viewmodel.LiveDataTimeViewModel;

public class DemoBusActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_bus_activity);
        Button btn1 = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DemoBusActivity.this, DemoNetHttpActivity.class));
            }
        });

        LiveDataBusSelf.get().getChannel("test_sel", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        tv.setText(s);
                        Log.i("nbfox", "onChanged=" + s);

                        ToastUtils.showToast("自定义的livedata");
                    }
                });

        viewModeTime();
    }

    private void viewModeTime() {
        LiveDataTimeViewModel vm = ViewModelProviders.of(this).get(LiveDataTimeViewModel.class);
        vm.getmTime().observe(this, new android.arch.lifecycle.Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tv.setText(s);
            }
        });
    }
}
