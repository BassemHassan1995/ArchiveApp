package com.watanya.archive.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(
        tableName = "photos"
)

public class Photo {


    @NonNull
    @Expose
    @PrimaryKey
    @SerializedName("id")
    public String id = String.valueOf(Math.random());

    public String pageNumber;

    public Byte[] photo;

    public Photo() {
    }
}
