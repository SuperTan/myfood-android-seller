package com.yisjt.myfood.seller.ui.fragment.setting;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseFragment;
import com.yisjt.myfood.seller.ui.atcivity.FeedBackActivity;
import com.yisjt.myfood.seller.utils.SmallTools;

/**
 * 设置界面
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_version_number;


    @Override
    protected void initView(Bundle savedInstanceState) {

        tv_version_number = (TextView) getView().findViewById(R.id.tv_version_number);


        getView().findViewById(R.id.ll_go_service).setOnClickListener(this);
        getView().findViewById(R.id.tv_go_feedback).setOnClickListener(this);
        getView().findViewById(R.id.tv_go_cust).setOnClickListener(this);
        getView().findViewById(R.id.btn_exit).setOnClickListener(this);


    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {
        tv_version_number.setText(getResources().getString(R.string.yisjt_cai299) +"  "+ getVersion());
    }


    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_go_service:

                Intent intent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + getResources().getString(R.string.customer_service_telephone)));
                startActivity(intent);
                break;

            case R.id.tv_go_feedback:
                SmallTools.startActivity(getActivity(), FeedBackActivity.class);
                break;

            case R.id.tv_go_cust:
                break;

            case R.id.btn_exit:
                break;


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    /**
     * 获取当前应用程序的版本号
     */
    private String getVersion() {
        String st = getResources().getString(R.string.Version_number_is_wrong);
        PackageManager pm = getActivity().getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getActivity().getPackageName(), 0);
            String version = packinfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return st;
        }
    }

}
