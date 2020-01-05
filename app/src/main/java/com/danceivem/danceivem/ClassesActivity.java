package com.danceivem.danceivem;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassesActivity extends AppCompatActivity {

    // Need a class card with
    //  - the image
    //  - the details
    //  - the choreographer's name


    private ExpandableListView mExpandableListView;
    private ConstraintLayout mConstraintLayout;
    private Button mButton;
    private CardView mCardView;

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

        // Get the intent that started this activity
        Intent intent = getIntent();
        int position = intent.getIntExtra(DatesActivity.POSITION, 0);

        CreateClassCards(position);
        BuildListView();

        mConstraintLayout = findViewById(R.id.expandableView);
        mButton = findViewById(R.id.classesButton);
        mCardView = findViewById(R.id.classesCardView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mConstraintLayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(mCardView, new AutoTransition());
                    mConstraintLayout.setVisibility(View.VISIBLE);
                } else {
                    TransitionManager.beginDelayedTransition(mCardView, new AutoTransition());
                    mConstraintLayout.setVisibility(View.GONE);
                }
            }
        });
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

        // Set up on-click event
        mAdapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Expand the class card at this position
            }
        });
    }
}
