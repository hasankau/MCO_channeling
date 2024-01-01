package com.example.user.mco2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.user.mco2.Adaptors.DoctorsAdaptor;
import com.example.user.mco2.Adaptors.NullAdaptor;
import com.example.user.mco2.Controller.CheckInternet;
import com.example.user.mco2.Controller.ControllerLists;
import com.example.user.mco2.modal.DoctorObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;

public class Doctors extends AppCompatActivity {

    ListView listView;

    Context context;
    ListView li;
    ArrayAdapter adap;
    DoctorsAdaptor adaptor;
    SearchView ser;
    SQLiteDatabase db;
    String s = "";

    Session session;

    DoctorsAdaptor doctorsAdaptor;
    NullAdaptor nullAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        StrictMode.ThreadPolicy sm = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sm);

        listView = (ListView) findViewById(R.id.doclist);

        context = getApplicationContext();

        CheckInternet network = new CheckInternet();

        if(network.isConnected(getApplicationContext())){
            loadproducts();
        }else{
            Toast.makeText(context, "Hmm.. your not connected to the internet", Toast.LENGTH_LONG).show();
            shownull();
        }



        ser = (SearchView) findViewById(R.id.ser2);

        ser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                CheckInternet network = new CheckInternet();

                if(network.isConnected(getApplicationContext())){
                    searchdoctors(newText);
                }else{
                    Toast.makeText(context, "Hmm.. your not connected to the internet", Toast.LENGTH_LONG).show();
                    shownull();
                }

                return true;
            }
        });

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

            ControllerLists.doclist.clear();

            HashMap<Integer, String> cats = new HashMap();
            for (int i = 0; i < lengthJsonArr; i++) {

                JSONObject job = jr.getJSONObject(i);

                DoctorObject doc = new DoctorObject();
                doc.setName(job.optString("name"));
                doc.setDistrict(job.optString("district"));
                doc.setHospital(job.optString("hospital"));
                doc.setId(job.optString("id"));
                doc.setSpecial(job.optString("special"));

                ControllerLists.doclist.add(doc);
            }



            doctorsAdaptor = new DoctorsAdaptor(this.getApplicationContext());
            listView.setAdapter(doctorsAdaptor);



        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(context, "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();
            shownull();
        }
    }









    private void loadproducts(){

        SoapObject request = new SoapObject(WebServices.NAMESPACE, WebServices.Doctors.method);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);



        try {

            HttpTransportSE androidHttpTransport = new HttpTransportSE(WebServices.URL, WebServices.timeout);


            androidHttpTransport.call(WebServices.Doctors.action, envelope);

            JSONArray jr = new JSONArray((envelope.getResponse().toString()));

            int lengthJsonArr = jr.length();

            ControllerLists.doclist.clear();

            HashMap<Integer, String> cats = new HashMap();
            for (int i = 0; i < lengthJsonArr; i++) {


                JSONObject job = jr.getJSONObject(i);

                DoctorObject doc = new DoctorObject();
                doc.setName(job.optString("name"));
                doc.setDistrict(job.optString("district"));
                doc.setHospital(job.optString("hospital"));
                doc.setId(job.optString("id"));
                doc.setSpecial(job.optString("special"));

                ControllerLists.doclist.add(doc);
            }



            doctorsAdaptor = new DoctorsAdaptor(this.getApplicationContext());
            listView.setAdapter(doctorsAdaptor);

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(context, "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();
            shownull();
        }
    }





    private void shownull(){




        try {


            ControllerLists.doclist.clear();

            for (int i = 0; i < 4; i++) {

                DoctorObject doc = new DoctorObject();


                ControllerLists.doclist.add(doc);
            }



            nullAdaptor = new NullAdaptor(this.getApplicationContext());
            listView.setAdapter(nullAdaptor);

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(context, "Hmm.. error occured connecting to server", Toast.LENGTH_LONG).show();

        }
    }
}
