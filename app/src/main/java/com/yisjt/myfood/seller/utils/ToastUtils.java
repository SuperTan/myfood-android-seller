package com.yisjt.myfood.seller.utils;

import android.widget.Toast;

import com.yisjt.myfood.seller.base.SellerApplication;

/**
 * Created by Administrator on 2016/3/28.
 */
public class ToastUtils {
    public static void toastSHORT(Object s) {
        Toast.makeText(SellerApplication.getOverAllContext(), s.toString(), Toast.LENGTH_SHORT).show();
    }

    public static void toastLONG(Object s) {
        Toast.makeText(SellerApplication.getOverAllContext(), s.toString(), Toast.LENGTH_LONG).show();
    }
}
