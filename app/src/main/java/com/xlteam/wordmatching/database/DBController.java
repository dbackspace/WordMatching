package com.xlteam.wordmatching.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashSet;

public class DBController {
    private static final String TAG = "DataAdapterHelper";

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDbHelper;
    private static DBController INSTANCE;

    public static DBController getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DBController(context);
        }
        return INSTANCE;
    }

    private DBController(Context context) {
        mDbHelper = new DatabaseHelper(context);
        open();
    }

    public void open() throws SQLException {
        try {
            mDatabase = mDbHelper.openDataBase();
        } catch (SQLException mSQLException) {
            Log.d(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public boolean checkWordInDB(String searchWord) {
        String tableName = ("" + searchWord.charAt(0)).toUpperCase();
        String[] selectionArgs = {searchWord};
        String rawQuery = "select * from " + tableName + " where word =?";
        Cursor c = mDatabase.rawQuery(rawQuery, selectionArgs);
        boolean exist = (c.getCount() > 0);
        return exist;
    }

    public String recommendWordNotInSet(HashSet<String> setWord, char recommendChar) {
        String tableName = ("" + recommendChar).toUpperCase();
        String characterLower = ("" + recommendChar).toLowerCase();
        String rawQuery = "select * from " + tableName + " where word like " + "'" + characterLower + "%' order by random() limit 1";
        Cursor c;
        String res = "";
        do {
            c = mDatabase.rawQuery(rawQuery, null);
            c.moveToFirst();
            res = c.getString(c.getColumnIndex("word"));
        } while (setWord.contains(res));
        return res;
    }

    public void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }
}
