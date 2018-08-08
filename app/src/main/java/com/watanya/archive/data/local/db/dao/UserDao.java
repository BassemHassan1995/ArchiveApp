package com.watanya.archive.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.watanya.archive.data.model.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users WHERE accessToken LIKE :token LIMIT 1")
    Flowable<User> findByToken(String token);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Query("SELECT * FROM users")
    Flowable<List<User>> loadAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    Flowable<List<User>> loadAllByIds(List<String> userIds);
}
