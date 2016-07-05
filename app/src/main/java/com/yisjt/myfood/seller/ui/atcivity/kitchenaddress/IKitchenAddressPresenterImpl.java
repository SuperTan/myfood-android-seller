package com.yisjt.myfood.seller.ui.atcivity.kitchenaddress;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class IKitchenAddressPresenterImpl implements IKitchenAddressPresenter {
    private IKitchenAddressView iLoginView;
    private List<Spannable> spannableList;

    public IKitchenAddressPresenterImpl(IKitchenAddressView iLoginView) {
        this.iLoginView = iLoginView;
        spannableList = new ArrayList<>();
    }


    @Override
    public void pInitLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        iLoginView.vInitLocation(option);
    }

    @Override
    public void pGetNowAddress(BDLocation location, int color) {
        Log.e("location", "location:" + location.getFloor());
        if (location.getFloor() == null) {
            iLoginView.vAddresses(changText("", location.getCity()
                    + location.getDistrict()
                    + location.getStreet()
                    + location.getStreetNumber(), color));
        } else {
            iLoginView.vAddresses(changText(location.getFloor(), location.getFloor()
                    + location.getDistrict()
                    + location.getStreet()
                    + location.getStreetNumber(), color));

        }
    }


    @Override
    public void pHistoricalAddresses() {
        iLoginView.vHistoricalAddresses(spannableList);
    }


    @Override
    public void pGetNowAddress(LatLng latLng, final int color) {
        //设置反地理编码位置坐标
        ReverseGeoCodeOption op = new ReverseGeoCodeOption();
        op.location(latLng);
        //实例化一个地理编码查询对象
        GeoCoder geoCoder = GeoCoder.newInstance();
        //发起反地理编码请求(经纬度->地址信息)
        geoCoder.reverseGeoCode(op);
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
                iLoginView.vAddresses(changText("", arg0.getAddressDetail().city
                        + arg0.getAddressDetail().district
                        + arg0.getAddressDetail().street
                        + arg0.getAddressDetail().streetNumber, color));
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult arg0) {
            }
        });
    }

    private SpannableStringBuilder changText(String top, String bottom, int color) {

        SpannableStringBuilder ssbTime = new SpannableStringBuilder(top);
        if (!top.equals("")) {
            ssbTime.append("\n");
        }
        int i = ssbTime.length();
        ssbTime.append(bottom);
        ForegroundColorSpan span_1 = new ForegroundColorSpan(color);
        ssbTime.setSpan(span_1, i, ssbTime.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ssbTime.setSpan(new RelativeSizeSpan(0.8f), i, ssbTime.length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return ssbTime;
    }

}
