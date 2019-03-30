package com.parking.park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;

public class MainActivity extends BaseActivity {

    private View enter;
    private View exit;
    private TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = findViewById(R.id.tv_enter);
        exit = findViewById(R.id.tv_exit);
        tv_version = (TextView) findViewById(R.id.tv_version);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EntranceActivity.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExitActivity.class);
                startActivity(intent);
            }
        });

        tv_version.setText("应用：" + AppUtils.getAppName() + "\n是否测试版本：" + BuildConfig.DEBUG + "\n版本：" + AppUtils.getAppVersionName());
    }

}
