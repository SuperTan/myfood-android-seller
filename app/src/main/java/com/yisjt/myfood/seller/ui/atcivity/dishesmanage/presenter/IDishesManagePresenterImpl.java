package com.yisjt.myfood.seller.ui.atcivity.dishesmanage.presenter;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import com.yisjt.myfood.seller.ui.atcivity.dishesmanage.view.IDishesManageView;

import java.util.List;

import okhttp3.MediaType;


/**
 * Created by Administrator on 2016/6/17.
 */
public class IDishesManagePresenterImpl implements IDishesManagePresenter {
    private IDishesManageView dishesManageView;

    public IDishesManagePresenterImpl(IDishesManageView dishesManageView) {
        this.dishesManageView = dishesManageView;
    }


    @Override
    public void pTextStyle(String[] top, String[] bottom, int color) {
        dishesManageView.vTextKindStyle(changText(top[0], bottom[0], color));
        dishesManageView.vDishesImage(changText(top[1], bottom[1], color));
        dishesManageView.vTextExplainFirstStyle(changText(top[2], bottom[2], color));
        dishesManageView.vEatWay(changText(top[3], bottom[3], color));

    }

    @Override
    public void pDishUpLoading(List<String> dishes_kind, String dishes_name,
                               String dishes_price, List<String> selectedPhotos,
                               List<String> eat_way, boolean dishesUpOrDwon) {
        if (TextUtils.isEmpty(dishes_name)) {
            dishesManageView.vDishesNameEmpty();
        } else if (TextUtils.isEmpty(dishes_price)) {
            dishesManageView.vDishesPriceEmpty();
        } else if (dishes_kind.size() == 0) {
            dishesManageView.vDishesKindSizeZero();
        } else if (eat_way.size() == 0) {
            dishesManageView.vEatWaySizeZero();
        } else if (selectedPhotos.size() == 0) {
            dishesManageView.vDishesImageSizeZero();
        } else {

//            HashMap<String, String> map = new HashMap<>();
//
//            map.put("name", "回锅肉");
//            map.put("price", "12.90");
//            map.put("ismarketable", "true");
//            map.put("imagepath", "阿达瓦胜多负少");
//            map.put("foodcategoryid", "a810a54506504873ad1a537929f2a187");
//            map.put("myfooddiningtypes", "DELIVER");
//            map.put("shopid", "4dbe7685ee9c4622a3e196781bacb143");
//            map.put("description", "来一个");
//
//            OkHttpUtils.post()
//                    .url("http://wode.ysy299.com/service/api/myfoodFood")
//                    .addAllParams(map)
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//                            Log.e("OkHttpUtils", "");
//
//                        }
//
//                        @Override
//                        public void onResponse(String response, int id) {
//                            Log.e("OkHttpUtils", "" + response);
//                        }
//                    });


        }


        Log.e("btn_ok", "dishes_kind" + dishes_kind.size());
        Log.e("btn_ok", "eat_way" + eat_way.size());
    }






    @Override
    public void selectKind(List<String> kindList, String kindString, boolean isChecked) {
        if (isChecked) {
            if (!kindList.contains(kindString)) {
                kindList.add(kindString);
            }
        } else {
            if (kindList.contains(kindString)) {
                kindList.remove(kindString);
            } else {
                kindList.add(kindString);
            }
        }
    }

    private SpannableStringBuilder changText(String top, String bottom, int color) {
        SpannableStringBuilder ssbTime = new SpannableStringBuilder(top);
        ssbTime.append("\n");
        int i = ssbTime.length();
        ssbTime.append(bottom);
        ForegroundColorSpan span_1 = new ForegroundColorSpan(color);
        ssbTime.setSpan(span_1, i, ssbTime.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ssbTime.setSpan(new RelativeSizeSpan(0.6f), i, ssbTime.length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return ssbTime;
    }

}
