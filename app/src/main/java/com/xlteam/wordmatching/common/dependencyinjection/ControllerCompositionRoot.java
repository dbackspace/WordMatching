package com.xlteam.wordmatching.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;

import com.xlteam.wordmatching.common.utils.CustomKeyboard;
import com.xlteam.wordmatching.database.DBController;
import com.xlteam.wordmatching.ui.common.ViewMvcFactory;
import com.xlteam.wordmatching.ui.controller.PlayActivityController;

public class ControllerCompositionRoot {
    private final ActivityCompositionRoot mActivityCompositionRoot;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        mActivityCompositionRoot = activityCompositionRoot;
    }

    public FragmentActivity getActivity() {
        return mActivityCompositionRoot.getActivity();
    }

    private Context getContext() {
        return getActivity();
    }


    public PlayActivityController getPlayActivityController() {
        return new PlayActivityController();
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public DBController getDbController() {
        return DBController.getInstance(getContext());
    }

    public CustomKeyboard getCustomKeyboard() {
        return new CustomKeyboard(getActivity());
    }
}
