package com.yisjt.myfood.seller.ui.atcivity.kitchenaddress;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseRecylerAdaper;

/**
 * Created by Administrator on 2016/6/29.
 */
public class KitchenAddressAdaper extends BaseRecylerAdaper {

    private Spannable firstSpannable;

    public KitchenAddressAdaper(Context context) {
        super(context);
        firstSpannable = new SpannableString("定位中…………");
    }

    public void notifyFirst(Spannable firstSpannable) {
        this.firstSpannable = firstSpannable;
        notifyItemChanged(0);
    }

    public class HistoryAddressHolder extends RecyclerView.ViewHolder {
        public TextView tv_history_address;

        public HistoryAddressHolder(View itemView) {
            super(itemView);
            tv_history_address = (TextView) itemView.findViewById(R.id.tv_history_address);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new HistoryAddressHolder(inflater.inflate(R.layout.item_kitchen_address, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HistoryAddressHolder addressHolder = (HistoryAddressHolder) holder;
        if (position == 0) {
            addressHolder.tv_history_address.setText(firstSpannable);
        }else{
            addressHolder.tv_history_address.setText((String) list.get(position - 1));
        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
}
