package com.watanya.archive.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed Hassan Kamal on 31 January 2018.
 */
@Entity(
        tableName = "users"
)
public class User {

    public User() {
    }

    @NonNull
    @Expose
    @PrimaryKey
    @SerializedName("id")
    public String id = String.valueOf(Math.random());

    @Expose
    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    public String firstName;

    @Expose
    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    public String lastName;

    @Expose
    @SerializedName("email")
    @ColumnInfo(name = "email")
    public String mail;

    @Expose
    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    public String phoneNumber;

    @Expose
    @SerializedName("photo")
    public String photo;

    @Expose
    public String password;

    @Expose
    @SerializedName("token")
    public String accessToken;


    public User(String email, String password) {
        mail = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String phoneNumber, String mail, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
