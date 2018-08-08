package com.watanya.archive.ui.view.main;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.watanya.archive.BR;
import com.watanya.archive.R;
import com.watanya.archive.data.model.Category;
import com.watanya.archive.data.model.File;
import com.watanya.archive.ui.ViewModelFactory;
import com.watanya.archive.ui.base.BaseActivity;
import com.watanya.archive.databinding.MainLayoutBinding;
import com.watanya.archive.ui.view.viewfiles.ViewFilesActivity;

public class MainActivity extends BaseActivity<MainLayoutBinding, MainViewModel> implements MainNavigator {

    private MainViewModel mMainViewModel;

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
        return R.layout.main_layout;
    }

    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mMainViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(MainViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void openActivity(Class<? extends BaseActivity> activity) {
        startActivity(activity);
    }

    @Override
    public void openViewFileActivity(Class<ViewFilesActivity> viewFilesActivityClass, File selectedFile) {
        Bundle options = new Bundle();
        options.putSerializable("file" , selectedFile);

        startActivity(viewFilesActivityClass , options);
//        startActivity(viewFileActivityClass , options);
    }

    @Override
    public void openViewFilesActivity(Class<ViewFilesActivity> viewFilesActivityClass, Category selectedCategory) {
        Bundle options = new Bundle();
        options.putSerializable("category" , selectedCategory);

        startActivity(viewFilesActivityClass , options);
    }

}
