package com.danceivem.danceivem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private ArrayList<ClassCard> mClassCards;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // static because it's an inner class of ClassAdapter
    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public ClassViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.classImageView);
            mTextView = itemView.findViewById(R.id.classTextView);

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

    public ClassAdapter(ArrayList<ClassCard> classCards) {
        mClassCards = classCards;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dates, parent, false);
        return new ClassViewHolder(view, mListener);
    }

    // Called by RecyclerView to display the data at the input position
    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassCard currentCard = mClassCards.get(position);

        holder.mImageView.setImageResource(currentCard.getImageResource());
        holder.mTextView.setText(currentCard.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return mClassCards.size();
    }
}