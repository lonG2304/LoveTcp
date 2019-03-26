package com.parking.park;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.parking.park.utils.ImageLoader;

import java.io.File;
import java.util.List;

import static com.parking.park.Constant.AD_LOOP_INTERVAL;

public class AdLayout extends FrameLayout {

    private ViewPager mViewPager;
    private volatile boolean looping;
    private volatile boolean stopped;

    public AdLayout(@NonNull Context context) {
        this(context, null);
    }

    public AdLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);
        mViewPager = new ViewPager(context);
        mViewPager.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mViewPager);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        looping = false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        looping = true;
    }

    public void setImageList(final List<String> paths) {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {//必须实现
                return paths.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {//必须实现
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
                ImageView imageView = new ImageView(AdLayout.this.getContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageLoader.load(Uri.fromFile(new File(paths.get(position))), imageView);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
                container.removeView((View) object);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    if (stopped) break;
                    try {
                        Thread.sleep(AD_LOOP_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!looping) continue;
                    post(new Runnable() {
                        @Override
                        public void run() {
                            int count = mViewPager.getAdapter().getCount();
                            int currentItem = mViewPager.getCurrentItem();
                            int next;
                            if (count == 0) return;
                            if (currentItem >= count - 1) next = 0;
                            else next = currentItem + 1;
                            mViewPager.setCurrentItem(next);
                        }
                    });
                }
            }
        }).start();
    }

    public void stop() {
        stopped = true;
    }
}
