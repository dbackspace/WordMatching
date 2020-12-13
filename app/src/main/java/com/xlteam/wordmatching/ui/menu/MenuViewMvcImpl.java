package com.xlteam.wordmatching.ui.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xlteam.wordmatching.R;
import com.xlteam.wordmatching.ui.common.views.BaseObservableViewMvc;

public class MenuViewMvcImpl extends BaseObservableViewMvc<MenuViewMvc.Listener> implements MenuViewMvc {
    TextView tvLocal, tvOnline;

    public MenuViewMvcImpl(LayoutInflater layoutInflater, @Nullable ViewGroup parent) {
        setRootView(layoutInflater.inflate(R.layout.activity_menu, parent, false));
        bindingView();

        tvLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (MenuViewMvc.Listener listener : getListeners()) {
                    listener.openLocalPlayMode(getContext());
                }
            }
        });

        tvOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (MenuViewMvc.Listener listener : getListeners()) {
                    listener.openOnlinePlayMode(getContext());
                }
            }
        });
    }

    private void bindingView() {
        tvLocal = findViewById(R.id.tvLocal);
        tvOnline = findViewById(R.id.tvOnline);
    }
}
