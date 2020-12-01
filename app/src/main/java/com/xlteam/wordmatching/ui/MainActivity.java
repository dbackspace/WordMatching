package com.xlteam.wordmatching.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.database.DBController;

public class MainActivity extends AppCompatActivity {
    private DBController mDbController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbController = new DBController(this);
        mDbController.createDatabase();
        mDbController.open();
        String searchWord = "aaa";
        boolean exist = mDbController.checkWordInDB(searchWord);
        mDbController.close();
        Toast.makeText(this, "Tồn tại hay không: " + exist, Toast.LENGTH_LONG).show();
    }
}
