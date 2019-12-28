package com.danceivem.danceivem;

public class ClassCard {
    private int mImageResource;
    private String mTeacherName;
    private String mDetails;

    public ClassCard(int imageResource, String teacherName, String details) {
        mImageResource = imageResource;
        mTeacherName = teacherName;
        mDetails = details;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getTeacherName() {
        return mTeacherName;
    }

    public String getDetails() { return mDetails; }
}
