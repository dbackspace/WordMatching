package com.xlteam.wordmatching.ui.playscreen;

import android.os.Bundle;
import android.os.Handler;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.database.DBController;
import com.xlteam.wordmatching.common.utils.CustomKeyboard;
import com.xlteam.wordmatching.ui.common.controllers.BaseActivity;

import java.util.HashSet;

public class PlayActivity extends BaseActivity
        implements PlayViewMvc.Listener {
    DBController dbController;
    char prevLastCharacter = '_';
    PlayViewMvc mViewMvc;

    CustomKeyboard mCustomKeyboard;
    Handler handlerNotice = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getControllerCompositionRoot().getViewMvcFactory().getPlayViewMvc(null);
        setContentView(mViewMvc.getRootView());

        dbController = getControllerCompositionRoot().getDbController();
        mCustomKeyboard = getControllerCompositionRoot().getCustomKeyboard();
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbController.createDatabase();
        dbController.open(); // tối về cho cái của nợ này vào controller cả
        mViewMvc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
    }

    void sendWord(String word) {
        if (getCheckedData().contains(word)) {
            showNotice(getString(R.string.exist_word));
        } else if (!dbController.checkWordInDB(word)) {
            showNotice(getString(R.string.undefine_word));
        } else {
            String wordBot = dbController.recommendWordNotInSet(getCheckedData(), word.charAt(word.length() - 1));
            prevLastCharacter = wordBot.charAt(wordBot.length() - 1);
            mViewMvc.updateAddedData(word, wordBot);
        }
    }

    @Override
    public void showNotice(String msgNotice) {
        handlerNotice.removeCallbacks(runnableNotice);
        handlerNotice.postDelayed(runnableNotice, 2000);
    }

    @Override
    public void onBackPressed() {
        if (mCustomKeyboard.isCustomKeyboardVisible())
            mCustomKeyboard.hideCustomKeyboard();
        else
            this.finish();
    }

    @Override
    public void sendWordClicked(String word, boolean isRecommend) {
        if (isRecommend) {
            String wordRecommend = dbController.recommendWordNotInSet(getCheckedData(), prevLastCharacter);
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
            mViewMvc.setTvNotice();
        }
    };

}