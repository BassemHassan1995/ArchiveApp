package com.watanya.archive.ui.view.createFile;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.watanya.archive.BR;
import com.watanya.archive.R;
import com.watanya.archive.ui.ViewModelFactory;
import com.watanya.archive.ui.base.BaseActivity;
import com.watanya.archive.databinding.CreatefileLayoutBinding;

public class CreateFileActivity extends BaseActivity<CreatefileLayoutBinding, CreateFileViewModel> implements CreateFileNavigator {

    private CreateFileViewModel mCreateFileViewModel;

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
        return R.layout.createfile_layout;
    }

    @Override
    public CreateFileViewModel getViewModel() {
        return mCreateFileViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mCreateFileViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(CreateFileViewModel.class);
        super.onCreate(savedInstanceState);

    }
}
