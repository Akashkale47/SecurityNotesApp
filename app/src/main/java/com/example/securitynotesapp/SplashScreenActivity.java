package com.example.securitynotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView imageView_logo;
    TextView textView_tagLine;

    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        /*Initializing views*/
        imageView_logo = findViewById(R.id.iv_logo);
        textView_tagLine = findViewById(R.id.tv_tagline);
        /*Splash screen animation*/
        Animation splashScreenAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        imageView_logo.startAnimation(splashScreenAnimation);
        textView_tagLine.startAnimation(splashScreenAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, AuthenticationActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }

}

