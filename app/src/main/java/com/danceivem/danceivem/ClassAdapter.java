package com.danceivem.danceivem;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private ArrayList<ClassCard> mClassCards;
    private Context mContext;
    private OnItemClickListener mListener;

    public ClassAdapter(ArrayList<ClassCard> classCards) {
        mClassCards = classCards;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // Provides a reference to the views for each data item
    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        private TextView mTeacherNameTextView;
        private TextView mDetailsTextVIew;

        public ClassViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            mTeacherNameTextView = itemView.findViewById(R.id.teacherNameTextView);
            mDetailsTextVIew = itemView.findViewById(R.id.detailsTextView);

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

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_classescards,
                parent, false);
        return new ClassViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassCard currentCard = mClassCards.get(position);

        holder.mTeacherNameTextView.setText(currentCard.getTeacherName());
        holder.mDetailsTextVIew.setText(currentCard.getDetails());
    }

    @Override
    public int getItemCount() {
        return mClassCards.size();
    }
}
