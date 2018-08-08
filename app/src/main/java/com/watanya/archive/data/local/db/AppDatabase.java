package com.watanya.archive.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.watanya.archive.data.local.db.dao.CategoryDao;
import com.watanya.archive.data.local.db.dao.FileDao;
import com.watanya.archive.data.local.db.dao.PaperDao;
import com.watanya.archive.data.local.db.dao.PhotoDao;
import com.watanya.archive.data.local.db.dao.UserDao;
import com.watanya.archive.data.model.Category;
import com.watanya.archive.data.model.File;
import com.watanya.archive.data.model.Paper;
import com.watanya.archive.data.model.Photo;
import com.watanya.archive.data.model.User;
import com.watanya.archive.ui.base.BaseApplication;
import com.watanya.archive.utils.AppConstants;

@Database(entities = {User.class, Category.class, File.class, Paper.class, Photo.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract FileDao fileDao();
    public abstract PaperDao paperDao();
    public abstract PhotoDao photoDao();

    public static AppDatabase getRoomDatabase() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(BaseApplication.getContext(), AppDatabase.class, AppConstants.DB_NAME)
//                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
