package com.xlteam.wordmatching.ui.menu;

import android.content.Context;

import com.xlteam.wordmatching.ui.common.views.ObservableViewMvc;

public interface MenuViewMvc extends ObservableViewMvc<MenuViewMvc.Listener> {
    interface Listener {
        void openLocalPlayMode(Context context);

        void openOnlinePlayMode(Context context);
    }

}
