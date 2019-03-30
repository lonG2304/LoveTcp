package com.parking.park.utils;

import android.widget.Toast;

import com.blankj.utilcode.util.Utils;

import static com.parking.park.Constant.IS_DEBUG;

public class MyToast {
    public static void showTestToast(String msg) {
        if (IS_DEBUG)
            Toast.makeText(Utils.getApp(), "测试信息：" + msg, Toast.LENGTH_SHORT).show();
    }
}
