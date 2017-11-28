package com.jzg.erp.utils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jzg.erp.app.JzgApp;


/**
 * @author: voiceofnet
 * email: pengkun@jingzhengu.com
 * phone:18101032717
 * @time: 2016/5/23 15:41
 * @desc:
 */
public class PrefUtils {
    private static String prefix = "erp"+"_";
    private static SharedPreferences sp;

    static {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(JzgApp.getAppContext());
        }
    }

    public static void remove(String key) {
        sp.edit().remove(prefix +  key).commit();
    }

    public static void putInt(String key, int value) {
        sp.edit().putInt(prefix +  key, value).commit();
    }

    public static void putLong(String key, long value) {
        sp.edit().putLong(prefix +  key, value).commit();
    }

    public static void putString(String key, String value) {
        sp.edit().putString(prefix +  key, value).commit();
    }

    public static void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(prefix +  key, value).commit();
    }

    public static void putFloat(String key, float value) {
        sp.edit().putFloat(prefix +  key, value).commit();
    }

    public static int getInt(String key) {
        return sp.getInt(prefix +  key, 0);
    }

    public static long getLong(String key) {
        return sp.getLong(prefix +  key, 0);
    }

    public static String getString(String key) {
        return sp.getString(prefix +  key, "");
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(prefix +  key, defaultValue);
    }

    public static float getFloat(String key) {
        return sp.getFloat(prefix +  key, 0);
    }

    public static void clear() {
        sp.edit().clear();
    }



}
