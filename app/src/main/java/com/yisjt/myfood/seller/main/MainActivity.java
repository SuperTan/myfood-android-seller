package com.yisjt.myfood.seller.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseFragmentActivity;
import com.yisjt.myfood.seller.ui.atcivity.kitchenaddress.KitchenAddressActivity;
import com.yisjt.myfood.seller.ui.fragment.control.ControlFragment;
import com.yisjt.myfood.seller.ui.fragment.orderform.OrderFormFragment;
import com.yisjt.myfood.seller.ui.fragment.setting.SettingFragment;
import com.yisjt.myfood.seller.utils.SmallTools;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    private TextView tv_order_from;
    private TextView tv_control;
    private TextView tv_setting;
    private OrderFormFragment orderForm;
    private ControlFragment control;
    private SettingFragment setting;
    private Fragment fragment;
    private View selectView;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
        initBLL(savedInstanceState);

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        fragment = orderForm = OrderFormFragment.newInstance();
        control = ControlFragment.newInstance();
        setting = SettingFragment.newInstance();
        transaction = getSupportFragmentManager().beginTransaction();

        tv_order_from = (TextView) findViewById(R.id.tv_order_from);
        tv_control = (TextView) findViewById(R.id.tv_control);
        tv_setting = (TextView) findViewById(R.id.tv_setting);

        tv_order_from.setOnClickListener(this);
        tv_control.setOnClickListener(this);
        tv_setting.setOnClickListener(this);

    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {
        PushManager.getInstance().initialize(this.getApplicationContext());
        tv_order_from.setSelected(true);
        selectView = tv_order_from;
        getSupportFragmentManager().beginTransaction().add(R.id.fg_main, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_from:
                selectView(selectView, tv_order_from);
                selectView = tv_order_from;

                showOrHideFragment(fragment, orderForm, getSupportFragmentManager().beginTransaction());
                fragment = orderForm;
                break;
            case R.id.tv_control:
                selectView(selectView, tv_control);
                selectView = tv_control;

                SmallTools.startActivity(this, KitchenAddressActivity.class);

                showOrHideFragment(fragment, control, getSupportFragmentManager().beginTransaction());
                fragment = control;
                break;
            case R.id.tv_setting:
                selectView(selectView, tv_setting);
                selectView = tv_setting;


                showOrHideFragment(fragment, setting, getSupportFragmentManager().beginTransaction());
                fragment = setting;
                break;

        }

    }


    /**
     * 隐藏或者显示Fragment
     */
    public void showOrHideFragment(Fragment from, Fragment to, FragmentTransaction transaction) {

        if (from == to)
            return;
        if (to.isAdded()) {
            transaction.show(to).hide(from).commit();
        } else {
            transaction.add(R.id.fg_main, to).hide(from).commit();
        }

    }

    /**
     * 点击选择某个按钮
     *
     * @param from
     * @param to
     */
    public void selectView(View from, View to) {

        if (from == to) {

        } else {
            from.setSelected(false);
            to.setSelected(true);
        }

    }

}
