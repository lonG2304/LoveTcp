package com.parking.park;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.parking.park.bean.ExitBean;
import com.parking.park.utils.ImageLoader;
import com.parking.park.utils.SpanStringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExitActivity extends BaseActivity {

    @BindView(R.id.car_code)
    TextView carCode;
    @BindView(R.id.tv_car_type)
    TextView mTvCarType;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_cost)
    TextView mTvCost;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.tv_tips)
    TextView mTvTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ExitBean messageEvent) {

        mTvCarType.setText(messageEvent.getClxz());
        mTvTime.setText(SpanStringUtils.getTime(messageEvent.getSc()));
        mTvCost.setText(SpanStringUtils.getTime(messageEvent.getJe()));
        carCode.setText(SpanStringUtils.getCarCode(messageEvent.getCp()));
        mTvTips.setText(messageEvent.getFjxx1());
        ImageLoader.load(this, messageEvent.getFkewm(), mIvCode);

    }

}
