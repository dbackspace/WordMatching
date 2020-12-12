package com.xlteam.wordmatching.common.utils;

import android.view.View;

public class Utility {
    public static void setEnableView(View view, boolean enable) {
        if (enable) {
            view.setEnabled(true);
            view.setAlpha(1f);
        } else {
            view.setEnabled(false);
            view.setAlpha(0.3f);
        }
    }
}
