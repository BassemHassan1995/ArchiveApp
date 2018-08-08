package com.watanya.archive.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.watanya.archive.data.model.Category;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Category> categories);

    @Query("SELECT * FROM categories")
    Flowable<List<Category>> loadAll();

    @Query("SELECT * FROM categories WHERE id IN (:ids)")
    Flowable<List<Category>> loadAllByIds(List<String> ids);

    @Query("SELECT * FROM categories WHERE id LIKE :id")
    Single<Category> findById(String id);
}
