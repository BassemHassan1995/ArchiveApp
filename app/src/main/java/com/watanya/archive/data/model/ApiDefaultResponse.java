package com.watanya.archive.data.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiDefaultResponse implements Serializable{

    @SerializedName(value = "status", alternate = "Status")
    private String status;

    @SerializedName("message")
    private String message;

    public ApiDefaultResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
