package com.example.user.mco2;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Session session;
    EditText email, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy sm = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sm);

        session = new Session(getApplicationContext());
        email = (EditText) findViewById(R.id.email);
        pw = (EditText) findViewById(R.id.pw);

        Button log = (Button) findViewById(R.id.log);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (email.getText().toString().trim().length() == 0) {
                    email.setError("Empty!");
                } else if (email.getText().toString().trim().length() == 0) {
                    pw.setError("Empty!");
                } else {

                    //


                }

            }
        });




    }
}
