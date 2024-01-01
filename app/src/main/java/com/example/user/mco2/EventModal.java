package com.example.user.mco2;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.mco2.Adaptors.DoctorsAdaptor;
import com.example.user.mco2.Adaptors.EventsAdaptor;
import com.example.user.mco2.Controller.ControllerLists;
import com.example.user.mco2.R;
import com.example.user.mco2.modal.DoctorObject;
import com.example.user.mco2.modal.EventObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;

/**
 * Created by User on 11/16/2017.
 */

public class EventModal extends Dialog implements
            android.view.View.OnClickListener {

    String id;
    EventsAdaptor eventsAdaptor;


        public Activity c;
        public Dialog d;
        public Button no;
    ListView listView;

        public EventModal(Activity a, String id) {
            super(a);
            this.c = a;
            this.id = id;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.event_modal);
            no = (Button) findViewById(R.id.btn_no);
            listView = (ListView) findViewById(R.id.live);

            loadevents();

            no.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_no:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }



    private void loadevents(){

        SoapObject request = new SoapObject(WebServices.NAMESPACE, WebServices.AllEvents.method);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);



        try {

            HttpTransportSE androidHttpTransport = new HttpTransportSE(WebServices.URL, WebServices.timeout);


            androidHttpTransport.call(WebServices.AllEvents.action, envelope);

            JSONArray jr = new JSONArray((envelope.getResponse().toString()));

            int lengthJsonArr = jr.length();

            ControllerLists.evlist.clear();

            HashMap<Integer, String> cats = new HashMap();
            for (int i = 0; i < lengthJsonArr; i++) {


                JSONObject job = jr.getJSONObject(i);

                EventObject doc = new EventObject();
                doc.setId(job.optString("id"));
                doc.setTitle(job.optString("title"));
                doc.setAddress(job.optString("add"));
                doc.setLocation(job.optString("loc"));
                doc.setStart(job.optString("start"));
                doc.setEnd(job.optString("end"));
                doc.setDiscription(job.optString("disc"));
                doc.setDate(job.optString("date"));
                doc.setUser_id(job.optString("user"));
                doc.setStatus(job.optString("status"));

                ControllerLists.evlist.add(doc);
            }


            eventsAdaptor = new EventsAdaptor(c.getApplication().getApplicationContext());
            listView.setAdapter(eventsAdaptor);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(c.getApplication().getApplicationContext(), "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();
        }
    }


    }