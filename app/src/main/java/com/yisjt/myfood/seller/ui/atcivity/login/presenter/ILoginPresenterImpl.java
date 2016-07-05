package com.yisjt.myfood.seller.ui.atcivity.login.presenter;

import android.os.CountDownTimer;

import com.yisjt.myfood.seller.ui.atcivity.login.view.ILoginView;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ILoginPresenterImpl implements ILoginPresenter {

    private ILoginView iLoginView;

    public ILoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }


    @Override
    public void pLogin(String name, String passwd) {

//        if (TextUtils.isEmpty(name)) {
//            iLoginView.vOnUsernameError();
//        } else if (TextUtils.isEmpty(passwd)) {
//            iLoginView.vOnPasswordError();
//        } else {
//            iLoginView.vOnSuccess();
//        }
        iLoginView.vOnSuccess();

    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            iLoginView.vTimering(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            iLoginView.vStopTimering();
            timer.cancel();
        }
    };


    @Override
    public void pVerificationCode() {

    }

    @Override
    public void pStartTimering() {
        timer.start();
    }

    @Override
    public void pOnClear() {

    }

    @Override
    public void pOnDestroy() {

    }
}
