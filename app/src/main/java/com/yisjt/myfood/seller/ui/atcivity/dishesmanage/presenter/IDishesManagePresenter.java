package com.yisjt.myfood.seller.ui.atcivity.dishesmanage.presenter;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface IDishesManagePresenter {


    /**
     * 菜品系设置不同字体的显示
     *
     * @param color
     */
    void pTextStyle(String[] top, String[] bottom, int color);


    /**
     * 上传判断是否符合
     */
    void pDishUpLoading(List<String> dishes_kind, String dishes_name
            , String dishes_price, List<String> selectedPhotos
            , List<String> eat_way, boolean dishesUpOrDwon);

    /**
     * 判断选择的是否已经添加，
     *
     * @param kindList
     * @param kindString
     * @param isChecked
     */
    void selectKind(List<String> kindList, String kindString, boolean isChecked);
}
