package com.parking.park;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
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

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.netty.channel.ChannelHandlerContext;

public class BaseApplication extends MultiDexApplication {

    public static BaseApplication context = null;
    public static Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Utils.init(this);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionCaughter());

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


    class ExceptionCaughter implements Thread.UncaughtExceptionHandler {


        @Override
        public void uncaughtException(Thread thread, Throwable e) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent restartIntent = PendingIntent.getActivity(
                    context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            //退出程序
            AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用

            e.printStackTrace();
            File file = new File(Environment.getExternalStorageDirectory(), "Parking_LOG");
            FileUtils.createOrExistsDir(file);


            try {
                StringWriter writer = new StringWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                e.printStackTrace(printWriter);
                Throwable cause = e.getCause();
                while (cause != null) {
                    cause.printStackTrace(printWriter);
                    cause = cause.getCause();
                }
                printWriter.close();
                FileIOUtils.writeFileFromString(new File(file, formatter.format(new Date()) + ".txt"), writer.toString());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            //干掉当前的程序
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }


}
