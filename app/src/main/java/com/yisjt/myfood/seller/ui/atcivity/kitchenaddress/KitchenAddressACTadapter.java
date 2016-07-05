package com.yisjt.myfood.seller.ui.atcivity.kitchenaddress;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.CustomBaseAdapter;

/**
 * Created by Administrator on 2016/6/30.
 */
public class KitchenAddressACTadapter extends CustomBaseAdapter {
    /**
     * 构造函数
     *
     * @param context
     */
    public KitchenAddressACTadapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ACTViewHodler viewHodler;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_kitchen_address, parent, false);
            viewHodler = new ACTViewHodler();
            convertView.setTag(viewHodler);
        }
        viewHodler = (ACTViewHodler) convertView.getTag();
        viewHodler.tv_history_address.setText(list.get(position) + "");
        return convertView;
    }

    public class ACTViewHodler {
        public TextView tv_history_address;
    }
}
