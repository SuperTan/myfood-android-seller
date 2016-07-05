package com.yisjt.myfood.seller.ui.atcivity.kitchenaddress;

import android.text.Spannable;

import com.baidu.location.LocationClientOption;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface IKitchenAddressView {
    /**
     * 初始化请求参数成功后执行
     *
     * @param option
     */

    void vInitLocation(LocationClientOption option);

    /**
     * 刷新第一个位置的定位信息
     *
     * @param addressFirst
     */
    void vAddresses(Spannable addressFirst);

    /**
     * 显示历史记录定位信息
     *
     * @param addressAll
     */
    void vHistoricalAddresses(List<Spannable> addressAll);


}