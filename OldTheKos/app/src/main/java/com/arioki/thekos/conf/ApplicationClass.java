package com.arioki.thekos.conf;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by arioki on 11/19/2016.
 */

public class ApplicationClass extends Application{
    private static ApplicationClass sInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate(){
        super.onCreate();
        mRequestQueue  = Volley.newRequestQueue(this);
        sInstance = this;
    }

    public synchronized static ApplicationClass getInstance(){
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public void addToRequestQueue(Request request){
        mRequestQueue.add(request);
    }
}
