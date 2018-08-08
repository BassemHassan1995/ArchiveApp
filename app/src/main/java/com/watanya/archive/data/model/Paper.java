package com.watanya.archive.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(
        tableName = "papers"
)

public class Paper implements Serializable {


    @NonNull
    @Expose
    @PrimaryKey
    @SerializedName("id")
    public String id = String.valueOf(Math.random());

    @NonNull
    public String title="";

    public String inNumber;

    public String outNumber;

    public ArrayList<Photo> photos;

    public Paper() {
    }

    public Paper(@NonNull String title) {
        this.title = title;
    }
}
