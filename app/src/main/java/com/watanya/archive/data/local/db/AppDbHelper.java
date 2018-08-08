package com.watanya.archive.data.local.db;

import android.annotation.SuppressLint;

import com.watanya.archive.data.local.prefs.AppPreferencesHelper;
import com.watanya.archive.data.model.User;
import com.watanya.archive.data.interfaces.OperationListener;
import com.watanya.archive.data.remote.ErrorDetails;
import com.watanya.archive.data.interfaces.DbInterface;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class AppDbHelper implements DbInterface {

    private final AppDatabase mAppDatabase;
    public static AppDbHelper helper = null;

    public AppDbHelper() {
        helper = this;
        mAppDatabase = AppDatabase.getRoomDatabase();
    }


    public static AppDbHelper getInstance() {
        return helper;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllUsers(final OperationListener<List<User>> listener) {
        mAppDatabase.userDao()
                        .loadAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<User>>() {
                            @Override
                            public void accept(List<User> users) {
                                listener.onSuccess(users);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                listener.onError(new ErrorDetails(throwable));
                            }
                        });
    }

    @Override
    public void insertUser(final User user, final OperationListener<Void> listener) {

        Completable
                .fromAction(new Action() {
                    @Override
                    public void run() {

                        mAppDatabase.userDao().insert(user);
                    }})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {

                        listener.onSuccess(null);
                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onError(new ErrorDetails(e));
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getLoggedInUser(final OperationListener<User> listener) {
        mAppDatabase.userDao()
                .findByToken(AppPreferencesHelper.getInstance().getAccessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        listener.onSuccess(user);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.onError(new ErrorDetails(throwable));
                    }
                });
    }

    @Override
    public void deleteLoggedInUser(final OperationListener<Void> listener) {
        getLoggedInUser(new OperationListener<User>() {
            @Override
            public void onSuccess(final User result) {
                Completable.fromAction(new Action() {
                    @Override
                    public void run() {
                        mAppDatabase.userDao().delete(result);
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onComplete() {

                                listener.onSuccess(null);
                            }

                            @Override
                            public void onError(Throwable e) {

                                listener.onError(new ErrorDetails(e));
                            }
                        });
            }

            @Override
            public void onError(ErrorDetails throwable) {
                listener.onError(throwable);
            }
        });

    }
}
