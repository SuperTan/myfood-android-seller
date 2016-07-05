package com.yisjt.myfood.seller.ui.fragment.orderform;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseFragment;
import com.yisjt.myfood.seller.ui.atcivity.dishesmanage.DishesManageActivity;
import com.yisjt.myfood.seller.ui.fragment.beingprocessed.BeingProcessedFragment;
import com.yisjt.myfood.seller.ui.fragment.processed.ProcessedFragment;
import com.yisjt.myfood.seller.ui.fragment.untreated.UntreatedFragment;

/**
 * 订单页面
 */
public class OrderFormFragment extends BaseFragment {

    private ViewPager vp_order_form;
    private TabLayout tl_order_form;
    private Fragment[] fragment = new Fragment[3];
    private BeingProcessedFragment beingProcessed;
    private ProcessedFragment processed;
    private UntreatedFragment untreated;
    private OrderFromPager orderFromPager;

    public static OrderFormFragment newInstance() {
        OrderFormFragment fragment = new OrderFormFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_form, container, false);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {

        fragment[0] = untreated = new UntreatedFragment();
        fragment[1] = beingProcessed = new BeingProcessedFragment();
        fragment[2] = processed = new ProcessedFragment();
        orderFromPager = new OrderFromPager(getFragmentManager());

        vp_order_form = (ViewPager) getView().findViewById(R.id.vp_order_form);
        tl_order_form = (TabLayout) getView().findViewById(R.id.tl_order_form);
    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {
        vp_order_form.setAdapter(orderFromPager);
        vp_order_form.setOffscreenPageLimit(3);
        //tl_order_form.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        tl_order_form.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tl_order_form.setupWithViewPager(vp_order_form);//将TabLayout和ViewPager关联起来。
        tl_order_form.setTabsFromPagerAdapter(orderFromPager);//给Tabs设置适配器
        tl_order_form.getTabAt(0).setText(getResources().getString(R.string.untreated));
        tl_order_form.getTabAt(1).setText(getResources().getString(R.string.beingProcessed));
        tl_order_form.getTabAt(2).setText(getResources().getString(R.string.processed));
        getView().findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DishesManageActivity.class));
            }
        });

    }

    private class OrderFromPager extends FragmentPagerAdapter {

        public OrderFromPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragment[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
