package com.xlteam.wordmatching.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static com.xlteam.wordmatching.utils.AppConstant.DATABASE_NAME;
import static com.xlteam.wordmatching.utils.AppConstant.DB_PATH_SUFFIX;


public class DatabaseHelper {
    private Context context;

    public DatabaseHelper(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void checkCopyDB() {
        //private app
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                //   Toast.makeText(getContext(), "Copying ", Toast.LENGTH_LONG).show();
                CopyDataBaseFromAsset();
                Log.d("duc.nh3", "success");
                //Toast.makeText(getContext(), "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try {
            // System.out.println("vao");
            //  Toast.makeText(context, "Vao", Toast.LENGTH_SHORT).show();
            InputStream myInput;
            myInput = getContext().getAssets().open(DATABASE_NAME);
            String outFileName = getPathDB();
            File f = new File(getContext().getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            /* Close the streams */
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            Log.e("Loi", e.toString());
        }
    }

    private String getPathDB() {
        return getContext().getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public SQLiteDatabase getInstance() {
        //B1: Mo CSDL
        return context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
    }
}