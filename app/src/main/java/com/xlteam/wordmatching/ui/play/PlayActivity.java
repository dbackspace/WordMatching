package com.xlteam.wordmatching.ui.play;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.database.DBController;

import java.util.Arrays;
import java.util.HashSet;

public class PlayActivity extends AppCompatActivity {
    ImageView imgSupport, imgSend;
    EditText edtInputWord;
    RecyclerView rvWord;
    WordAdapter mWordAdapter;
    HashSet<String> setData;
    DBController dbController;
    char prevLastCharacter = '_';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        setData = new HashSet<>(); //get data cũ hoặc khởi tạo
        dbController = new DBController(this);
        dbController.createDatabase();
        dbController.open(); // tối về cho cái của nợ này vào controller cả
        findViewById();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvWord.setLayoutManager(linearLayoutManager);
        mWordAdapter = new WordAdapter(setData, 2, 0);
        rvWord.setAdapter(mWordAdapter);

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = edtInputWord.getText().toString();
                if (prevLastCharacter == '_') {
                    prevLastCharacter = word.charAt(word.length() - 1);
                }
                if (word.charAt(0) == prevLastCharacter && !setData.contains(word) && dbController.checkWordInDB(word)) {
                    setData.add(word);
                    String wordBot = dbController.recommendWordNotInSet(setData, word.charAt(word.length() - 1));
                    prevLastCharacter = wordBot.charAt(wordBot.length() - 1);
                    setData.add(wordBot);
                    mWordAdapter.updateData(Arrays.asList(word, wordBot));
                    rvWord.scrollToPosition(setData.size() - 1);
                    prevLastCharacter = wordBot.charAt(wordBot.length() - 1);
                    edtInputWord.setText(prevLastCharacter);
                    edtInputWord.requestFocus();
                }
            }
        });
    }

    void findViewById() {
        imgSupport = findViewById(R.id.imgSupport);
        imgSend = findViewById(R.id.imgSend);
        edtInputWord = findViewById(R.id.edtInputWord);
        rvWord = findViewById(R.id.rvWord);
    }
}