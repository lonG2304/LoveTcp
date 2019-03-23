package com.parking.park;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parking.park.bean.ExitBean;
import com.parking.park.tcp.EmCommand;
import com.parking.park.tcp.MessageReceiver;
import com.parking.park.tcp.ParkingHelper;
import com.parking.park.tcp.ParkingInfoListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.netty.channel.ChannelHandlerContext;

public class ExitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ExitBean messageEvent) {
    }

}
