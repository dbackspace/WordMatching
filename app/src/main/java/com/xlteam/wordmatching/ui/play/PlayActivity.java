package com.xlteam.wordmatching.ui.play;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.database.DBController;
import com.xlteam.wordmatching.utils.CustomKeyboard;
import com.xlteam.wordmatching.utils.Utility;

import java.util.Arrays;
import java.util.HashSet;

public class PlayActivity extends AppCompatActivity {
    ImageView imgSend;
    EditText edtInputWord;
    RecyclerView rvWord;
    WordAdapter mWordAdapter;
    RelativeLayout rlSupport;
    HashSet<String> setData;
    DBController dbController;
    char prevLastCharacter = '_';
    TextView tvFirst, numberSupport, tvNotice;
    CustomKeyboard mCustomKeyboard;

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

        mCustomKeyboard = new CustomKeyboard(this, R.id.keyboardview, R.xml.hexkbd);
        mCustomKeyboard.registerEditText(R.id.edtInputWord);

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = tvFirst.getText().toString() + edtInputWord.getText().toString();
                if (word.isEmpty()) return;
                sendWord(word.toLowerCase());
            }
        });

        rlSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setData.size() == 0) {
                    showNotice("Bạn phải bắt đầu trò chơi...");
                } else {
                    String wordRecommend = dbController.recommendWordNotInSet(setData, prevLastCharacter);
                    sendWord(wordRecommend);
                }
            }
        });
        edtInputWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    Utility.setEnableView(imgSend, false);
                } else {
                    Utility.setEnableView(imgSend, true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Utility.setEnableView(imgSend, false);
    }

    void sendWord(String word) {
        if (!setData.contains(word) && dbController.checkWordInDB(word)) {
            setData.add(word);
            String wordBot = dbController.recommendWordNotInSet(setData, word.charAt(word.length() - 1));
            prevLastCharacter = wordBot.charAt(wordBot.length() - 1);
            setData.add(wordBot);
            mWordAdapter.updateData(Arrays.asList(word, wordBot));
            rvWord.scrollToPosition(setData.size() - 1);
            tvFirst.setText(String.valueOf(prevLastCharacter));
            edtInputWord.setText("");
            Utility.setEnableView(imgSend, false);
        }
    }

    void findViewById() {
        rlSupport = findViewById(R.id.rlSupport);
        imgSend = findViewById(R.id.imgSend);
        edtInputWord = findViewById(R.id.edtInputWord);
        rvWord = findViewById(R.id.rvWord);
        tvFirst = findViewById(R.id.tvFirst);
        numberSupport = findViewById(R.id.numberSupport);
        tvNotice = findViewById(R.id.tvNotice);
    }

    void showNotice(String notice) {
        tvNotice.setText(notice);
        tvNotice.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvNotice.setVisibility(View.GONE);
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        if (mCustomKeyboard.isCustomKeyboardVisible())
            mCustomKeyboard.hideCustomKeyboard();
        else
            this.finish();
    }
}