package com.example.ravinder077.ggsdav;

/**
 * Created by ravinder077 on 24-09-2017.
 */

import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by ravinder077 on 23-09-2017.
 */

public class taba extends Fragment {


    int notifyID = 1;

    private String TAG = MainActivity.class.getSimpleName();
    private static final String endpoint = "http://timepasstoday.com/fetchcount.php";
    private TabAData images=null;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    private String dataoffset =null;
    private String notification=null;
    private String acti=null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


      final  View view = inflater.inflate(R.layout.activity_main1, container, false);

        LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.linearlayout_galleryid);
        pDialog = new ProgressDialog(getActivity());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),GallaryActivity.class);
                startActivity(i);


            }
        });


        LinearLayout linearLayout1=(LinearLayout)view.findViewById(R.id.linearlayoutnotificationid);
        pDialog = new ProgressDialog(getActivity());
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TabLayout tabs = (TabLayout) getActivity().findViewById(R.id.tab_layout);
                tabs.getTabAt(1).select();

            }
        });
        LinearLayout linearLayout2=(LinearLayout)view.findViewById(R.id.linearlayoutactivityid);
        pDialog = new ProgressDialog(getActivity());
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TabLayout tabs = (TabLayout) getActivity().findViewById(R.id.tab_layout);
                tabs.getTabAt(2).select();

            }
        });
        LinearLayout linearLayout3=(LinearLayout)view.findViewById(R.id.linearlayoutcontactusid);
        pDialog = new ProgressDialog(getActivity());
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
        pDialog.setMessage("Loading...");
        pDialog.show();
      // Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();
        JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.POST, endpoint,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                       // pDialog.hide();
                        try {

                        // Parsing json
                        JSONArray c = response.getJSONArray("tasks");
                        for (int i = 0; i < c.length(); i++) {
                            JSONObject obj = c.getJSONObject(i);

                                TabAData movie = new TabAData();
                                movie.setActivity(obj.getString("act"));
                                movie.setNotification(obj.getString("noti"));
                           acti =obj.getString("act");
                            notification=obj.getString("noti");
                          //  Toast.makeText(getActivity(), acti, Toast.LENGTH_SHORT).show();
                           // Toast.makeText(getActivity(), notification, Toast.LENGTH_SHORT).show();
                            TextView textView1=(TextView)view.findViewById(R.id.notificationid);
                            TextView textView2=(TextView)view.findViewById(R.id.eventid);


                            textView2.setText(obj.getString("act"));
                            textView1.setText(obj.getString("noti"));
                             pDialog.hide();
                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();

            }
        });

        ApplicationController.getInstance().addToRequestQueue(movieReq);





        return view;

    }




}
