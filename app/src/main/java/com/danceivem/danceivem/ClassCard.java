package com.danceivem.danceivem;

public class ClassCard {
    private int mImageResource;
    private String mTeacherName;

    public ClassCard(int imageResource, String teacherName) {
        mImageResource = imageResource;
        mTeacherName = teacherName;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getTeacherName() {
        return mTeacherName;
    }
}
