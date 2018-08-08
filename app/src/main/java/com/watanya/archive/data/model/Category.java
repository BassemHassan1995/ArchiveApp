package com.watanya.archive.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(
        tableName = "categories"
)

public class Category implements Serializable {

    @NonNull
    @PrimaryKey
    public String id = String.valueOf(Math.random());

    @NonNull
    public String name = "";

    public ArrayList<File> files;

    public Category() {
    }

    public Category(@NonNull String name) {
        this.name = name;
        files = new ArrayList<>();
        files.add(new File(name + " File 1", "Important"));
        files.add(new File(name + " File 2", "Very Important"));
        files.add(new File(name + " File 3", "Urgent"));
        files.add(new File(name + " File 4", ""));
        files.add(new File(name + " File 5", "Not Important"));
    }

    public ArrayList<String> getFilesNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add("All Files");
        for (File f : files) {
            names.add(f.name);
        }
        return names;
    }

    ;
}
