package com.watanya.archive.ui.base;

import android.arch.lifecycle.ViewModel;

import com.watanya.archive.data.local.db.AppDbHelper;
import com.watanya.archive.data.interfaces.DbInterface;
import com.watanya.archive.data.local.prefs.AppPreferencesHelper;
import com.watanya.archive.data.remote.OperationsExecutor;
import com.watanya.archive.data.interfaces.OperationsInterface;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<N> extends ViewModel {


    private CompositeDisposable mCompositeDisposable;

    private DbInterface dbHelper;
    private AppPreferencesHelper preferencesHelper;
    private OperationsInterface operationsHelper;
    private N mNavigator;

    public BaseViewModel(N navigator) {
        this.mCompositeDisposable = new CompositeDisposable();
        this.mNavigator = navigator;
        dbHelper = AppDbHelper.getInstance();
        preferencesHelper = AppPreferencesHelper.getInstance();
        operationsHelper = OperationsExecutor.getInstance();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public N getNavigator() {
        return mNavigator;
    }

    protected DbInterface getDbHelper() {
        return dbHelper;
    }

    protected AppPreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }

    protected OperationsInterface getOperationsHelper() {
        return operationsHelper;
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

}
