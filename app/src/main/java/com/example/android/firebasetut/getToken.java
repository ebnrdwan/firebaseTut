package com.example.android.firebasetut;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Abdulrhman on 10/06/2017.
 */

public class getToken extends FirebaseInstanceIdService {
    String TOKENFLAG= String.valueOf(R.string.token);
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKENFLAG,token).commit();

        Log.d(getString(R.string.token),token);
        System.out.print(getString(R.string.token)+ token);

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}
