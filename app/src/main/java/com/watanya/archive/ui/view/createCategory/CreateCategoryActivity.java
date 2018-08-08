package com.watanya.archive.ui.view.createCategory;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.watanya.archive.BR;
import com.watanya.archive.R;
import com.watanya.archive.ui.ViewModelFactory;
import com.watanya.archive.ui.base.BaseActivity;
import com.watanya.archive.databinding.CreatecategoryLayoutBinding;

public class CreateCategoryActivity extends BaseActivity<CreatecategoryLayoutBinding, CreateCategoryViewModel> implements CreateCategoryNavigator {

    private CreateCategoryViewModel mCreateCategoryViewModel;

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
        return R.layout.createcategory_layout;
    }

    @Override
    public CreateCategoryViewModel getViewModel() {
        return mCreateCategoryViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mCreateCategoryViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(CreateCategoryViewModel.class);
        super.onCreate(savedInstanceState);

    }
}
