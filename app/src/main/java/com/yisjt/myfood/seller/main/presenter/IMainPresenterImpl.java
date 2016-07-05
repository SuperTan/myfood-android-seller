package com.yisjt.myfood.seller.main.presenter;

import com.yisjt.myfood.seller.main.view.IMainView;

/**
 * Created by Administrator on 2016/6/17.
 */
public class IMainPresenterImpl implements IMainPresenter {
    private IMainView iMainView;

    public IMainPresenterImpl(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    @Override
    public void pLogin(String name, String passwd) {

    }

    @Override
    public void pOnClear() {

    }

    @Override
    public void pOnDestroy() {

    }
}
