package com.watanya.archive.ui.view.viewPapers;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.watanya.archive.BR;
import com.watanya.archive.R;
import com.watanya.archive.data.model.File;
import com.watanya.archive.ui.ViewModelFactory;
import com.watanya.archive.ui.base.BaseActivity;
import com.watanya.archive.databinding.ViewpapersLayoutBinding;
import com.watanya.archive.ui.view.test.TestActivity;

public class ViewPapersActivity extends BaseActivity<ViewpapersLayoutBinding, ViewPapersViewModel> implements ViewPapersNavigator {

    private ViewPapersViewModel mViewPapersViewModel;

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
        return R.layout.viewpapers_layout;
    }

    @Override
    public ViewPapersViewModel getViewModel() {
        return mViewPapersViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mViewPapersViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ViewPapersViewModel.class);
        super.onCreate(savedInstanceState);

    }

    @Override
    public File getFile() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        return (File) extras.getSerializable("file");
    }

    @Override
    public void OpenPaperActivity(Bundle extras) {
        startActivity(TestActivity.class, extras);
    }
}
