package com.parking.park;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parking.park.tcp.EmCommand;
import com.parking.park.tcp.MessageReceiver;
import com.parking.park.tcp.ParkingHelper;
import com.parking.park.tcp.ParkingInfoListener;

import io.netty.channel.ChannelHandlerContext;

public class ExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
    }

}
