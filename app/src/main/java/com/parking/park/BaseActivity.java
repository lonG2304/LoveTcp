package com.parking.park;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.FileUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/23 0023.
 */
public class BaseActivity extends AppCompatActivity {
    protected AdLayout mLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到当前界面的装饰视图
        View decorView = getWindow().getDecorView();
//        SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏
        //设置系统UI元素的可见性
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ActivityCompat.requestPermissions(this, new String[]{android
                .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        initAddLayout();
    }


    private void initAddLayout() {
        mLayout = new AdLayout(this);
        mLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        File file = new File(Environment.getExternalStorageDirectory(), "Parking_AD");
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }

        List<File> files = FileUtils.listFilesInDir(file);
        if (files == null) return;
        ArrayList<String> paths = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {
            paths.add(files.get(i).getPath());
        }
        mLayout.setImageList(paths);
    }


    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        if (mLayout != null)
            mLayout.stop();
        super.onDestroy();
    }
}
