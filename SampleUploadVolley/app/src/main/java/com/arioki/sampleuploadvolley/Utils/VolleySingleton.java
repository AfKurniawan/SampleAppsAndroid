package com.arioki.sampleuploadvolley.Utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.arioki.sampleuploadvolley.Application.MyApplication;


/**
 * Created by -Andevindo- on 10/8/2015.
 */
public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());
    }



    public static VolleySingleton getInstance() {
        if(sInstance == null){
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }


}