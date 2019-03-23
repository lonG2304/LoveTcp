package com.parking.park.utils;

import com.google.gson.Gson;
import com.parking.park.bean.BaseBean;

/**
 * Created by Administrator on 2019/3/23 0023.
 */
public class BeanConvertor {
    private static Gson mGson = new Gson();

    public static <T extends BaseBean> T getBean(String data, Class<T> clz) {
        try {
            return  mGson.fromJson(data, clz);
        } catch (Exception e) {
            return null;
        }
    }


}
