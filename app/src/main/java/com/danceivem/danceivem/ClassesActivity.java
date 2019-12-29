package com.danceivem.danceivem;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassesActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;

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
