package com.xlteam.wordmatching.ui.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.xlteam.wordmatching.ui.viewimpl.PlayViewMvc;
import com.xlteam.wordmatching.ui.viewimpl.PlayViewMvcImpl;

public class ViewMvcFactory {
    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public PlayViewMvc getPlayViewMvc(@Nullable ViewGroup parent) {
        return new PlayViewMvcImpl(mLayoutInflater, parent);
    }
}
