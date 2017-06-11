package com.example.android.firebasetut;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Abdulrhman on 11/06/2017.
 */

public class vollySingletone {
    private static  vollySingletone minstance;
    private static Context context;
    private RequestQueue requestQueue;

    private RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

   private vollySingletone(Context context) {
        this.context= context;
       requestQueue=getRequestQueue();
    }

    public static synchronized vollySingletone getMinstance(Context context){

        if (minstance==null){
            minstance= new vollySingletone(context);

        }
        return minstance;
    }
    public <T> void addToRequestQueue(Request<T>request){
        getRequestQueue().add(request);
    }
}
