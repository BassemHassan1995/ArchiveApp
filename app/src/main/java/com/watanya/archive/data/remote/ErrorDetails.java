package com.watanya.archive.data.remote;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by Ahmed Hassan Kamal on 21 February 2018.
 */
public class ErrorDetails {

    private String errorMsg;
    private int statusCode;

    public ErrorDetails(Throwable throwable){
        formatThrowable(throwable);
    }


    public void formatThrowable(Throwable throwable) {
        if (throwable instanceof HttpException)
        {
            errorMsg = ((HttpException) throwable).message();
            statusCode = ((HttpException) throwable).code();
        }
        else if (throwable instanceof ConnectException)
        {
            errorMsg = "Please Check Internet Connection and try again";
            statusCode = 401;
        }
        else if (throwable instanceof SocketTimeoutException)
        {
            errorMsg = "Request timed out";
            statusCode = 504;
        }
        else
        {
            errorMsg = "Something went wrong, please try again later";
            statusCode = 500;
        }
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
