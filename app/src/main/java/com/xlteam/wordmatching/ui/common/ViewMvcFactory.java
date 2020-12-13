package com.xlteam.wordmatching.ui.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.xlteam.wordmatching.ui.menu.MenuViewMvc;
import com.xlteam.wordmatching.ui.menu.MenuViewMvcImpl;
import com.xlteam.wordmatching.ui.playscreen.PlayViewMvc;
import com.xlteam.wordmatching.ui.playscreen.PlayViewMvcImpl;

public class ViewMvcFactory {
    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public PlayViewMvc getPlayViewMvc(@Nullable ViewGroup parent) {
        return new PlayViewMvcImpl(mLayoutInflater, parent);
    }

    public MenuViewMvc getMenuViewMvc(@Nullable ViewGroup parent) {
        return new MenuViewMvcImpl(mLayoutInflater, parent);
    }
}
