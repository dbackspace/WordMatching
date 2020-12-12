package com.xlteam.wordmatching.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PrefUtils {
    public static void putInt(Context context, String key, String keyWord, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(keyWord, value);
        editor.apply();
    }

    public static void putString(Context context, String key, String keyWord, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(keyWord, value);
        editor.apply();
    }

    public static void putFloat(Context context, String key, String keyWord, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(keyWord, value);
        editor.apply();
    }

    public static void putLong(Context context, String key, String keyWord, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(keyWord, value);
        editor.apply();
    }

    public static void putBoolean(Context context, String key, String keyWord, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(keyWord, value);
        editor.apply();
    }

    public static int getInt(Context context, String key, String keyWord) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        if (sharedPreferences != null) return sharedPreferences.getInt(keyWord, 0);
        return 0;
    }

    public static String getString(Context context, String key, String keyWord) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        if (sharedPreferences != null) return sharedPreferences.getString(keyWord, "");
        return "";
    }

    public static float getFloat(Context context, String key, String keyWord) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        if (sharedPreferences != null) return sharedPreferences.getFloat(keyWord, 0);
        return 0;
    }

    public static long getLong(Context context, String key, String keyWord) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        if (sharedPreferences != null) return sharedPreferences.getLong(keyWord, 0);
        return 0;
    }

    public static boolean getBoolean(Context context, String key, String keyWord) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, MODE_PRIVATE);
        if (sharedPreferences != null) return sharedPreferences.getBoolean(keyWord, false);
        return false;
    }
}