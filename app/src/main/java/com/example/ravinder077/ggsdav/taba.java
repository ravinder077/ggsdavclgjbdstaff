package com.example.ravinder077.ggsdav;

/**
 * Created by ravinder077 on 24-09-2017.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by ravinder077 on 23-09-2017.
 */

public class taba extends Fragment {


    int notifyID = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_main1, container, false);

    /*    Button button=(Button)view.findViewById(R.id.btnnotifications);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        *//*        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0,
                        new Intent(getActivity(), tabb.class), 0);

                Notification notification = new Notification.Builder(getActivity())
                        .setContentTitle("Title")
                        .setContentText("Message")
                        .setSmallIcon(R.drawable.a)

                        .setContentIntent(pendingIntent)
                        .build();

                NotificationManager notificationManager =
                        (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);*//*

                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(getContext())
                                .setSmallIcon(R.drawable.likethumb)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!")
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setPriority(Notification.PRIORITY_HIGH);



                NotificationManager notificationManager =
                        (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(notifyID++, mBuilder.build());

            }
        });
*/

        return view;

    }
}
