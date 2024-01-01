package com.example.user.mco2.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mco2.Controller.ControllerLists;
import com.example.user.mco2.DoctorViewActivity;
import com.example.user.mco2.R;
import com.example.user.mco2.modal.DoctorObject;
import com.example.user.mco2.modal.EventObject;

public class EventsAdaptor extends BaseAdapter {

    Context con;

    public EventsAdaptor(Context c){
        con = c;
    }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return ControllerLists.evlist.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return ControllerLists.evlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            // TODO Auto-generated method stub.

            final EventObject doc = ControllerLists.evlist.get(position);

            if (view == null) {

                LayoutInflater layoutInflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.event_list_item, null);
            }

            TextView prod_name = (TextView) view.findViewById(R.id.dp);
            prod_name.setText(doc.getTitle());
//
            TextView prod_price = (TextView) view.findViewById(R.id.num);
            prod_price.setText(doc.getAddress());

            TextView t = (TextView) view.findViewById(R.id.hos);
            t.setText(doc.getStart() +" - "+ doc.getEnd());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{

                        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
                        animation1.setDuration(1000);

                        v.startAnimation(animation1);

                        Intent vi = new Intent(con, DoctorViewActivity.class);
                        vi.putExtra("id", position);
                        vi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        con.startActivity(vi);

                    }catch (Exception e){
                        Toast.makeText(con, e.toString(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            });

            return view;


        }


    }