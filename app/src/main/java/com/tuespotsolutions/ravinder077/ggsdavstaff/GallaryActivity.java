package com.tuespotsolutions.ravinder077.ggsdavstaff;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.wangjie.androidbucket.utils.ABThreadUtil.TAG;

/**
 * Created by ravinder077 on 07-10-2017.
 */

public class GallaryActivity extends AppCompatActivity {

    private List<GallaryData> postlist = new ArrayList<GallaryData>();
    private RecyclerView MyRecyclerView;
    private GallaryAdapter adapter;
    private GridLayoutManager mLayoutManager;
    String dataoffset=null;
    FrameLayout frameLayout=null;
    private ProgressDialog pDialog;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallaryrecycle);
        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        MyRecyclerView = (RecyclerView) findViewById(R.id.gallaryrec);
        pDialog = new ProgressDialog(this);
       Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbarid);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("   College Photo Gallery");
        mActionBarToolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setIcon(R.drawable.gall);
        //toolbar.setTitle("gggggggggggggggggggggggggggggg");


        dataoffset="0";
        mLayoutManager = new GridLayoutManager(this,2);

        MyRecyclerView.setLayoutManager(mLayoutManager);
        // preparePostData();


        adapter = new GallaryAdapter(postlist);

        frameLayout=(FrameLayout)findViewById(R.id.gallaryframe);



        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        MyRecyclerView.addOnScrollListener(scrollListener);




        fetchStores();


        MyRecyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), MyRecyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", (Serializable) postlist);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }


    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        //Toast.makeText(this, "new data loading", Toast.LENGTH_SHORT).show();
        pDialog.setMessage("Please Wait...");
        pDialog.show();
        final Integer count =Integer.parseInt(dataoffset)+10;
        dataoffset=count.toString();


        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://timepasstoday.com/fetchgallary.php?dataoffset="+count.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "more data query gallary : " + "http://timepasstoday.com/fetchgallary.php?dataoffset="+count.toString());
                pDialog.hide();
                Log.d(TAG, "Fetch Stores: " + response);
                showStores(response);
                //MyRecyclerView.setAdapter(adapter);
                System.err.println("adpter attached");
                adapter.notifyDataSetChanged();
                System.err.println("data set changed attached");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Fetch Stores Error: " + error.getMessage());
            }
        });
        ApplicationController.getInstance().addToRequestQueue(fetchAllStores);
    }
    public ArrayList<GallaryData> jsonToMap(String jsonStr) throws JSONException {


        HashMap<String, String> contact = new HashMap<>();

        ArrayList<GallaryData> al = new ArrayList<GallaryData>();

        if (jsonStr != null) {

            System.err.print("josn strin is");

            System.err.print(jsonStr);

            System.err.print("josn strin ends");


            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("tasks");

                // looping through All Contacts
                JSONArray c = jsonObj.getJSONArray("tasks");
                for (int i = 0; i < c.length(); i++) {
                    JSONObject obj = c.getJSONObject(i);
                    String id = obj.getString("id");

                    String name = obj.getString("name");

                    String img = obj.getString("img");
                    String date = obj.getString("date");



                    GallaryData cc = new GallaryData();

                    cc.setId(id);
                    cc.setName(name);
                    cc.setImg(img);
                    cc.setDatetime(date);


                    al.add(cc);


                }


            } catch (Exception e) {
                e.printStackTrace();


            }
        }

        return al;
    }
    public void initializeList(ArrayList<GallaryData> al) {
        postlist.clear();

        for (GallaryData cd : al) {


            GallaryData item = new GallaryData();


            item.setId(cd.getId());
            item.setName(cd.getName());
            item.setImg(cd.getImg());
            item.setDatetime(cd.getDatetime());

            //item.setPostVideo(cd.getPostVideo());

            postlist.add(item);




        }


        System.err.println("data added in list "+postlist);


    }
    private void fetchStores() {

        Log.d(TAG, "Data query : " + "http://timepasstoday.com/fetchgallary.php?dataoffset="+dataoffset);
        pDialog.setMessage("Please Wait...");
        pDialog.show();

        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://timepasstoday.com/fetchgallary.php?dataoffset="+dataoffset, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "Data query gallary: " + "http://timepasstoday.com/fetchgallary.php?dataoffset="+dataoffset);
                pDialog.hide();
                Log.d(TAG, "Fetch Stores: " + response);
                showStores(response);
                MyRecyclerView.setAdapter(adapter);
                System.err.println("adpter attached");
                adapter.notifyDataSetChanged();
                System.err.println("data set changed attached");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Fetch Stores Error fetchStores: " + error.getMessage());
            }
        });
        ApplicationController.getInstance().addToRequestQueue(fetchAllStores);
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
                String date = obj.getString("datetime");


                GallaryData cc = new GallaryData();

                cc.setId(id);
                cc.setName(name);
                cc.setImg(img);
                cc.setDatetime(date);
                postlist.add(cc);
            }
        } catch (JSONException e) {
            Log.d(TAG, "Show Stores: " + e.getMessage());
        }
    }
}
