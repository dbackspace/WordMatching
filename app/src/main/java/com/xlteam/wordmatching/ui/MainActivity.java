package com.xlteam.wordmatching.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.database.DBController;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private DBController mDbController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashSet<String> listWord = new HashSet<>();
        listWord.add("aaa");
        listWord.add("a");
        mDbController = new DBController(this);
        mDbController.createDatabase();
        mDbController.open();

//        boolean exist = mDbController.checkWordInDB(searchWord);
        String res = mDbController.recommendWordNotInSet(listWord, 'a');
//        Toast.makeText(this, "Tồn tại hay không: " + exist, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Từ gợi ý: " + res, Toast.LENGTH_LONG).show();
        mDbController.close();
    }
}
