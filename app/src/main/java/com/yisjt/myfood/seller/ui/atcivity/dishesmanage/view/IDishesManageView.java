package com.yisjt.myfood.seller.ui.atcivity.dishesmanage.view;

import android.text.SpannableStringBuilder;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface IDishesManageView {
    /**
     * 菜品系设置不同字体的显示
     *
     * @param ssb
     */
    void vTextKindStyle(SpannableStringBuilder ssb);

    /**
     * 菜品介绍设置不同字体的显示
     *
     * @param ssb
     */
    void vTextExplainFirstStyle(SpannableStringBuilder ssb);

    /**
     * 就餐方式设置不同字体的显示
     *
     * @param ssb
     */
    void vEatWay(SpannableStringBuilder ssb);

    /**
     * 就餐方式设置不同字体的显示
     *
     * @param ssb
     */
    void vDishesImage(SpannableStringBuilder ssb);

    /**
     * 未选中菜品系列
     */
    void vDishesKindSizeZero();

    /**
     * 菜品名称为空
     */
    void vDishesNameEmpty();

    /**
     * 菜品价格为空
     */
    void vDishesPriceEmpty();


    /**
     * 未选中菜品图片
     */
    void vDishesImageSizeZero();


    /**
     * 就餐方式未选中
     */
    void vEatWaySizeZero();
    /**
     * 判断没有错误后开始上传
     */
    void vDishUpLoading(List<String> dishes_kind, String dishes_name
            , int dishes_price, List<String> selectedPhotos
            , List<String> eat_way, boolean dishesUpOrDwon);
}