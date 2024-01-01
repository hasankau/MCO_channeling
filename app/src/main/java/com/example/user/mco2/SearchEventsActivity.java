package com.example.user.mco2;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.mco2.Adaptors.DoctorsAdaptor;
import com.example.user.mco2.Adaptors.EventsAdaptor;
import com.example.user.mco2.Adaptors.NullAdaptor;
import com.example.user.mco2.Controller.ControllerLists;
import com.example.user.mco2.modal.DoctorObject;
import com.example.user.mco2.modal.EventObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;

public class SearchEventsActivity extends AppCompatActivity {

    EventsAdaptor eventsAdaptor;
    NullAdaptor nullAdaptor;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_events);
        listView = (ListView) findViewById(R.id.evlist);

        loadevents();
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


            eventsAdaptor = new EventsAdaptor(this.getApplicationContext());
            listView.setAdapter(eventsAdaptor);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();
        }
    }


    private void searchdoctors(String key){

        SoapObject request = new SoapObject(WebServices.NAMESPACE, WebServices.SearchDocts.method);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        request.addProperty("key", key);
        envelope.setOutputSoapObject(request);

        try {

            HttpTransportSE androidHttpTransport = new HttpTransportSE(WebServices.URL, WebServices.timeout);


            androidHttpTransport.call(WebServices.SearchDocts.action, envelope);

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



            eventsAdaptor = new EventsAdaptor(this.getApplicationContext());
            listView.setAdapter(eventsAdaptor);



        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();
            shownull();
        }
    }



    private void shownull(){




        try {


            ControllerLists.evlist.clear();

            for (int i = 0; i < 4; i++) {

                EventObject doc = new EventObject();


                ControllerLists.evlist.add(doc);
            }



            nullAdaptor = new NullAdaptor(this.getApplicationContext());
            listView.setAdapter(nullAdaptor);

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();

        }
    }


}
