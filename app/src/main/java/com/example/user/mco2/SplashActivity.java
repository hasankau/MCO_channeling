package com.example.user.mco2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView iv = (ImageView) findViewById(R.id.logo);
        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(1000);
        iv.startAnimation(animation1);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                    Intent home = new Intent(SplashActivity.this, StartActivity.class);

                    startActivity(home);
                    finish();


            }
        }, 1500);

    }
}

