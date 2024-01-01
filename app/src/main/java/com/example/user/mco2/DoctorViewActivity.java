package com.example.user.mco2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.mco2.Controller.ControllerLists;
import com.example.user.mco2.modal.DoctorObject;

public class DoctorViewActivity extends AppCompatActivity {

    int pid;
    ImageView img;
    TextView name, spec, hos, dis;
    Session session;

    DoctorObject dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);


        session  = new Session(getApplicationContext());

        StrictMode.ThreadPolicy sm = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sm);

        pid = getIntent().getIntExtra("id", 1);



        dob = ControllerLists.doclist.get(pid);


        name = (TextView) findViewById(R.id.name);
        spec = (TextView) findViewById(R.id.special);
        hos = (TextView) findViewById(R.id.hospital);
        dis = (TextView) findViewById(R.id.district);


        name.setText("Name :        "+dob.getName());
        spec.setText("Specialist :  "+dob.getSpecial());
        hos.setText("Hospital :      "+dob.getHospital());
        dis.setText("District :      "+dob.getDistrict());


        Button in = (Button) findViewById(R.id.wtc);
        final ProgressDialog pDialog = new ProgressDialog(DoctorViewActivity.this);

        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("Loading..");
                pDialog.setCancelable(false);
                pDialog.show();
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        EventModal cdd=new EventModal(DoctorViewActivity.this, dob.getId());
                        cdd.show();
                        if(cdd.isShowing()){
                            pDialog.dismiss();
                        }

                        }
                }, 1500);



            }
        });
    }
}