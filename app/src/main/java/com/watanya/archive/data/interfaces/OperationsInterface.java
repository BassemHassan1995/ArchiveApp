package com.watanya.archive.data.interfaces;

import com.watanya.archive.data.model.ApiDefaultResponse;
import com.watanya.archive.data.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface OperationsInterface {

    void login(User user, final OperationListener<User> listener);
    void facebookLogin(final String token, final OperationListener<User> listener);
    void updateUserPhoto(Byte[] photo, final OperationListener<String> listener);
    void signup(User user, final OperationListener<User> listener);
    void getProfile(final OperationListener<User> listener);
    void updateProfile(User user, final OperationListener<User> listener);
    void forgetPassword(User user, final OperationListener<ApiDefaultResponse> listener);
    void changePassword(String oldPass, String newPass, final OperationListener<ApiDefaultResponse> listener);

}
