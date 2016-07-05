package com.yisjt.myfood.seller.base.mvp.presenter;

import com.yisjt.myfood.seller.base.mvp.view.ILoginView;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ILoginPresenterImpl implements ILoginPresenter
{
    private ILoginView iLoginView;

    public ILoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }


}
