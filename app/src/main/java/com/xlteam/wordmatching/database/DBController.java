package com.xlteam.wordmatching.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBController {
    private static final String TAG = "DataAdapterHelper";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public DBController(Context context) {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public DBController createDatabase() {
        mDbHelper.createDataBase();
        return this;
    }

    public DBController open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDb = mDbHelper.getDatabase();
        } catch (SQLException mSQLException) {
            Log.d(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public boolean checkWordInDB(String searchWord) {
        String tableName = ("" + searchWord.charAt(0)).toUpperCase();
        String[] selectionArgs = {searchWord};
        String rawQuery = "select * from " + tableName + " where word =?";
        Cursor c = mDb.rawQuery(rawQuery, selectionArgs);
        boolean exist = (c.getCount() > 0);
        return exist;
    }

    public void close() {
        mDbHelper.close();
    }
}
