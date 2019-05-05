
package com.nbfox.component_me.demo.ioc_annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//IOC 需要单独运行 度脱离Dagger
//@ContentView(valueLayout = R.layout.ioc_activity)
public class IOCActivity extends AppCompatActivity {

    private static final String TAG = "IOCActivity";

//    @ViewInject(R.id.tv)
//    private TextView textView;
//
//    @ViewInject(R.id.button)
//    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        InjectUtils.inject(this);
//        textView.setText("kakakakka");
//
//        btn.setText("按钮");

    }


   /* @OnClick(R.id.tv)
    public void onClickTest(View view) {
        Log.e(TAG, "点击事件");
        Toast.makeText(this, "onClick(View view)", Toast.LENGTH_SHORT).show();
    }

    @OnLongClick(R.id.button)
    public boolean onLongClickTest(View view) {
        Log.e(TAG, "长按事件");
        Toast.makeText(this, "onLongClick(View view)", Toast.LENGTH_SHORT).show();
        return true;
    }*/


}

