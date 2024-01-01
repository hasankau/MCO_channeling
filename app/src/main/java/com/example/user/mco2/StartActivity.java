package com.example.user.mco2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mco2.Adaptors.DoctorsAdaptor;
import com.example.user.mco2.Controller.ControllerLists;
import com.example.user.mco2.modal.DoctorObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;

public class StartActivity extends AppCompatActivity {

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        StrictMode.ThreadPolicy sm = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sm);

        session = new Session(getApplicationContext());

        if(session.isLoggedIn()) {
            Intent home = new Intent(StartActivity.this, LoginActivity.class);

            startActivity(home);
            finish();
        }else {
districts();
            hospitals();
            specials();
            Intent home = new Intent(StartActivity.this, AdminHomeActivity.class);

            startActivity(home);
            finish();
        }

        Button log = (Button) findViewById(R.id.button2);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent home = new Intent(StartActivity.this, LoginActivity.class);

            startActivity(home);
            finish();
            }
        });

        TextView t1 = (TextView) findViewById(R.id.textView2);
        TextView t2 = (TextView) findViewById(R.id.textView3);
        Button b = (Button) findViewById(R.id.button2);



        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(1000);

        t1.startAnimation(animation1);
        t2.startAnimation(animation1);
        b.startAnimation(animation1);

    }






    private void districts(){



        try {
            SoapObject request = new SoapObject(WebServices.NAMESPACE, WebServices.LoadDistricts.method);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(WebServices.URL, WebServices.timeout);


            androidHttpTransport.call(WebServices.LoadDistricts.action, envelope);

            JSONArray jr = new JSONArray((envelope.getResponse().toString()));

            int lengthJsonArr = jr.length();

            ControllerLists.dislist.clear();

            HashMap<Integer, String> cats = new HashMap();
            for (int i = 0; i < lengthJsonArr; i++) {

                JSONObject job = jr.getJSONObject(i);

                String dis = job.optString("name");

                ControllerLists.dislist.add(dis);
            }


        } catch (Exception e) {

            e.printStackTrace();
//            Toast.makeText(context, "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();

        }
    }

    private void hospitals(){



        try {
            SoapObject request = new SoapObject(WebServices.NAMESPACE, WebServices.LoadHospitals.method);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(WebServices.URL, WebServices.timeout);


            androidHttpTransport.call(WebServices.LoadHospitals.action, envelope);

            JSONArray jr = new JSONArray((envelope.getResponse().toString()));

            int lengthJsonArr = jr.length();

            ControllerLists.hoslist.clear();

            HashMap<Integer, String> cats = new HashMap();
            for (int i = 0; i < lengthJsonArr; i++) {

                JSONObject job = jr.getJSONObject(i);

                String dis = job.optString("name");

                ControllerLists.hoslist.add(dis);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    private void specials(){



        try {
            SoapObject request = new SoapObject(WebServices.NAMESPACE, WebServices.LoadSpecials.method);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(WebServices.URL, WebServices.timeout);


            androidHttpTransport.call(WebServices.LoadSpecials.action, envelope);

            JSONArray jr = new JSONArray((envelope.getResponse().toString()));

            int lengthJsonArr = jr.length();

            ControllerLists.speclist.clear();

            HashMap<Integer, String> cats = new HashMap();
            for (int i = 0; i < lengthJsonArr; i++) {

                JSONObject job = jr.getJSONObject(i);

                String dis = job.optString("name");

                ControllerLists.speclist.add(dis);
            }




        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();

        }
    }


}
