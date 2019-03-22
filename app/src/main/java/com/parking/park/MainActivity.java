package com.parking.park;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parking.park.tcp.EmCommand;
import com.parking.park.tcp.MessageReceiver;
import com.parking.park.tcp.ParkingHelper;
import com.parking.park.tcp.ParkingInfoListener;

import io.netty.channel.ChannelHandlerContext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {
                startTCP();
            }
        }).start();
    }

    private void startTCP() {
        ParkingHelper.getInstance().start(new ParkingInfoListener() {
            @Override
            public void onChannelActive(ChannelHandlerContext ctx) {
            }

            @Override
            public void onReceiveInfo(ChannelHandlerContext ctx, MessageReceiver info) {
                switch (info.getCommand()) {
                    case HEART_BEAT:
                        ParkingHelper.getInstance().sendHeart();
                        break;
                    case ENTRANCE:
                        ParkingHelper.getInstance().sendMsg(true, EmCommand.ENTRANCE);
                        break;
                    case EXIT:
                        ParkingHelper.getInstance().sendMsg(true, EmCommand.EXIT);
                        break;
                }
            }

            @Override
            public void onChannelInactive(ChannelHandlerContext ctx) {
            }
        });


    }
}
