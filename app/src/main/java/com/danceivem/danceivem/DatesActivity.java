package com.danceivem.danceivem;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DatesActivity extends AppCompatActivity {

    // private members -------------------------------------
    private ArrayList<DateCard> mDateCards = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private DateAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    // end private members ---------------------------------

    public static final String POSITION = "com.danceivem.danceivem.POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        CreateDateCards();
        BuildRecyclerView();
    }

    // TODO: Continue adding to this list for more classes
    // TODO: Figure out how to incorporate this with a single SQL database
    // TODO: Get new images for the dates
    public void CreateDateCards() {
        mDateCards.add(new DateCard(R.drawable.dance_iv_em, "1/2/20"));
        mDateCards.add(new DateCard(R.drawable.dance_iv_em, "1/16/20"));
    }

    public void BuildRecyclerView() {
        mRecyclerView = findViewById(R.id.activity_dates__RecyclerView);
        // Set to true if we know Recycler View won't change in size
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DateAdapter(mDateCards);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        // Set up on-click event
        // TODO: Figure out how to set up the database with all information based on the position
        // TODO:    or the date when the card is clicked
        mAdapter.setOnItemClickListener(new DateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Start new Activity
                Intent intent = new Intent(DatesActivity.this, ClassesActivity.class);
                intent.putExtra(POSITION, position);
                startActivity(intent);
            }
        });
    }
}
