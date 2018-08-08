package com.watanya.archive.ui.view.viewfiles;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.watanya.archive.BR;
import com.watanya.archive.R;
import com.watanya.archive.data.model.Category;
import com.watanya.archive.ui.ViewModelFactory;
import com.watanya.archive.ui.base.BaseActivity;
import com.watanya.archive.databinding.ViewfilesLayoutBinding;

public class ViewFilesActivity extends BaseActivity<ViewfilesLayoutBinding, ViewFilesViewModel> implements ViewFilesNavigator {

    private ViewFilesViewModel mViewFilesViewModel;

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
        return R.layout.viewfiles_layout;
    }

    @Override
    public ViewFilesViewModel getViewModel() {
        return mViewFilesViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mViewFilesViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ViewFilesViewModel.class);
        super.onCreate(savedInstanceState);

    }

    @Override
    public Category getCategory() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        return (Category) extras.getSerializable("category");
    }
}
