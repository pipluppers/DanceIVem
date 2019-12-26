package com.danceivem.danceivem;

public class DateCard {
    private int mImageResource; // R.drawable.image is saved as image
    private String mDateText;

    public DateCard(int imageResource, String dateText) {
        mImageResource = imageResource;
        mDateText = dateText;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getDate() {
        return mDateText;
    }
}
