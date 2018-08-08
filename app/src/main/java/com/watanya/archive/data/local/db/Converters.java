package com.watanya.archive.data.local.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.watanya.archive.data.model.File;
import com.watanya.archive.data.model.Paper;
import com.watanya.archive.data.model.Photo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ahmed Hassan Kamal on 05 March 2018.
 */
public class Converters {

    //ArrayList<String> to String
    @TypeConverter
    public static ArrayList<String> toStringList(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromStringList(ArrayList<String> list) {
        return new Gson().toJson(list);
    }

    //Data to Long
    @TypeConverter
    public static Long fromDate(Date date) {
        return (date == null ? null : date.getTime());
    }

    @TypeConverter
    public static Date toDate(Long millisSinceEpoch) {
        return (millisSinceEpoch == null ? null : new Date(millisSinceEpoch));
    }

    //ArrayList<Paper> to String
    @TypeConverter
    public static ArrayList<Paper> toPaperList(String value) {
        Type listType = new TypeToken<ArrayList<Paper>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromPaperList(ArrayList<Paper> papers) {
        return new Gson().toJson(papers);
    }

    //ArrayList<Photo> to String
    @TypeConverter
    public static ArrayList<Photo> toPhotoList(String value) {
        Type listType = new TypeToken<ArrayList<Photo>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromPhotoList(ArrayList<Photo> photos) {
        return new Gson().toJson(photos);
    }

    //ArrayList<File> to String
    @TypeConverter
    public static ArrayList<File> toFileList(String value) {
        Type listType = new TypeToken<ArrayList<File>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromFileList(ArrayList<File> files) {
        return new Gson().toJson(files);
    }


    //ArrayList<File> to String
    @TypeConverter
    public static Byte[] toByteArray(String value) {
        Type listType = new TypeToken<Byte[]>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromByteArray(Byte[] photo) {
        return new Gson().toJson(photo);
    }


}
