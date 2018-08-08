package com.watanya.archive.data.interfaces;

public interface PreferencesInterface {

    int getAppVersion();
    void setAppVersion(int versionCode);
    String getLanguage();
    void setLanguage(String language);
    String getAccessToken();
    void setAccessToken(String token);
    void deleteAccessToken();
}
