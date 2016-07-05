package com.yisjt.myfood.seller;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.yisjt.myfood.seller.base.BaseActivity;
import com.yisjt.myfood.seller.ui.atcivity.login.LoginActivity;

public class SplashScreensActivity extends BaseActivity {
    private RelativeLayout rl_splash_root;
    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 全屏无状态栏和标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screens);
        initView(savedInstanceState);
        initBLL(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        rl_splash_root = (RelativeLayout) findViewById(R.id.rl_splash_root);

    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);

        rl_splash_root.startAnimation(animation);

    }


    @Override
    protected void onStart() {
        super.onStart();

        new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();

                long costTime = System.currentTimeMillis() - start;
                //等待sleeptime时长
                if (sleepTime - costTime > 0) {
                    try {
                        Thread.sleep(sleepTime - costTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //进入主页面
                startActivity(new Intent(SplashScreensActivity.this, LoginActivity.class));
                finish();
            }
        }).start();
    }


    /**
     * 获取当前应用程序的版本号
     */
    private String getVersion() {

        String st = getResources().getString(R.string.Version_number_is_wrong);
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getPackageName(), 0);
            String version = packinfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return st;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
