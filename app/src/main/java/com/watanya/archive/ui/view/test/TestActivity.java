package com.watanya.archive.ui.view.test;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.stfalcon.frescoimageviewer.ImageViewer;
import com.watanya.archive.BR;
import com.watanya.archive.R;
import com.watanya.archive.ui.ViewModelFactory;
import com.watanya.archive.ui.base.BaseActivity;
import com.watanya.archive.databinding.TestLayoutBinding;

import java.util.ArrayList;

public class TestActivity extends BaseActivity<TestLayoutBinding, TestViewModel> implements TestNavigator {

    private TestViewModel mTestViewModel;

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
        return R.layout.test_layout;
    }

    @Override
    public TestViewModel getViewModel() {
        return mTestViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mTestViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(TestViewModel.class);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void showGallery(ArrayList<String> list, int startPosition) {
        Fresco.initialize(this);

        new ImageViewer.Builder(this, list)
                .setStartPosition(startPosition)
                .setBackgroundColor(getResources().getColor(R.color.white))
                .allowSwipeToDismiss(true)
                .setOnDismissListener(new ImageViewer.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        finish();
                    }
                })
                .allowZooming(true)
                .show();
    }
}
