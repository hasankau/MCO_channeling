package com.example.user.mco2.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public class CheckInternet {

    public boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

}
