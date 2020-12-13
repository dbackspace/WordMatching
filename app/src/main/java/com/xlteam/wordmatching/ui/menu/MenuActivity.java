package com.xlteam.wordmatching.ui.menu;

import android.os.Bundle;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.ui.common.controllers.BaseActivity;

public class MenuActivity extends BaseActivity {
    private MenuActivityController mMenuActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuViewMvc mViewMvc;

        mMenuActivityController = getControllerCompositionRoot().getMenuActivityController();
        mViewMvc = getControllerCompositionRoot().getViewMvcFactory().getMenuViewMvc(null);
        setContentView(mViewMvc.getRootView());

        mMenuActivityController.bindView(mViewMvc);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mMenuActivityController.onStart();
    }

    //
    @Override
    protected void onStop() {
        super.onStop();
        mMenuActivityController.onStop();
    }
}
