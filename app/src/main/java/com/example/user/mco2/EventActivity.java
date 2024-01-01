package com.example.user.mco2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.mco2.Controller.CheckInternet;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class EventActivity extends AppCompatActivity {


    EditText title, disc, loc, add, start, end, date, lar, lon, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        title = findViewBy


        StrictMode.ThreadPolicy sm = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sm);

        session = new Session(getApplicationContext());

        final CheckInternet network = new CheckInternet();



        Button canxel = (Button) findViewById(R.id.b1);

        canxel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent postads = new Intent(RegisterActivity.this, AdminHomeActivity.class);
                startActivity(postads);

            }
        });

        Button save = (Button) findViewById(R.id.b2);
        final ProgressDialog pDialog = new ProgressDialog(RegisterActivity.this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(network.isConnected(getApplicationContext())){

                    pDialog.setMessage("Loading..");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            addnewdoctor();
                            pDialog.dismiss();

                        }
                    }, 1500);


                }else{
                    Toast.makeText(getApplicationContext(), "Hmm.. your not connected to the internet", Toast.LENGTH_LONG).show();
                }

            }
        });






    private void addnewdoctor() {

        try {

            SoapObject request = new SoapObject(WebServices.NAMESPACE, WebServices.addnewevent.method);
            request.addProperty("title", name.getText().toString());
            request.addProperty("disc", dis.getSelectedItem().toString());
            request.addProperty("location", hos.getSelectedItem().toString());
            request.addProperty("address", spec.getSelectedItem().toString());
            request.addProperty("start", spec.getSelectedItem().toString());
            request.addProperty("end", spec.getSelectedItem().toString());
            request.addProperty("date", spec.getSelectedItem().toString());
            request.addProperty("lat", spec.getSelectedItem().toString());
            request.addProperty("lon", spec.getSelectedItem().toString());
            request.addProperty("status", spec.getSelectedItem().toString());
            request.addProperty("uid", spec.getSelectedItem().toString());
            request.addProperty("type", spec.getSelectedItem().toString());



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(WebServices.URL, WebServices.timeout);

            androidHttpTransport.call(WebServices.AddNewDoctor.action, envelope);

            JSONObject jr = new JSONObject(envelope.getResponse().toString());
//            Toast.makeText(getApplicationContext(), jr.toString(), Toast.LENGTH_LONG).show();

            if (jr.getString("data").equals("1")) {
                Intent home = new Intent(RegisterActivity.this, Doctors.class);
                startActivity(home);
                finish();

            } else {
                Toast.makeText(getApplicationContext(), "Could not add event", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

            e.printStackTrace();

        }
    }

    }
}
