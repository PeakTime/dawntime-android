package com.peaktime.dawntime;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LEESANGYUN on 2018-01-01.
 */

public class SharedPreferInstance {

    public static final String PREFERENCE_NAME = "dawntime_sharedPreference";
    private static SharedPreferInstance instance = null;
    private static Context mContext;
    private static SharedPreferences prefer;
    private static SharedPreferences.Editor editor;

    public static SharedPreferInstance getInstance(Context context) {
        mContext = context;

        if (instance == null) {
            instance = new SharedPreferInstance();
        }
        if (prefer == null) {
            prefer = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            editor = prefer.edit();
        }
        return instance;
    }

    public void putLoginPrefer(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putLockPrefer(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putNoticePrefer(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putBlindPreder(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getLoginPrefer(String key) {
        return prefer.getBoolean(key, false);
    }

    public Boolean getLockPrefer(String key) {
        return prefer.getBoolean(key, false);
    }

    public Boolean getNoticePrefer(String key) {
        return prefer.getBoolean(key, false);
    }

    public Boolean getBlindPrefer(String key) {
        return prefer.getBoolean(key, false);
    }
}
