package com.xlteam.wordmatching.ui.playscreen;

import com.xlteam.wordmatching.ui.common.views.ObservableViewMvc;

import java.util.HashSet;

public interface PlayViewMvc extends ObservableViewMvc<PlayViewMvc.Listener> {
    interface Listener {
        void sendWordClicked(String word, boolean isRecommend);

        void showNotice(int resIntMsg);
    }

    HashSet<String> getCheckedData();

    void updateAddedData(String word, String wordBot);

    void setTvNotice(int resIntMsg, int visibleType);
}
