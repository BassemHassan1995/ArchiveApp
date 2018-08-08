package com.watanya.archive.data.interfaces;

import com.watanya.archive.data.remote.ErrorDetails;

public interface OperationListener<T> {

    void onSuccess(T result);
    void onError(ErrorDetails throwable);

}
