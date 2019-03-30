package com.parking.park;

import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.parking.park.bean.EntranceBean;
import com.parking.park.bean.ExitBean;
import com.parking.park.bean.OverBean;
import com.parking.park.tcp.EmSend;
import com.parking.park.tcp.ParkingHelper;
import com.parking.park.tcp.ParkingInfoListener;
import com.parking.park.utils.BeanConvertor;
import com.parking.park.utils.MyToast;

import org.greenrobot.eventbus.EventBus;

import io.netty.channel.ChannelHandlerContext;

public class BaseApplication extends MultiDexApplication {

    public static BaseApplication context = null;
    public static Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Utils.init(this);

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
            public void onReceiveInfo(ChannelHandlerContext ctx, EmSend emSend, String rcvData) {

                switch (emSend) {
                    case HEART_BEAT:
                        ParkingHelper.getInstance().sendHeart();
                        break;
                    case ENTRANCE:
                        EventBus.getDefault().post(BeanConvertor.getBean(rcvData, EntranceBean.class));
                        ParkingHelper.getInstance().sendMsg(true, EmSend.ENTRANCE);
                        break;
                    case EXIT:
                        EventBus.getDefault().post(BeanConvertor.getBean(rcvData, ExitBean.class));
                        ParkingHelper.getInstance().sendMsg(true, EmSend.EXIT);
                        break;
                    case OVER:
                        EventBus.getDefault().post(new OverBean());
                        ParkingHelper.getInstance().sendMsg(true, EmSend.OVER);
                        break;

                }
            }


            @Override
            public void onChannelInactive(ChannelHandlerContext ctx) {
            }
        });


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        MyToast.showTestToast("内存太低");

    }
}
