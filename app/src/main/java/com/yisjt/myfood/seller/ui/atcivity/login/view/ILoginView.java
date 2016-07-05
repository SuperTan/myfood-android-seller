package com.yisjt.myfood.seller.ui.atcivity.login.view;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface ILoginView {

    /**
     * 清除
     */
    void vOnClear();

    /**
     * 密码为空
     */
    void vOnUsernameError();

    /**
     * 用户名为空
     */
    void vOnPasswordError();

    /**
     * 获取验证码失败
     */
    void vVerificationCodeFaile();

    /**
     * 登录成功
     */
    void vOnSuccess();

    /**
     * 界面
     */
    void vTimering(long millisUntilFinished);

    void vStopTimering();

    /**
     * 登录失败
     */
    void vOnFailed();


}