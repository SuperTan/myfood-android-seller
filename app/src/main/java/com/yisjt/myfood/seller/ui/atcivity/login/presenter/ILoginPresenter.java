package com.yisjt.myfood.seller.ui.atcivity.login.presenter;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface ILoginPresenter {
    /**
     * 登录执行
     *
     * @param name
     * @param passwd
     */
    void pLogin(String name, String passwd);

    /**
     * 获取验证码
     */
    void pVerificationCode();

    /**
     * 点击获取获取验证码计时器开始
     */
    void pStartTimering();



    /**
     * 清除
     */
    void pOnClear();

    void pOnDestroy();
}
