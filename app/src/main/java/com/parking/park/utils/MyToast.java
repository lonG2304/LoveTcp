package com.parking.park.utils;

import android.widget.Toast;

import com.blankj.utilcode.util.Utils;

import static com.parking.park.BaseApplication.mainHandler;
import static com.parking.park.Constant.showTestMsg;

public class MyToast {
    public static void showTestToast(final String msg) {
        if (showTestMsg) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Utils.getApp(), "测试信息：" + msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
