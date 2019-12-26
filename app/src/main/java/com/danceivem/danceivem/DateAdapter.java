package com.danceivem.danceivem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {
    private ArrayList<DateCard> mDateCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // static because it's an inner class of DateAdapter
    public static class DateViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public DateViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.dateImageView);
            mTextView = itemView.findViewById(R.id.dateTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public DateAdapter(ArrayList<DateCard> dateCards) {
        mDateCards = dateCards;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dates, parent, false);
        DateViewHolder dvh = new DateViewHolder(view, mListener);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        DateCard currentCard = mDateCards.get(position);

        holder.mImageView.setImageResource(currentCard.getImageResource());
        holder.mTextView.setText(currentCard.getDate());
    }

    @Override
    public int getItemCount() {
        return mDateCards.size();
    }
}
