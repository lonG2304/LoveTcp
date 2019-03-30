package com.parking.park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;

import static com.parking.park.Constant.msg_over_delay;
import static com.parking.park.Constant.showTestMsg;

public class MainActivity extends BaseActivity {

    private View enter;
    private View exit;
    private TextView tv_version;
    private CheckBox bt_change_test;
    private EditText et_delay_time;
    private Button bt_set_msg_delay_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = findViewById(R.id.tv_enter);
        exit = findViewById(R.id.tv_exit);
        tv_version = (TextView) findViewById(R.id.tv_version);
        bt_change_test = (CheckBox) findViewById(R.id.bt_change_test);
        bt_set_msg_delay_time = (Button) findViewById(R.id.bt_set_msg_delay_time);
        et_delay_time = (EditText) findViewById(R.id.et_delay_time);
        et_delay_time.setText(msg_over_delay / 1000 + "");

        bt_change_test.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showTestMsg = b;
            }
        });

        bt_set_msg_delay_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et_delay_time.getText().toString();
                try {
                    msg_over_delay = Integer.parseInt(s) * 1000;
                    ToastUtils.showShort("设置成功");
                } catch (Exception e) {
                    ToastUtils.showShort("请输入正确的整数");
                }
            }
        });


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
