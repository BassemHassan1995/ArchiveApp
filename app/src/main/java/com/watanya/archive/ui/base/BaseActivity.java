package com.watanya.archive.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.watanya.archive.R;
import com.watanya.archive.utils.NetworkUtils;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback {

    private T mViewDataBinding;
    private V mViewModel;
    private ProgressDialogView dialog;

    public abstract int getBindingVariable();

    public abstract @LayoutRes int getLayoutId();

    public abstract V getViewModel();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }


    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {
        //TODO: open LoginActivity
//        startActivity(LoginActivity.newIntent(this));
        finish();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void showWaitingDialog() {
        hideWaitingDialog();
        dialog = new ProgressDialogView(this);

        dialog.setCanceledOnTouchOutside(false);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.v("Dialog", "Canceled ");
                // Wake observers with the result

            }
        });

        try {
            if (!dialog.isShowing())
                dialog.showProgressDialog(getString(R.string.loading));
        } catch (Exception e) {
        } // Show dialog on activity killed
    }

    public void hideWaitingDialog() {
        if (dialog!=null && dialog.isShowing())
            dialog.hideDialog();
    }

    public void showUiToast(String message , int duration){
        Toast.makeText(this, message, duration).show();
    }

    protected void startActivity(Class<? extends BaseActivity> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }


    protected void startActivity(Class<? extends BaseActivity> activity, Bundle options) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(options);
        startActivity(intent);
    }


    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }
}

