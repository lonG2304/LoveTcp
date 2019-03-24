package com.parking.park.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2019/3/23 0023.
 */
public class ImageLoader {
    public static void load(Context context, String url,
                            ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(imageView);
    }
}
