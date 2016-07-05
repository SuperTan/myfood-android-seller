package com.yisjt.myfood.seller.main.view;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface IMainView {

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
     * 登录成功
     */
    void vOnSuccess();


}