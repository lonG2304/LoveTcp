package com.parking.park;

import android.app.Application;

import com.parking.park.bean.EntranceBean;
import com.parking.park.bean.ExitBean;
import com.parking.park.tcp.EmCommand;
import com.parking.park.tcp.MessageReceiver;
import com.parking.park.tcp.ParkingHelper;
import com.parking.park.tcp.ParkingInfoListener;
import com.parking.park.utils.BeanConvertor;

import org.greenrobot.eventbus.EventBus;

import io.netty.channel.ChannelHandlerContext;

public class BaseApplication extends Application {

    public static BaseApplication context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

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
                EmCommand command = info.getCommand();
                String servSeq = command.getServSeq();
                switch (command) {
                    case HEART_BEAT:
                        ParkingHelper.getInstance().sendHeart();
                        break;
                    case ENTRANCE:
                        EventBus.getDefault().post(BeanConvertor.getBean(servSeq, EntranceBean.class));
                        ParkingHelper.getInstance().sendMsg(true, EmCommand.ENTRANCE);
                        break;
                    case EXIT:
                        EventBus.getDefault().post(BeanConvertor.getBean(servSeq, ExitBean.class));
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
