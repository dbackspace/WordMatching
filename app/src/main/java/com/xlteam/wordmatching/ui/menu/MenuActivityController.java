package com.xlteam.wordmatching.ui.menu;

import android.content.Context;
import android.content.Intent;

import com.xlteam.wordmatching.ui.playscreen.PlayActivity;

public class MenuActivityController implements MenuViewMvc.Listener {
    private MenuViewMvc mViewMvc;

    public void bindView(MenuViewMvc viewMvc) {
        mViewMvc = viewMvc;
    }

    public void onStart() {
        mViewMvc.registerListener(this);
    }

    public void onStop() {
        mViewMvc.unregisterListener(this);
    }

    @Override
    public void openLocalPlayMode(Context context) {
        context.startActivity(new Intent(context, PlayActivity.class));
    }

    @Override
    public void openOnlinePlayMode(Context context) {

    }
}
