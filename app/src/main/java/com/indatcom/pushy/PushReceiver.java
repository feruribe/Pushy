package com.indatcom.pushy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PushReceiver extends BroadcastReceiver
{
    MainActivity ma = new MainActivity();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // Create a test notification
        // (Use deprecated notification API for demonstration
        // purposes, to avoid having to import AppCompat into your project)

        String notificationTitle = "Notificacion Pushy";
        String notificationDesc = "Test notification";
        String mensaje= "Mensaje vacio";

        // Attempt to grab the message property from the payload
        if ( intent.getStringExtra("link") != null )
        {

            notificationDesc = intent.getStringExtra("link");
            mensaje = intent.getStringExtra("message");
        }

        // Create a test notification
        Notification notification = new Notification(android.R.drawable.ic_dialog_info, mensaje, System.currentTimeMillis());

        // Sound + vibrate + light
        notification.defaults = Notification.DEFAULT_ALL;

        // Dismisses when pressed
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        // Set notification click intent
        Intent intento = new Intent(context, Webview.class);
        intento.putExtra("NotificationMessage", notificationDesc);
        intento.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent notificationIntent = PendingIntent.getActivity(context, 0,intento, PendingIntent.FLAG_UPDATE_CURRENT);



        // Set title and desc
        notification.setLatestEventInfo(context, notificationTitle, mensaje, notificationIntent );

        // Get notification manager
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Dispatch the notification
        mNotificationManager.notify(0, notification);
    }


}