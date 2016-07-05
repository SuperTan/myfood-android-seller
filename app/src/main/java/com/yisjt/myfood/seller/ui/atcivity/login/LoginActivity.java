package com.yisjt.myfood.seller.ui.atcivity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yisjt.myfood.seller.R;
import com.yisjt.myfood.seller.base.BaseActivity;
import com.yisjt.myfood.seller.main.MainActivity;
import com.yisjt.myfood.seller.ui.atcivity.login.presenter.ILoginPresenterImpl;
import com.yisjt.myfood.seller.ui.atcivity.login.view.ILoginView;
import com.yisjt.myfood.seller.utils.SmallTools;
import com.yisjt.myfood.seller.utils.ToastUtils;
import com.yisjt.myfood.seller.utils.view.PromptDialog;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {
    private EditText et_user_name;
    private EditText et_user_password;
    private Button btn_login;
    private TextView tv_get_code;
    private TextView tv_app_explain;
    private TextView tv_title;
    //private TextView tv_code_error;
    private ILoginPresenterImpl loginPresenter;
    private PromptDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView(savedInstanceState);
        initBLL(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        loginPresenter = new ILoginPresenterImpl(this);

        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_user_password = (EditText) findViewById(R.id.et_user_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_get_code = (TextView) findViewById(R.id.tv_get_code);
        tv_app_explain = (TextView) findViewById(R.id.tv_app_explain);
        tv_title = (TextView) findViewById(R.id.tv_title);
       // tv_code_error = (TextView) findViewById(R.id.tv_code_error);

        findViewById(R.id.rl_hide_input).setOnClickListener(this);

    }

    @Override
    protected void initBLL(Bundle savedInstanceState) {
        btn_login.setOnClickListener(this);
        tv_get_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginPresenter.pLogin(et_user_name.getText().toString(), et_user_password.getText().toString());
                break;
            case R.id.tv_get_code:
                dialog = new PromptDialog(this);

                dialog.showLoginDialog(getResources().getString(R.string.sure),
                        getResources().getString(R.string.cancel),
                        getResources().getString(R.string.verification_code_error));

                dialog.setOnSureFinishListener(new PromptDialog.OnSureFinishListener() {
                    @Override
                    public void onSureFinish() {

                        dialog.dismiss();
                    }
                });

                SmallTools.hideInput(this, tv_title);
                loginPresenter.pVerificationCode();
                tv_get_code.setEnabled(false);
                loginPresenter.pStartTimering();
                break;
            case R.id.rl_hide_input:
                SmallTools.hideInput(this, tv_title);
                break;

        }
    }

    @Override
    public void vOnClear() {

    }

    @Override
    public void vOnUsernameError() {
        ToastUtils.toastSHORT(getResources().getString(R.string.user_name_empty));
    }

    @Override
    public void vOnPasswordError() {
        ToastUtils.toastSHORT(getResources().getString(R.string.pass_word_empty));
    }


    @Override
    public void vVerificationCodeFaile() {

    }

    @Override
    public void vOnSuccess() {
        //进入主页面
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void vTimering(long millisUntilFinished) {
        tv_get_code.setTextColor(getResources().getColor(R.color.color_fe761b));
        tv_get_code.setText((millisUntilFinished / 1000) + getResources().getString(R.string.send_repetition));
    }

    @Override
    public void vStopTimering() {
        tv_get_code.setEnabled(true);
        tv_get_code.setTextColor(getResources().getColor(R.color.color_3B3E3D));
        tv_get_code.setText(getResources().getString(R.string.get_verification_code));
    }

    @Override
    public void vOnFailed() {
      //  tv_code_error.setVisibility(View.VISIBLE);
    }


}
