package com.xlteam.wordmatching.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper {

    private static String TAG = "WordDB";
    private static String DB_NAME = "WordDb26Tables";
    private final File DB_FILE;
    private final Context mContext;


    public DatabaseHelper(Context context) {
        DB_FILE = context.getDatabasePath(DB_NAME);
        this.mContext = context;
    }

    public void createDataBase() {
        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            try {
                copyDataBase();
                Log.d(TAG, "createDataBase database created");
            } catch (IOException e) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_FILE.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    private void copyDataBase() throws IOException {
        Log.d(TAG, "copyDatabase");
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        Log.d(TAG, "opened");
        OutputStream mOutput = new FileOutputStream(DB_FILE);
        Log.d(TAG, "created file");
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    private boolean checkDataBase() {
        return DB_FILE.exists();
    }
}
