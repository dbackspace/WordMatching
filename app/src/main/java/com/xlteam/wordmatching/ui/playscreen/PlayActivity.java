package com.xlteam.wordmatching.ui.playscreen;

import android.os.Bundle;
import android.os.Handler;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.database.DBController;
import com.xlteam.wordmatching.common.utils.CustomKeyboard;
import com.xlteam.wordmatching.ui.common.controllers.BaseActivity;

import java.util.HashSet;

public class PlayActivity extends BaseActivity {

    private PlayActivityController mPlayActivityController;
    CustomKeyboard mCustomKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayViewMvc mViewMvc;

        mPlayActivityController = getControllerCompositionRoot().getPlayActivityController();
        mViewMvc = getControllerCompositionRoot().getViewMvcFactory().getPlayViewMvc(null);
        setContentView(mViewMvc.getRootView());

        mPlayActivityController.bindView(mViewMvc);
        mCustomKeyboard = getControllerCompositionRoot().getCustomKeyboard();
    }

    @Override
    public void onBackPressed() {
        if (mCustomKeyboard.isCustomKeyboardVisible())
            mCustomKeyboard.hideCustomKeyboard();
        else
            this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPlayActivityController.onStart();
    }
//
    @Override
    protected void onStop() {
        super.onStop();
        mPlayActivityController.onStop();
    }


}