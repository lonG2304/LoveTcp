package com.parking.park;

import android.os.Bundle;
import android.widget.TextView;

import com.parking.park.bean.EntranceBean;
import com.parking.park.bean.ExitBean;
import com.parking.park.utils.SpanStringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntranceActivity extends BaseActivity {

    @BindView(R.id.tv_remainder)
    TextView mTvRemainder;
    @BindView(R.id.car_code)
    TextView carCode;
    @BindView(R.id.tv_car_type)
    TextView mTvCarType;
    @BindView(R.id.tv_tips)
    TextView mTvTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EntranceBean messageEvent) {
        mTvCarType.setText(messageEvent.getClxz());
        mTvRemainder.setText(SpanStringUtils.getCarRemainder(messageEvent.getYw()));
        carCode.setText(SpanStringUtils.getCarCode(messageEvent.getCp()));
        mTvTips.setText(messageEvent.getFjxx1());

    }

}
