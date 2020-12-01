package com.xlteam.wordmatching.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = "WordDB";
    private static String DB_NAME = "WordDb26Tables";
    private static int DB_VERSION = 1;
    private final File DB_FILE;
    private SQLiteDatabase mDataBase;
    private final Context mContext;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_FILE = context.getDatabasePath(DB_NAME);
        this.mContext = context;
    }

    public void createDataBase() {
        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
                Log.d(TAG, "createDataBase database created");
            } catch (IOException e) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    public boolean openDataBase() throws SQLException {
        // Log.v("DB_PATH", DB_FILE.getAbsolutePath());
        mDataBase = SQLiteDatabase.openDatabase(DB_FILE.getAbsolutePath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        // mDataBase = SQLiteDatabase.openDatabase(DB_FILE, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    public SQLiteDatabase getDatabase() {
        return mDataBase;
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

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        if(mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }
}
