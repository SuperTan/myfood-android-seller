package com.yisjt.myfood.seller.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/6/21.
 */
public class SellerApplication extends Application {
    private static Context context;

    public static Context getOverAllContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
