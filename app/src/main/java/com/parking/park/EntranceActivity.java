package com.parking.park;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.parking.park.bean.EntranceBean;
import com.parking.park.bean.OverBean;
import com.parking.park.utils.SpanStringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.parking.park.Constant.msg_over_delay;

public class EntranceActivity extends BaseActivity {

    @BindView(R.id.tv_remainder)
    TextView mTvRemainder;
    @BindView(R.id.car_code)
    TextView carCode;
    @BindView(R.id.tv_car_type)
    TextView mTvCarType;
    @BindView(R.id.tv_tips)
    TextView mTvTips;
    @BindView(R.id.fl_container)
    FrameLayout fl_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        fl_container.addView(mLayout);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EntranceBean messageEvent) {
        mTvCarType.setText(messageEvent.getClxz());
        mTvRemainder.setText(SpanStringUtils.getCarRemainder(messageEvent.getYw()));
        carCode.setText(SpanStringUtils.getCarCode(messageEvent.getCp()));
        mTvTips.setText(messageEvent.getFjxx1());
        if (fl_container.indexOfChild(mLayout) >= 0) {
            fl_container.removeView(mLayout);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(final OverBean messageEvent) {
        fl_container.setTag(messageEvent);
        fl_container.postDelayed(new Runnable() {
            @Override
            public void run() {
                Object tag = fl_container.getTag();
                if (fl_container.indexOfChild(mLayout) < 0 &&
                        messageEvent == tag)
                    fl_container.addView(mLayout);
            }
        }, msg_over_delay);
    }

}
