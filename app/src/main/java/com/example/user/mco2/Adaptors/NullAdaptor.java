package com.example.user.mco2.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mco2.Controller.ControllerLists;
import com.example.user.mco2.R;
import com.example.user.mco2.modal.DoctorObject;

public class NullAdaptor extends BaseAdapter {

    Context con;

    public NullAdaptor(Context c){
        con = c;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ControllerLists.doclist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return ControllerLists.doclist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        // TODO Auto-generated method stub.

        final DoctorObject doc = ControllerLists.doclist.get(position);

        if (view == null) {

            LayoutInflater layoutInflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.doctor_list_item_null, null);
        }

        return view;


    }


}