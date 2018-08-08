package com.watanya.archive.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(
        tableName = "files"
)

public class File implements Serializable {

    @NonNull
    @Expose
    @PrimaryKey
    @SerializedName("id")
    public String id = String.valueOf(Math.random());

    @NonNull
    public String name = "";

    public String note;

    public String categoryId;
//    public Category category;

    public ArrayList<Paper> papers;

    public File() {
    }

    public File(@NonNull String name) {
        this.name = name;
        papers = new ArrayList<>();
        papers.add(new Paper("Paper 1"));
        papers.add(new Paper("Paper 2"));
        papers.add(new Paper("Paper 3"));
        papers.add(new Paper("Paper 4"));

    }

    public File(@NonNull String name, String note) {
        this.name = name;
        this.note = note;
        papers = new ArrayList<>();
        papers.add(new Paper("Paper 1"));
        papers.add(new Paper("Paper 2"));
        papers.add(new Paper("Paper 3"));
        papers.add(new Paper("Paper 4"));

    }
}
