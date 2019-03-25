package com.parking.park;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

public class AdLayout extends FrameLayout {

    private ViewPager mViewPager;

    public AdLayout(@NonNull Context context) {
        this(context, null);
    }

    public AdLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mViewPager = new ViewPager(context);
        mViewPager.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mViewPager);
    }

    public void setImageList(final List<String> paths) {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return paths.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }
        });


    }
}
