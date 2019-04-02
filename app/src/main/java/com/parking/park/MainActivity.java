package com.parking.park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.parking.park.Constant.AS_ENTER_MODE;
import static com.parking.park.Constant.MSG_OVER_DELAY;
import static com.parking.park.Constant.msg_over_delay;
import static com.parking.park.Constant.showTestMsg;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tv_enter)
    TextView mTvEnter;
    @BindView(R.id.tv_exit)
    TextView mTvExit;
    @BindView(R.id.radio_exit)
    RadioButton mRadioExit;
    @BindView(R.id.radio_enter)
    RadioButton mRadioEnter;
    @BindView(R.id.rg_select_type)
    RadioGroup mRgSelectType;
    @BindView(R.id.bt_change_test)
    CheckBox mBtChangeTest;
    @BindView(R.id.et_delay_time)
    EditText mEtDelayTime;
    @BindView(R.id.bt_set_msg_delay_time)
    Button mBtSetMsgDelayTime;
    @BindView(R.id.bt_close)
    Button bt_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mEtDelayTime.setText(msg_over_delay + "");

        mBtChangeTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showTestMsg = b;
            }
        });


        mBtSetMsgDelayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mEtDelayTime.getText().toString();
                try {
                    msg_over_delay = Integer.parseInt(s);
                    SPUtils.getInstance().put(MSG_OVER_DELAY, msg_over_delay);
                    ToastUtils.showShort("设置成功");
                } catch (Exception e) {
                    ToastUtils.showShort("请输入正确的整数");
                }
            }
        });

        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentw = new Intent(Intent.ACTION_MAIN);
                intentw.addCategory(Intent.CATEGORY_HOME);
                intentw.setClassName("android",
                        "com.android.internal.app.ResolverActivity");
                startActivity(intentw);

            }
        });

        mTvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EntranceActivity.class);
                startActivity(intent);
            }
        });
        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExitActivity.class);
                startActivity(intent);
            }
        });

        mTvVersion.setText("应用：" + AppUtils.getAppName() + "\n是否测试版本：" + BuildConfig.DEBUG + "\n版本：" + AppUtils.getAppVersionName());

        boolean isEnter = SPUtils.getInstance().getBoolean(AS_ENTER_MODE, true);
        mRgSelectType.clearCheck();
        if (isEnter) {
            mTvEnter.performClick();
            mRgSelectType.check(R.id.radio_enter);
        } else {
            mTvExit.performClick();
            mRgSelectType.check(R.id.radio_exit);
        }

        mRgSelectType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ToastUtils.showShort("设置成功，请重新启动应用");
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.radio_enter) {
                    SPUtils.getInstance().put(AS_ENTER_MODE, true);
                } else
                    SPUtils.getInstance().put(AS_ENTER_MODE, false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
