package com.watanya.archive.ui.base;


import com.watanya.archive.MyApplication;
import com.watanya.archive.data.local.db.AppDbHelper;
import com.watanya.archive.data.local.prefs.AppPreferencesHelper;

public class BaseApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppPreferencesHelper prefHelper = AppPreferencesHelper.getInstance();
        if (prefHelper == null)
            prefHelper = new AppPreferencesHelper(getContext());
        prefHelper.setApplicationLanguage(getContext(), prefHelper.getLanguage());

        AppDbHelper dbHelper = AppDbHelper.getInstance();
        if (dbHelper == null)
            dbHelper = new AppDbHelper();
    }
}
