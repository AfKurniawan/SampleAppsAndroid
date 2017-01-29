package com.arioki.thekos;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Splash extends AppCompatActivity {

    private FirebaseAnalytics firebaseAnalytics;
    private static final int SPLASH_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                Splash.this.finish();
            }
        }, SPLASH_TIME);
    }
}
