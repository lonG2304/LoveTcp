package com.parking.park;

import com.blankj.utilcode.util.SPUtils;

public class Constant {

    public static String TOKEN = "";
    public static String LOCAL_CONNECTION_IP = "";


    public static int PORT = 9999;

    public static final String AS_ENTER_MODE = "AS_ENTER_MODE";
    public static final String MSG_OVER_DELAY = "msg_over_delay";

    public static final long AD_LOOP_INTERVAL = 3000l;
    public static long msg_over_delay = SPUtils.getInstance().getLong(MSG_OVER_DELAY, 3);
    ;
    public static boolean showTestMsg = false;
//    public static final boolean showTestMsg = BuildConfig.DEBUG;


}
