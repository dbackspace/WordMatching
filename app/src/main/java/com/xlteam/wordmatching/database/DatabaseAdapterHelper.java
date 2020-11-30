package com.xlteam.wordmatching.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseAdapterHelper {
    private static final String TAG = "DataAdapterHelper";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper2 mDbHelper;

    public DatabaseAdapterHelper(Context context) {
        this.mContext = context;
        mDbHelper = new DatabaseHelper2(mContext);
    }

    public DatabaseAdapterHelper createDatabase() {
        mDbHelper.createDataBase();
        return this;
    }

    public DatabaseAdapterHelper open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.d(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public String getKetQuaBoiBai(int index) {
        Log.d(TAG, "getKetQuaBoiBaiAdapter id =" + index);
        Cursor cursor = mDb.query("BoiBaiTay", new String[] {"result"}, "key = ?", new String[]{Integer.toString(index)}, null, null, null);
        Log.d(TAG, "cursor" + cursor.getCount());
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String getTenBai(int index) {
        Log.d(TAG, "getTenBaiAdapter id =" + index);
        Cursor cursor = mDb.query("BoiBaiTay", new String[] {"name"}, "key = ?", new String[]{Integer.toString(index)}, null, null, null);
        Log.d(TAG, "cursor" + cursor.getCount());
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public void close() {
        mDbHelper.close();
    }

    public int getNguHanh(int id) {
        Log.d(TAG, "getNguHanhAdapter id =" + id);
        Cursor cursor = mDb.query("NguHanh", new String[] {"menh"}, "_id = ?", new String[] {Integer.toString(id)}, null, null, null);
        Log.d(TAG, "cursor" + cursor.getCount());
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int getMenhNam(int year) {
        int id = (year % 9) + 1;
        Cursor cursor = mDb.query("Nam", new String[] {"quai"}, "_id = ?", new String[] {Integer.toString(id)}, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int getMenhNu(int year) {
        int id = (year % 9) + 1;
        Cursor cursor = mDb.query("Nu", new String[] {"quai"}, "_id = ?", new String[] {Integer.toString(id)}, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public String getQuai(int id) {
        Cursor cursor = mDb.query("quai", new String[] {"name"}, "_id = ?", new String[] {Integer.toString(id)}, null, null, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public Cursor getQue(int noiquai, int ngoaiquai) {
        Cursor cursor = mDb.query("QueDich", new String[] {"_id", "diem"}, "noiquai = ? AND ngoaiquai = ?", new String[] {Integer.toString(noiquai), Integer.toString(ngoaiquai)}, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }
}
