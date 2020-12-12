package com.xlteam.wordmatching.ui.playscreen;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.ui.common.views.BaseObservableViewMvc;

import java.util.Arrays;
import java.util.HashSet;

public class PlayViewMvcImpl extends BaseObservableViewMvc<PlayViewMvc.Listener>
        implements PlayViewMvc {
    private ImageView imgSend;
    private EditText edtInputWord;
    private TextView tvFirst, numberHelp, tvNotice;

    private RecyclerView rvWord;
    private RelativeLayout rlHelp;


    private HashSet<String> mCheckedData;
    private int help;
    private WordAdapter mWordAdapter;

    public PlayViewMvcImpl(LayoutInflater layoutInflater, @Nullable ViewGroup parent) {
        setRootView(layoutInflater.inflate(R.layout.activity_play, parent, false));
        bindingView();

        mCheckedData = new HashSet<>(); //get data cũ hoặc khởi tạo
        help = 10; //get data cũ

        if (help >= 10) numberHelp.setText("9+");
        else numberHelp.setText(String.valueOf(help));


        mWordAdapter = new WordAdapter(mCheckedData, 2, 0);
        rvWord.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWord.setAdapter(mWordAdapter);


        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    String word = tvFirst.getText().toString() +
                            edtInputWord.getText().toString();
                    listener.sendWordClicked(word, false);
                }
            }
        });

        rlHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (help == 0) {
                    showNotice(R.string.no_number_help);
                } else if (mCheckedData.size() == 0) {
                    showNotice(R.string.must_start_game);
                } else {
                    for (Listener listener : getListeners()) {
                        listener.sendWordClicked(null, true);
                    }
                    numberHelp.setText(String.valueOf(--help));
                }
            }
        });

        edtInputWord.addTextChangedListener(textWatcher);

        setEnableView(imgSend, false);
    }

    void showNotice(int no_number_help) {
        tvNotice.setText(getString(no_number_help));
        tvNotice.setVisibility(View.VISIBLE);
        for (Listener listener : getListeners()) {
            listener.showNotice(no_number_help);
        }
    }

    private void bindingView() {
        rlHelp = findViewById(R.id.rlHelp);
        imgSend = findViewById(R.id.imgSend);
        edtInputWord = findViewById(R.id.edtInputWord);
        rvWord = findViewById(R.id.rvWord);
        tvFirst = findViewById(R.id.tvFirst);
        numberHelp = findViewById(R.id.numberHelp);
        tvNotice = findViewById(R.id.tvNotice);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            setEnableView(imgSend, !TextUtils.isEmpty(s.toString().trim()));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void setEnableView(View view, boolean enable) {
        if (enable) {
            view.setEnabled(true);
            view.setAlpha(1f);
        } else {
            view.setEnabled(false);
            view.setAlpha(0.3f);
        }
    }

    @Override
    public HashSet<String> getCheckedData() {
        return mCheckedData;
    }

    @Override
    public void updateAddedData(String word, String wordBot){
        mCheckedData.add(word);
        mCheckedData.add(wordBot);
        mWordAdapter.updateData(Arrays.asList(word, wordBot));
        rvWord.scrollToPosition(mCheckedData.size() - 1);
        tvFirst.setText(String.valueOf(wordBot.charAt(wordBot.length() - 1)));
        edtInputWord.setText("");
        setEnableView(imgSend, false);
    }

    @Override
    public void setTvNotice(int resIntMsg, int visibleType) {
        tvNotice.setText(getString(resIntMsg));
        tvNotice.setVisibility(visibleType);
    }
}
