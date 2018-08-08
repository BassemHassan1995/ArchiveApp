package com.watanya.archive.ui.view.splash;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.watanya.archive.BR;
import com.watanya.archive.R;
import com.watanya.archive.ui.ViewModelFactory;
import com.watanya.archive.ui.base.BaseActivity;
import com.watanya.archive.databinding.SplashLayoutBinding;

public class SplashActivity extends BaseActivity<SplashLayoutBinding, SplashViewModel> implements SplashNavigator {

    private SplashViewModel mSplashViewModel;

    @Override
    public void handleError(String message, int code) {

    }

    @Override
    public void showLoadingDialog() {
        showWaitingDialog();
    }

    @Override
    public void hideLoadingDialog() {
        hideWaitingDialog();
    }

    @Override
    public void showToast(String message, int duration) {
        showUiToast(message, duration);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_layout;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSplashViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(SplashViewModel.class);
        super.onCreate(savedInstanceState);

    }
}
