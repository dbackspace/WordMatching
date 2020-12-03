package com.xlteam.wordmatching.ui.play;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xlteam.wordmatching.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter {
    private final int TYPE_SENT = 0;
    private final int TYPE_RECEIVED = 1;
    private ArrayList<String> mWords;
    private int maxUser;
    private int numberUser;

    WordAdapter(HashSet<String> words, int maxUser, int numberUser) {
        this.maxUser = maxUser;
        this.numberUser = numberUser;
        mWords = new ArrayList<>();
        mWords.addAll(words);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word_sent, parent, false);
            return new SentViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word_received, parent, false);
            return new ReceivedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_SENT) {
            SentViewHolder sentViewHolder = (SentViewHolder) holder;
            sentViewHolder.tvWord.setText(mWords.get(position));
        } else {
            ReceivedViewHolder receivedViewHolder = (ReceivedViewHolder) holder;
            receivedViewHolder.tvWord.setText(mWords.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % maxUser == numberUser) return TYPE_SENT;
        return TYPE_RECEIVED;
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public void updateData(List<String> listWord) {
        mWords.addAll(listWord);
        notifyDataSetChanged();
    }

    static class SentViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord;

        SentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tvWord);
        }
    }

    static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord;

        ReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tvWord);
        }
    }
}
