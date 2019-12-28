package com.danceivem.danceivem;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

// Bind the data of the list view
public class ClassAdapter extends BaseExpandableListAdapter {

    // TODO: This information should come from a database in the future
    String[] classNames = {"Melissa", "Leilani", "Bri"};
    String[][] details = {{"Melissa's Class", "Venmo"}, {"Leilani's Class", "Venmo"}, {"Bri's Class", "Venmo"}};

    private Context mContext;
    private ArrayList<ClassCard> mClassCards;

    public ClassAdapter(Context context, ArrayList<ClassCard> classCards) {
        mContext = context;
        mClassCards = classCards;
    }

    @Override
    public int getGroupCount() {
        return mClassCards.size();
        //return classNames.length;
    }

    @Override
    public int getChildrenCount(int classPosition) {
        return details[classPosition].length;
    }

    @Override
    public Object getGroup(int classPosition) {
        return mClassCards.get(classPosition).getTeacherName();
        //return classNames[classPosition];
    }

    @Override
    public Object getChild(int classPosition, int detailsPosition) {
        return details[classPosition][detailsPosition];
    }

    @Override
    public long getGroupId(int classPosition) {
        return classPosition;
    }

    @Override
    public long getChildId(int classPosition, int detailsPosition) {
        return detailsPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // Class Names
    @Override
    public View getGroupView(int classPosition, boolean b, View view, ViewGroup viewGroup) {
        // Use CardView here
        CardView cardView = new CardView(mContext);

        TextView textView = new TextView(mContext);
        textView.setText(mClassCards.get(classPosition).getTeacherName());
        textView.setPadding(10,10,10,10);
        textView.setTextSize(30);

        cardView.addView(textView);
        return cardView;
    }

    // Make a card view with textview and button
    // Show Details text and venmo button at the very bottom center
    // Details and Venmo Button
    @Override
    public View getChildView(int classPosition, int detailsPosition, boolean isLastChild,
                             View view, ViewGroup viewGroup) {
        CardView cardView = new CardView(mContext);

        // Declared final because it is accessed within an inner class
        final TextView textView = new TextView(mContext);
        textView.setText(mClassCards.get(classPosition).getDetails());
        textView.setPadding(15,15,15,15);
        textView.setTextSize(20);

        int imageResource = mClassCards.get(classPosition).getImageResource();
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageResource);
        cardView.setBackgroundResource(R.drawable.dance_iv_em);

        Button button = new Button(mContext);
        button.setText(mContext.getResources().getString(R.string.venmo));
        button.setHeight(10);
        button.setWidth(20);
        button.setVisibility(View.VISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open Venmo
                String venmo = "https://venmo.com";
                Uri venmoAddress = Uri.parse(venmo);

                Intent openVenmo = new Intent(Intent.ACTION_VIEW, venmoAddress);
                if (openVenmo.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(openVenmo);
                }
            }
        });

        cardView.addView(textView);
        cardView.addView(button);

        return cardView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}