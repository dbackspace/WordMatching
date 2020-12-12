package com.xlteam.wordmatching.ui.common.controllers;

import androidx.appcompat.app.AppCompatActivity;

import com.xlteam.wordmatching.common.dependencyinjection.ActivityCompositionRoot;
import com.xlteam.wordmatching.common.dependencyinjection.ControllerCompositionRoot;
import com.xlteam.wordmatching.ui.common.CustomApplication;

public class BaseActivity extends AppCompatActivity {
    private ActivityCompositionRoot mActivityCompositionRoot;
    private ControllerCompositionRoot mControllerCompositionRoot;

    public ActivityCompositionRoot getActivityCompositionRoot() {
        if (mActivityCompositionRoot == null) {
            mActivityCompositionRoot = new ActivityCompositionRoot(
                    ((CustomApplication) getApplication()).getCompositionRoot(),
                    this
            );
        }
        return mActivityCompositionRoot;
    }

    protected ControllerCompositionRoot getControllerCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(getActivityCompositionRoot());
        }
        return mControllerCompositionRoot;
    }
}
