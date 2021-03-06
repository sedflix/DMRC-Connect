package com.example.dmrcconnect;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private int SLEEP_TIMER = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

        // Setup views
        final ImageView splashImg = findViewById(R.id.splashLogo);

        // Initialize the animations
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        final Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);

        animateLogo(splashImg, animFadeIn, animFadeOut);
        animateLogo(splashImg, animFadeOut, animFadeIn);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashImg.startAnimation(animFadeOut);
            }
        },500 * (SLEEP_TIMER-1));

    }

    private void animateLogo(final View img, Animation animFadeIn, final Animation animFadeOut) {
        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img.startAnimation(animFadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }

    private class LogoLauncher extends Thread{
        public void run(){
            try {
                sleep(1000 * SLEEP_TIMER);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            SplashScreenActivity.this.finish();
        }

    }
}


