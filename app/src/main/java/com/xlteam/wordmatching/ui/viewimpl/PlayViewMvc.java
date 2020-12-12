package com.xlteam.wordmatching.ui.viewimpl;

import com.xlteam.wordmatching.ui.common.views.ObservableViewMvc;

import java.util.HashSet;

public interface PlayViewMvc extends ObservableViewMvc<PlayViewMvc.Listener> {
    interface Listener {
        void sendWordClicked(String word, boolean isRecommend);

        void showNotice(String msgNotice);
    }

    HashSet<String> getCheckedData();

    void updateAddedData(String word, String wordBot);

    void setTvNotice();
}
