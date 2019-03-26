package com.parking.park;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.parking.park.bean.ExitBean;
import com.parking.park.utils.ImageLoader;
import com.parking.park.utils.SpanStringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.parking.park.Constant.AD_SHOW_DELAY;

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
    @BindView(R.id.fl_container)
    FrameLayout fl_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        fl_container.addView(mLayout);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ExitBean messageEvent) {

        mTvCarType.setText(messageEvent.getClxz());
        mTvTime.setText(SpanStringUtils.getTime(messageEvent.getSc()));
        mTvCost.setText(SpanStringUtils.getMoney(messageEvent.getJe()));
        carCode.setText(SpanStringUtils.getCarCode(messageEvent.getCp()));
        mTvTips.setText(messageEvent.getFjxx1());
        try {
            ImageLoader.load(messageEvent.getFkewm(), mIvCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fl_container.indexOfChild(mLayout) >= 0) {
            fl_container.removeView(mLayout);
            fl_container.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fl_container.addView(mLayout);
                }
            }, AD_SHOW_DELAY);
        }

    }

}
