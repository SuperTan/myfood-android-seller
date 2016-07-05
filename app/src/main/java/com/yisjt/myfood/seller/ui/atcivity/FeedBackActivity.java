package com.yisjt.myfood.seller.ui.atcivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseActivity;
import com.yisjt.myfood.seller.utils.view.TitleFirstBar;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {
    private TitleFirstBar titleFirstBar;
    private EditText et_feedback;
    private EditText et_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView(savedInstanceState);
        initBLL(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        titleFirstBar = new TitleFirstBar(this);
        et_feedback = (EditText) findViewById(R.id.et_feedback);
        et_contact = (EditText) findViewById(R.id.et_contact);
        titleFirstBar.title_txt_center.setText(getResources().getString(R.string.comments_and_feedback));
        titleFirstBar.title_imgbtn_left.setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_imgbtn_left:
                finish();
                break;
            case R.id.btn_submit:

                if (TextUtils.isEmpty(et_feedback.getText())) {
                    Toast.makeText(this, "空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(et_contact.getText())) {
                    Toast.makeText(this, "空", Toast.LENGTH_SHORT).show();
                }

                break;


        }

    }
}
