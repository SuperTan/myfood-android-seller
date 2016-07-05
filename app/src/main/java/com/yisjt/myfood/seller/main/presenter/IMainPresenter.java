package com.yisjt.myfood.seller.main.presenter;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface IMainPresenter {
    /**
     * 登录执行
     * @param name
     * @param passwd
     */
    void pLogin(String name, String passwd);

    /**
     * 清除
     */
    void pOnClear();

    void pOnDestroy();
}
