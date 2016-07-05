package com.yisjt.myfood.seller.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 各种小工具集合
 */
public class SmallTools {
    /**
     * 启动界面
     *
     * @param context
     * @param c
     */
    public static void startActivity(Context context, Class<?> c) {
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }

    /**
     * 将Uri转换为地址
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getPath(Context context, Uri uri) {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    /**
     * 弹出软键盘功能
     *
     * @param activity
     */
    public static void popupSoftKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制隐藏输入法键盘
     */
    public static void hideInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        boolean isOpen = imm.isActive();// isOpen若返回true，则表示输入法打开
        if (isOpen) {

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }

    }
}
