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
import android.widget.Toast;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private ArrayList<ClassCard> mClassCards;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context mContext;
    private OnItemClickListener mListener;

    public ClassAdapter(ArrayList<ClassCard> classCards) {
        mClassCards = classCards;

        // set initial expanded states to false
        for (int i = 0; i < mClassCards.size(); ++i) {
            expandState.append(i, false);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // Provides a reference to the views for each data item
    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ClassViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

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

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_classcards,
                parent, false);
        return new ClassViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassCard currentCard = mClassCards.get(position);

        holder.mTextView.setText(currentCard.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return mClassCards.size();
    }
}

//// Bind the data of the list view
//public class ClassAdapter extends BaseExpandableListAdapter {
//
//    // TODO: This information should come from a database in the future
//    String[] classNames = {"Melissa", "Leilani", "Bri"};
//    String[][] details = {{"Melissa's Class", "Venmo"}, {"Leilani's Class", "Venmo"}, {"Bri's Class", "Venmo"}};
//
//    private Context mContext;
//    private ArrayList<ClassCard> mClassCards;
//    private OnItemClickListener mListener;
//
//    public ClassAdapter(Context context, ArrayList<ClassCard> classCards) {
//        mContext = context;
//        mClassCards = classCards;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }
//
//    public static class ClassViewHolder extends RecyclerView.ViewHolder {
//        public ImageView mImageView;
//        public TextView mTextView;
//        public Button mButton;
//
//        public ClassViewHolder(View itemView, final OnItemClickListener listener) {
//            super(itemView);
//
//            mImageView = itemView.findViewById(R.id.classImageView);
//            mTextView = itemView.findViewById(R.id.classTextView);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getGroupCount() {
//        return mClassCards.size();
//        //return classNames.length;
//    }
//
//    @Override
//    public int getChildrenCount(int classPosition) {
//        return details[classPosition].length;
//    }
//
//    @Override
//    public Object getGroup(int classPosition) {
//        return mClassCards.get(classPosition).getTeacherName();
//        //return classNames[classPosition];
//    }
//
//    @Override
//    public Object getChild(int classPosition, int detailsPosition) {
//        return details[classPosition][detailsPosition];
//    }
//
//    @Override
//    public long getGroupId(int classPosition) {
//        return classPosition;
//    }
//
//    @Override
//    public long getChildId(int classPosition, int detailsPosition) {
//        return detailsPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    // Class Names
//    @Override
//    public View getGroupView(int classPosition, boolean b, View view, ViewGroup viewGroup) {
//        // Use CardView here
//        CardView cardView = new CardView(mContext);
//
//        TextView textView = new TextView(mContext);
//        textView.setText(mClassCards.get(classPosition).getTeacherName());
//        textView.setPadding(10,10,10,10);
//        textView.setTextSize(30);
//
//        cardView.addView(textView);
//        return cardView;
//    }
//
//    // Make a card view with textview and button
//    // Show Details text and venmo button at the very bottom center
//    // Details and Venmo Button
//    @Override
//    public View getChildView(int classPosition, int detailsPosition, boolean isLastChild,
//                             View view, ViewGroup viewGroup) {
//        CardView cardView = new CardView(mContext);
//
//        // Declared final because it is accessed within an inner class
//        final TextView textView = new TextView(mContext);
//        textView.setText(mClassCards.get(classPosition).getDetails());
//        textView.setPadding(15,15,15,15);
//        textView.setTextSize(20);
//
//        int imageResource = mClassCards.get(classPosition).getImageResource();
//        ImageView imageView = new ImageView(mContext);
//        imageView.setImageResource(imageResource);
//        cardView.setBackgroundResource(R.drawable.dance_iv_em);
//
//        Button button = new Button(mContext);
//        button.setText(mContext.getResources().getString(R.string.venmo));
//        button.setHeight(10);
//        button.setWidth(20);
//        button.setVisibility(View.VISIBLE);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Open Venmo to Melissa's page
//                String venmo = "https://venmo.com/Melissa-Barrera-13";
//                Uri venmoAddress = Uri.parse(venmo);
//
//                Intent openVenmo = new Intent(Intent.ACTION_VIEW, venmoAddress);
//                if (openVenmo.resolveActivity(mContext.getPackageManager()) != null) {
//                    mContext.startActivity(openVenmo);
//                }
//            }
//        });
//
//        cardView.addView(textView);
//        cardView.addView(button);
//
//        return cardView;
//    }
//
//    @Override
//    public boolean isChildSelectable(int i, int i1) {
//        return false;
//    }
//}