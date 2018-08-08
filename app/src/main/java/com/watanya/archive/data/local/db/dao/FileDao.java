package com.watanya.archive.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.watanya.archive.data.model.File;
import com.watanya.archive.data.model.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface FileDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(File file);

    @Update
    void update(File file);

    @Delete
    void delete(File file);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<File> files);

    @Query("SELECT * FROM files")
    Flowable<List<File>> loadAll();

    @Query("SELECT * FROM files WHERE id IN (:ids)")
    Flowable<List<File>> loadAllByIds(List<String> ids);

    @Query("SELECT * FROM files WHERE id LIKE :id")
    Single<File> findById(String id);
}
