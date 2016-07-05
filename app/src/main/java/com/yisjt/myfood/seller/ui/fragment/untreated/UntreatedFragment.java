package com.yisjt.myfood.seller.ui.fragment.untreated;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseFragment;


/**
 * 未处理页面
 */
public class UntreatedFragment extends BaseFragment {
    private WebView wv_being_processed;

    public static UntreatedFragment newInstance() {
        UntreatedFragment fragment = new UntreatedFragment();

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
        return inflater.inflate(R.layout.fragment_untreated, container, false);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        wv_being_processed = (WebView) getView().findViewById(R.id.wv_being_processed);
    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {
        wv_being_processed.getSettings().setJavaScriptEnabled(true);
        wv_being_processed.setWebViewClient(new WebViewClient());
        wv_being_processed.loadUrl("http://192.168.0.110:3000/order/list?orderType=1");
    }
}
