package com.watanya.archive.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.watanya.archive.data.model.Photo;
import com.watanya.archive.data.model.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Photo photo);

    @Update
    void update(Photo photo);

    @Delete
    void delete(Photo photo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Photo> photos);

    @Query("SELECT * FROM photos")
    Flowable<List<Photo>> loadAll();

    @Query("SELECT * FROM photos WHERE id IN (:ids)")
    Flowable<List<Photo>> loadAllByIds(List<String> ids);

    @Query("SELECT * FROM photos WHERE id LIKE :id")
    Single<Photo> findById(String id);
}
