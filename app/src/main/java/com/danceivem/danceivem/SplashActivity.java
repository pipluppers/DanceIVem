package com.danceivem.danceivem;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.lang.Runnable;

public class SplashActivity extends AppCompatActivity {

    public static String msg = "Android: ";

    // Called when app is launched
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Temporary
        startActivity(new Intent(this, DatesActivity.class));

        // Start the next activity after 8 seconds
//        (new Handler()).postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    startActivity(new Intent(SplashActivity.this, DatesActivity.class));
//                }
//            },8000);
    }

    // Called when app is about to come visible
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(msg, "The application has started.");
    }

    // Called when app becomes visible
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(msg, "Resuming");
    }

    // Called when another activity is taking focus
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(msg, "Pausing application");
    }

    // Called when the app is no longer visible
    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(msg, "Stopping application");
    }

    // Called just before the app is closed for good
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(msg, "Closing application");
    }
}
