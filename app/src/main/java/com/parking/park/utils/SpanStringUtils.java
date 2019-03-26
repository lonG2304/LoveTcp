package com.parking.park.utils;

import android.graphics.Color;

import me.jamesxu.androidspan.AndroidSpan;
import me.jamesxu.androidspan.SpanOptions;

/**
 * Created by Administrator on 2019/3/23 0023.
 */
public class SpanStringUtils {


    public static CharSequence getCarCode(String cp) {
        AndroidSpan span = new AndroidSpan();
        span.drawWithOptions("车牌", new SpanOptions())
                .drawWithOptions(cp, new SpanOptions().addRelativeSize(1.1f).addStyleSpan(android.graphics.Typeface.BOLD));
        return span.getSpanText();
    }

    public static CharSequence getCarRemainder(int yw) {
        String[] chars = (yw + "").split("");
        AndroidSpan span = new AndroidSpan();
        span.drawWithOptions("余位", new SpanOptions());
        for (int i = 0; i < chars.length; i++) {
            span.drawWithOptions(chars[i] + " ", new SpanOptions().addRelativeSize(1.2f).addStyleSpan(android.graphics.Typeface.BOLD));
        }
        return span.getSpanText();
    }

    public static CharSequence getTime(String sc) {
        AndroidSpan span = new AndroidSpan();
        span.drawWithOptions("停留：", new SpanOptions())
                .drawWithOptions(sc, new SpanOptions());
        return span.getSpanText();
    }

    public static CharSequence getMoney(String sc) {
        AndroidSpan span = new AndroidSpan();
        span.drawWithOptions("收费金额：", new SpanOptions())
                .drawWithOptions(sc, new SpanOptions().addForegroundColor(Color.RED));
        return span.getSpanText();
    }

    public static CharSequence getCarCode(String cp, String clxz) {
        AndroidSpan span = new AndroidSpan();
        span.drawWithOptions(clxz + ":", new SpanOptions())
                .drawWithOptions(cp, new SpanOptions().addRelativeSize(1.1f).addForegroundColor(Color.RED)
                        .addStyleSpan(android.graphics.Typeface.BOLD));
        return span.getSpanText();
    }
}
