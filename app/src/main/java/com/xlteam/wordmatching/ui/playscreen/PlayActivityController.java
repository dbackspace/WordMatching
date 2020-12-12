package com.xlteam.wordmatching.ui.playscreen;

import android.os.Handler;
import android.view.View;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.database.DBController;

import java.util.HashSet;

public class PlayActivityController implements PlayViewMvc.Listener {
    private PlayViewMvc mViewMvc;
    private final DBController mDbController;
    char prevLastCharacter = '_';

    Handler handlerNotice = new Handler();

    public PlayActivityController(DBController dbController) {
        mDbController = dbController;
    }

    public void bindView(PlayViewMvc viewMvc) {
        mViewMvc = viewMvc;
    }

    public void onStart() {
        mViewMvc.registerListener(this);
    }

    public void onStop() {
        mViewMvc.unregisterListener(this);
    }

    void sendWord(String word) {
        if (getCheckedData().contains(word)) {
            showNotice(R.string.exist_word);
        } else if (!mDbController.checkWordInDB(word)) {
            showNotice(R.string.undefine_word);
        } else {
            String wordBot = mDbController.recommendWordNotInSet(getCheckedData(), word.charAt(word.length() - 1));
            prevLastCharacter = wordBot.charAt(wordBot.length() - 1);
            mViewMvc.updateAddedData(word, wordBot);
        }
    }

    @Override
    public void showNotice(int resIntMsg) {
        mViewMvc.setTvNotice(resIntMsg, View.VISIBLE);
        handlerNotice.removeCallbacks(runnableNotice);
        handlerNotice.postDelayed(runnableNotice, 2000);
    }


    @Override
    public void sendWordClicked(String word, boolean isRecommend) {
        if (isRecommend) {
            String wordRecommend = mDbController.recommendWordNotInSet(getCheckedData(), prevLastCharacter);
            sendWord(wordRecommend);
        } else {
            if (word.isEmpty()) return;
            sendWord(word.toLowerCase());
        }
    }

    private HashSet<String> getCheckedData() {
        return mViewMvc.getCheckedData();
    }

    Runnable runnableNotice = new Runnable() {
        @Override
        public void run() {
            mViewMvc.setTvNotice(R.string.notice_null, View.GONE);
        }
    };
}
