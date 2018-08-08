package com.watanya.archive.ui;

public interface CommonActions {

    void handleError(String message, int code);
    void showLoadingDialog();
    void hideLoadingDialog();
    void showToast(String message,int duration);
}
