package com.parking.park;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.parking.park.bean.ExitBean;
import com.parking.park.bean.OverBean;
import com.parking.park.utils.SpanStringUtils;


import net.glxn.qrgen.android.QRCode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.parking.park.Constant.msg_over_delay;

public class ExitActivity extends BaseActivity {

    @BindView(R.id.tv_car_type)
    TextView mTvCarType;
    @BindView(R.id.tv_cost_time)
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
        mIvCode.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = mIvCode.getLayoutParams();
                layoutParams.height = mIvCode.getWidth();
                mIvCode.setLayoutParams(layoutParams);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ExitBean messageEvent) {
        mTvCarType.setText(SpanStringUtils.getCarCode(messageEvent.getCp(), messageEvent.getClxz()));
        mTvTime.setText(SpanStringUtils.getTime(messageEvent.getSc()));
        mTvCost.setText(SpanStringUtils.getMoney(messageEvent.getJe()));
        mTvTips.setText(messageEvent.getFjxx1());
        try {
            Bitmap myBitmap = QRCode.from(messageEvent.getFkewm()).bitmap();
            mIvCode.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mIvCode.setImageBitmap(myBitmap);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort("获取付款码失败");
        }
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
        }, msg_over_delay * 1000);
    }

}
