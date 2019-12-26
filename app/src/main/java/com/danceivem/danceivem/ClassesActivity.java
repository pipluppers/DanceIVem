package com.danceivem.danceivem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ClassesActivity extends AppCompatActivity {

    // private members -------------------------------------
    private ArrayList<ClassCard> mClassCards = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ClassAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    // end private members ---------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        // Get the intent that started this activity
        Intent intent = getIntent();
        int position = intent.getIntExtra(DatesActivity.POSITION, 0);

        // Based off this position, grab the information for the cards
        CreateClassCards();
        BuildRecyclerView();
    }

    // TODO: Get new images for the choreographers
    public void CreateClassCards() {
        mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Melissa"));
        mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Leilani"));
        mClassCards.add(new ClassCard(R.drawable.dance_iv_em, "Bri"));
    }

    public void BuildRecyclerView() {
        mRecyclerView = findViewById(R.id.activity_classes__RecyclerView);
        // Set to true if we know Recycler View won't change in size
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ClassAdapter(mClassCards);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
