package com.yisjt.myfood.seller.utils.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.yisjt.myfood.seller.R;

/**
 * 对应布局引用<include layout="@layout/title_first_bar"/>
 */
public class TitleFirstBar {

    private Activity activity;
    public TextView title_imgbtn_left;
    public TextView title_txt_center;
    public TextView title_imgbtn_right;

    public TitleFirstBar(Activity activity) {
        this.activity = activity;
        title_imgbtn_left = (TextView) activity.findViewById(R.id.title_imgbtn_left);
        title_txt_center = (TextView) activity.findViewById(R.id.title_txt_center);
        title_imgbtn_right = (TextView) activity.findViewById(R.id.title_imgbtn_right);

    }

    /**
     * 点击左边结束Activity
     */
    public void leftFinish() {
        title_imgbtn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }


}
