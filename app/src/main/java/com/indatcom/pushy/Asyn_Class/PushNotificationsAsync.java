package com.indatcom.pushy.Asyn_Class;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

import me.pushy.sdk.Pushy;

/**
 * Created by Ferbajoo on 17/05/2016.
 */
public class PushNotificationsAsync extends AsyncTask<Void, Void, String> {

    Context context;
    String registrationId;

    public PushNotificationsAsync(Context context) {
        this.context = context;
    }

    protected String doInBackground(Void... params)
    {
        try
        {
            // Acquire a unique registration ID for this device
          registrationId = Pushy.register(context);

            // Send the registration ID to your backend server and store it for later
            sendRegistrationIdToBackendServer(registrationId);
        }
        catch( Exception exc )
        {
            // Return exc to onPostExecute
            return "Error al obtener key";
        }

        // We're good
        return registrationId;
    }

    @Override
    protected void onPostExecute(String exc)
    {
        // Failed?
        if ( exc.equals("Error al obtener key") )
        {
            // Show error as toast message
            Log.e("Error:", exc);

            return;
        }

        // Succeeded, do something to alert the user
    }

    // Example implementation
    void sendRegistrationIdToBackendServer(String registrationId) throws Exception
    {
        // The URL to the function in your backend API that stores registration IDs
        URL sendRegIdRequest = new URL("https://0696ea072e6b91182f60479bfe64bdefaf5a90fda0d3f3e9d83f08f3015ffb7d/register/device?registration_id=" + registrationId);

        // Send the registration ID by executing the GET request
        sendRegIdRequest.openConnection();
    }
}