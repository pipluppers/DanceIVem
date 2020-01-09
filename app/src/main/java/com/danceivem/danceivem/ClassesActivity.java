package com.danceivem.danceivem;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassesActivity extends AppCompatActivity {
    private ConstraintLayout mExpandableView;
    private Button mButton;
    private Button mVenmoButton;
    private CardView mCardView;
    private TextView mDetailsTextView;
    private Context mContext;

    // private members -------------------------------------
    private ArrayList<ClassCard> mClassCards = new ArrayList<>();
    private ClassAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    // end private members ---------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        mContext = this;

        // Get the intent that started this activity
        Intent intent = getIntent();
        int position = intent.getIntExtra(DatesActivity.POSITION, 0);

        CreateClassCards(position);
        BuildListView();
        CreateVenmoLink();
//
//        // Expands and contracts on click
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mConstraintLayout.getVisibility() == View.GONE) {
//                    TransitionManager.beginDelayedTransition(mCardView, new AutoTransition());
//                    mConstraintLayout.setVisibility(View.VISIBLE);
//                } else {
//                    TransitionManager.beginDelayedTransition(mCardView, new AutoTransition());
//                    mConstraintLayout.setVisibility(View.GONE);
//                }
//            }
//        });
    }

    // TODO: Get new images for the choreographers
    // TODO: Use a database for this
    public void CreateClassCards(int position) {
        if (position == 0) {
            mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Melissa", "Details 1"));
            mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Leilani", "Details 2"));
            mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Bri", "Details 3"));
        }
        else if (position == 1) {
            mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Melissa", "Details 4"));
            mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Leilani", "Details 5"));
            mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Bri", "Details 6"));
        }
    }

    public void BuildListView() {
        mRecyclerView = findViewById(R.id.activity_classes__RecyclerView);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ClassAdapter(mClassCards);
        mRecyclerView.setAdapter(mAdapter);

        mExpandableView = findViewById(R.id.expandableView);
        mButton = findViewById(R.id.classesButton);
        mCardView = findViewById(R.id.classesCardView);

        mAdapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Expand or collapse expandable view
                if (mExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(mCardView, new AutoTransition());
                    mExpandableView.setVisibility(View.VISIBLE);
                } else {
                    TransitionManager.beginDelayedTransition(mCardView, new AutoTransition());
                    mExpandableView.setVisibility(View.GONE);
                }
            }
        });
    }

    public void CreateVenmoLink()
    {
        mVenmoButton = findViewById(R.id.venmoButton);
        final String venmoPackageName = "com.Venmo";
        mVenmoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager packageManager = mContext.getPackageManager();
                Intent intent = new Intent();
                if (isVenmoInstalled(venmoPackageName, packageManager)) {
                    // Open Venmo app
                    try {
                        startActivity(new Intent(venmoPackageName));
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Open webpage
                    Uri webAddress = Uri.parse("https://venmo.com");

                    // Create the intent for the new activity
                    Intent goToVenmo = new Intent(Intent.ACTION_VIEW, webAddress);
                    if (goToVenmo.resolveActivity(mContext.getPackageManager()) != null) {
                        startActivity(goToVenmo);
                    }
                }

            }
        });
    }

    // Helper functions
    private Boolean isVenmoInstalled(String venmoPackageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(venmoPackageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
