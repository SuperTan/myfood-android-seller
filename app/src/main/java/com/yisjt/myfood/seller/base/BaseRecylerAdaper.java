package com.yisjt.myfood.seller.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public abstract class BaseRecylerAdaper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected LayoutInflater inflater;
    protected Context context;
    protected List<T> list;


    public BaseRecylerAdaper(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setListData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }
}
