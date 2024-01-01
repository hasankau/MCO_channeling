package com.example.user.mco2;


public class WebServices {
    public static String IP = "10.184.161.131";
    public static String NAMESPACE = "http://MCO/";
    public static int timeout = 15000;
    public static String URL = "http://"+IP+":8080/MCO/NewWebService";

    public static class Doctors {
        public static String action = "http://MCO/getDocts";
        public static String method = "getDocts";
    }

    public static class SearchDocts {
        public static String action = "http://MCO/searchDocts";
        public static String method = "searchDocts";
    }

    public static class AddNewDoctor {
        public static String action = "http://MCO/addnewdoctor";
        public static String method = "addnewdoctor";
    }

    public static class LoadDistricts {
        public static String action = "http://MCO/loaddistricts";
        public static String method = "loaddistricts";
    }

    public static class LoadHospitals {
        public static String action = "http://MCO/loadhospitals";
        public static String method = "loadhospitals";
    }

    public static class LoadSpecials {
        public static String action = "http://MCO/loadspecials";
        public static String method = "loadspecials";
    }

    public static class signup {
        public static String action = "http://MCO/signup";
        public static String method = "signup";
    }


    public static class login {
        public static String action = "http://MCO/login";
        public static String method = "login";
    }


    public static class AllEvents {
        public static String action = "http://MCO/allEvents";
        public static String method = "allEvents";
    }

    public static class checkout {
        public static String action = "http://MCO/makeinvoice";
        public static String method = "makeinvoice";
    }

    public static class deletefromcart {
        public static String action = "http://webserv/deleteevent";
        public static String method = "deletefromcart";
    }


    public static class advert {
        public static String action = "http://webserv/advert";
        public static String method = "advert";
    }

    public static class invoice {
        public static String action = "http://webserv/getinvoice";
        public static String method = "getinvoice";
    }


    ublic static class addnewevent {
        public static String action = "http://webserv/addnewevent";
        public static String method = "addnewevent";
    }


    public static class invite {
        public static String action = "http://webserv/invite";
        public static String method = "invite";
    }



    public static class inviteStatus {
        public static String action = "http://webserv/inviteStatus";
        public static String method = "inviteStatus";
    }


    public static class eventStatus {
        public static String action = "http://webserv/eventStatus";
        public static String method = "eventStatus";
    }


    public static class newHospital {
        public static String action = "http://webserv/newHospital";
        public static String method = "newHospital";
    }


    public static class getMyPaymentHistory {
        public static String action = "http://webserv/getMyPaymentHistory";
        public static String method = "getMyPaymentHistory";
    }



}

