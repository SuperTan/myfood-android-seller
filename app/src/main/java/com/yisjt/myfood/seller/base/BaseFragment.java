package com.yisjt.myfood.seller.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/6/21.
 */
public abstract class BaseFragment extends Fragment {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState);
        initBLL(savedInstanceState);
    }
    /**
     * 第一步：获得或者创建全局变量 ,对UI控件的一些初始化,通过id找到UI控件,注册点击事件
     * @param savedInstanceState:onCreate中的参数savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     *
     * 第二步：设置属性，监听等，业务逻辑区域,业务逻辑（BLL）：封装业务处理功能
     * @param savedInstanceState:onCreate中的参数savedInstanceState
     */

    protected abstract void initBLL(Bundle savedInstanceState);
}
