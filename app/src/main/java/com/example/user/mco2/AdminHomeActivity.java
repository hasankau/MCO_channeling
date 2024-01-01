package com.example.user.mco2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class AdminHomeActivity extends AppCompatActivity {



    AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        session = new Session(getApplicationContext());

        animation1.setDuration(1000);


    }


    public void doctors(View view){
        view.startAnimation(animation1);
        Intent vi = new Intent(AdminHomeActivity.this, Doctors.class);
        startActivity(vi);
    }

    public void newdoctor(View view){
        view.startAnimation(animation1);
        Intent vi = new Intent(AdminHomeActivity.this, RegisterActivity.class);
        startActivity(vi);
    }


    public void newevent(View view){
        view.startAnimation(animation1);
        Intent vi = new Intent(AdminHomeActivity.this, EventActivity.class);
        startActivity(vi);
    }

    public void searchevents(View view){
        view.startAnimation(animation1);
        Intent vi = new Intent(AdminHomeActivity.this, SearchEventsActivity.class);
        startActivity(vi);
    }


    public void event(View view){
        view.startAnimation(animation1);
        Intent vi = new Intent(AdminHomeActivity.this, EventActivity.class);
        startActivity(vi);
    }
//
//    public void arr(View view){
//        view.startAnimation(animation1);
//        Intent vi = new Intent(Home1.this, Arrias.class);
//        startActivity(vi);
//    }
//
//    public void collected(View view){
//        view.startAnimation(animation1);
//        Intent vi = new Intent(Home1.this, Collected.class);
//        startActivity(vi);
//    }


}
