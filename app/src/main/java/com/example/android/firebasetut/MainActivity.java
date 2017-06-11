package com.example.android.firebasetut;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "http://10.0.2.2/fcmtut/fcmtut_insert.php";
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.token),token).commit();
        Intent intent = getIntent();
        String data = intent.getStringExtra("mes");
        TextView textView = (TextView) findViewById(R.id.textm);
        textView.setText(data);

        Log.v("TOKEN",token);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
                final String token = preferences.getString(getString(R.string.token),"");
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONESTAG",response);
                        Toast.makeText(MainActivity.this," success "+ response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"didint post token",Toast.LENGTH_SHORT).show();
                        Log.d("ERRORLOG",error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>map = new HashMap<String, String>();
                        map.put("fcm_token",token);
                        return map;
                    }
                };

                vollySingletone.getMinstance(MainActivity.this).addToRequestQueue(stringRequest);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN",token);
    }
}
