package com.watanya.archive.data.remote;

import com.watanya.archive.data.interfaces.OperationListener;
import com.watanya.archive.data.interfaces.OperationsInterface;
import com.watanya.archive.data.local.db.AppDbHelper;
import com.watanya.archive.data.local.prefs.AppPreferencesHelper;
import com.watanya.archive.data.model.ApiDefaultResponse;
import com.watanya.archive.data.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class OperationsExecutor implements OperationsInterface {

    private static OperationsExecutor _instance = null;

    private OperationsExecutor() {

    }

    public static OperationsExecutor getInstance() {
        if (_instance == null)
            _instance = new OperationsExecutor();
        return _instance;
    }


    @Override
    public void login(User user, final OperationListener<User> listener) {
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<User> observable =  apiService.login(headers, user);
        final CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<User>() {
                @Override
                public void accept(final User user) {
                    listener.onSuccess(user);
                    AppDbHelper.getInstance().insertUser(user, new OperationListener<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                            AppPreferencesHelper.getInstance().setAccessToken(user.accessToken);
                        }

                        @Override
                        public void onError(ErrorDetails throwable) {
                            ErrorDetails t = throwable;
                        }
                    });
                    disposable.dispose();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) {
                    listener.onError(new ErrorDetails(throwable));
                    disposable.dispose();
                }
            }));


    }


    @Override
    public void facebookLogin(final String token, final OperationListener<User> listener){
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        HashMap<String, String> body = new HashMap<>();
        body.put("token",token);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<User> observable =  apiService.facebookLogin(headers, body);
        final CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(final User user) {

                        AppDbHelper.getInstance().insertUser(user, new OperationListener<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                AppPreferencesHelper.getInstance().setAccessToken(user.accessToken);
                                listener.onSuccess(user);
                            }

                            @Override
                            public void onError(ErrorDetails throwable) {
//                                listener.onError(throwable);
                            }
                        });

                        disposable.dispose();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.onError(new ErrorDetails(throwable));
                        disposable.dispose();
                    }
                }));

    }

    @Override
    public void updateUserPhoto(Byte[] photo, final OperationListener<String> listener){
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<String> observable =  apiService.updatePhoto(headers, photo);
        final CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) {
                        listener.onSuccess(response);
                        disposable.dispose();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.onError(new ErrorDetails(throwable));
                        disposable.dispose();
                    }
                }));
    }

    @Override
    public void signup(User user, final OperationListener<User> listener){
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<User> observable =  apiService.signup(headers, user);
        final CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User response) {
                        listener.onSuccess(response);
                        disposable.dispose();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.onError(new ErrorDetails(throwable));
                        disposable.dispose();
                    }
                }));
    }

    @Override
    public void getProfile(final OperationListener<User> listener){
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<User> observable =  apiService.getProfile(headers);
        final CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User response) {
                        listener.onSuccess(response);
                        disposable.dispose();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.onError(new ErrorDetails(throwable));
                        disposable.dispose();
                    }
                }));
    }

    @Override
    public void updateProfile(User user, final OperationListener<User> listener){
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<User> observable =  apiService.updateProfile(headers, user);
        final CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User response) {
                        listener.onSuccess(response);
                        disposable.dispose();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.onError(new ErrorDetails(throwable));
                        disposable.dispose();
                    }
                }));

    }

    @Override
    public void forgetPassword(User user, final OperationListener<ApiDefaultResponse> listener){
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<ApiDefaultResponse> observable =  apiService.forgetPassword(headers, user);
        final CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiDefaultResponse>() {
                    @Override
                    public void accept(ApiDefaultResponse response) {
                        listener.onSuccess(response);
                        disposable.dispose();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.onError(new ErrorDetails(throwable));
                        disposable.dispose();
                    }
                }));

    }

    @Override
    public void changePassword(String oldPass, String newPass , final OperationListener<ApiDefaultResponse> listener){
        HashMap<String, String> headers = ApiClient.getDefaultHeaders();
        HashMap<String, String> passwords = new HashMap<>();
        passwords.put("old_password",oldPass);
        passwords.put("new_password", newPass);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<ApiDefaultResponse> observable =  apiService.changePassword(headers, passwords);
        final CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiDefaultResponse>() {
                    @Override
                    public void accept(ApiDefaultResponse response) {
                        listener.onSuccess(response);
                        disposable.dispose();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        listener.onError(new ErrorDetails(throwable));
                        disposable.dispose();
                    }
                }));

    }
}
