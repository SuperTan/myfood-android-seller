package com.yisjt.myfood.seller.ui.atcivity.kitchenaddress;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface IKitchenAddressPresenter {
    /**
     * 初始化百度地图请求信息
     */
    void pInitLocation();

    /**
     * 初次访问时对应的显示信息
     *
     * @param location
     * @param color
     */
    void pGetNowAddress(BDLocation location, int color);

    /**
     * 点击地图时显示的信息
     *
     * @param latLng
     * @param color
     */
    void pGetNowAddress(LatLng latLng, int color);

    /**
     * 定位的历史记录
     */
    void pHistoricalAddresses();


}
