package com.watanya.archive.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import com.watanya.archive.data.interfaces.PreferencesInterface;
import com.watanya.archive.utils.AppConstants;

import java.util.Locale;

public class AppPreferencesHelper implements PreferencesInterface {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_APP_LANGUAGE = "PREF_KEY_APP_LANGUAGE";

    private static final String PREF_KEY_APP_VERSION = "PREF_KEY_APP_VERSION";

    private static AppPreferencesHelper helper = null;

    private final SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context) {
        helper = this;
        mPrefs = context.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
        if (mPrefs.getInt(PREF_KEY_APP_VERSION, -1) == -1)
            initializePreferences(context);
    }

    public static AppPreferencesHelper getInstance() {
        return helper;
    }

    private void initializePreferences(Context context) {
        int versionCode;
        String language;

        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            setAppVersion(versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        language = context.getResources().getConfiguration().locale.getLanguage();
        setLanguage(language);
        //TODO: set app locale into the current language
    }

    public void setApplicationLanguage(Context context, String language) {

        String currentLanguage = language != null ? language
                : context.getResources().getConfiguration().locale.getLanguage();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Configuration overrideConfiguration = context.getResources().getConfiguration();
            overrideConfiguration.setLocale(new Locale(currentLanguage));
            Resources resources = context.getResources();
            resources.updateConfiguration(overrideConfiguration, resources.getDisplayMetrics());

        } else {
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = new Locale(currentLanguage);
            res.updateConfiguration(conf, dm);
        }

        setLanguage(currentLanguage);
    }

    @Override
    public int getAppVersion() {
        return mPrefs.getInt(PREF_KEY_APP_VERSION, -1);
    }

    @Override
    public void setAppVersion(int versionCode) {
        mPrefs.edit().putInt(PREF_KEY_APP_VERSION, versionCode).apply();
    }

    @Override
    public String getLanguage() {
        return mPrefs.getString(PREF_KEY_APP_LANGUAGE, "ar");
    }

    @Override
    public void setLanguage(String language) {
        mPrefs.edit().putString(PREF_KEY_APP_LANGUAGE, language).apply();
    }

    @Override
    public void deleteAccessToken(){
        mPrefs.edit().remove(PREF_KEY_ACCESS_TOKEN).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN,null);
    }

    @Override
    public void setAccessToken(String token) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN,token).apply();
    }

}
