package com.parking.park.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2019/3/23 0023.
 */
public class ImageLoader {
    public static void load(Uri uri,
                            ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(uri)
                .into(imageView);
    }

    public static void load(String url,
                            ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .into(imageView);
    }
}
