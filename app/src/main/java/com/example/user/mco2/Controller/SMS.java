package com.example.user.mco2.Controller;

import android.telephony.SmsManager;


    public class SMS {

        public void send(String num, String msg){
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(num, null, msg, null, null);
        }}
