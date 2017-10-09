package com.tuespotsolutions.ravinder077.ggsdavstaff;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri.Builder;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ravinder077 on 30-09-2017.
 */

  public  class FormDataSubmit
{




    Map<String,String> map;


    public String formSubmit(String key, Map<String,String> map)
    {
        this.map=map;
        FormData formData=new FormData();

        String data=null;
        Builder builder = new Builder();
        builder.scheme("http")
                .authority("www.timepasstoday.com")
                .appendPath(key);

           for(Map.Entry<String,String> ll:map.entrySet())
        {
            builder .appendQueryParameter(ll.getKey(), ll.getValue());
        }

        String myUrl = builder.build().toString();
        System.err.println("myUrl"+myUrl);
        try {
            formData.execute(myUrl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            data=   formData.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return data;
    }


    class FormData extends  AsyncTask<String,String,String>
    {






        @Override
        protected String doInBackground(String... params) {

            URL url;
            URL url1;
            String st = null;
            try {


                url=new URL(params[0]);
                System.err.println("url"+url);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.setRequestProperty("User-Agent","");
                con.setRequestMethod("GET");

                con.connect();

                InputStream in= con.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                StringBuilder result=new StringBuilder();
                String line;
                while((line=reader.readLine())!=null)
                {
                    result.append(line);
                }

                st=result.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }


           System.err.println("results "+st);



            return st;
        }
    }




}
