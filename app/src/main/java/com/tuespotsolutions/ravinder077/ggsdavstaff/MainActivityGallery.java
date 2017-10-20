package com.tuespotsolutions.ravinder077.ggsdavstaff;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivityGallery extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private static final String endpoint = "http://timepasstoday.com/fetchgallary.php?dataoffset=0";
    private ArrayList<Image> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    private String dataoffset =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pDialog = new ProgressDialog(this);
        images = new ArrayList<>();
        mAdapter = new GalleryAdapter(getApplicationContext(), images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        /* recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        fetchImages();
    }

    private void fetchImages() {

        pDialog.setMessage("Downloading json...");
        pDialog.show();

        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://timepasstoday.com/fetchgallary.php?dataoffset="+dataoffset, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "Data query gallary: " + "http://timepasstoday.com/fetchgallary.php?dataoffset="+dataoffset);

                Log.d(TAG, "Fetch Stores: " + response);
                showStores(response);
                recyclerView.setAdapter(mAdapter);
                System.err.println("adpter attached");
                mAdapter.notifyDataSetChanged();
                System.err.println("data set changed attached");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Fetch Stores Error fetchStores: " + error.getMessage());
            }
        });
    }


    private void showStores(JSONObject response) {
        try {
            JSONArray contacts = response.getJSONArray("tasks");

            // looping through All Contacts
            JSONArray c = response.getJSONArray("tasks");
            for (int i = 0; i < c.length(); i++) {
                JSONObject obj = c.getJSONObject(i);
                String id = obj.getString("id");


                String name = obj.getString("name");

                String img = obj.getString("img");
                String datetime = obj.getString("datetime");


                Image cc = new Image();


                cc.setName(name);
                cc.setSmall(img);
                cc.setMedium(img);
                cc.setLarge(img);
                cc.setTimestamp(datetime);
                images.add(cc);
            }
        } catch (JSONException e) {
            Log.d(TAG, "Show Stores: " + e.getMessage());
        }
    }
}