package com.tuespotsolutions.ravinder077.ggsdavstaff;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ravinder077 on 08-10-2017.
 */

public class NotificationShare extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharenotification);


        Button btnback=(Button)findViewById(R.id.btncancel);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotificationShare.this,MainActivity.class);
                startActivity(intent);
            }
        });



        Button submit=(Button)findViewById(R.id.btnsubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText sub=(EditText)findViewById(R.id.etnsubject);
                EditText info=(EditText)findViewById(R.id.etmob);


               // Toast.makeText(NotificationShare.this, sub.getText().toString(), Toast.LENGTH_SHORT).show();

               // Toast.makeText(NotificationShare.this, info.getText().toString(), Toast.LENGTH_SHORT).show();

                String urlfile="infosubmit.php";
                Map<String,String> map=new HashMap<String,String>();
                map.put("sub",sub.getText().toString());
                map.put("info",info.getText().toString());

                FormDataSubmit ff=new FormDataSubmit();
                ff.formSubmit(urlfile,map);

                Toast.makeText(NotificationShare.this, "Notification shared", Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(NotificationShare.this,MainActivity.class);
                //startActivity(intent);
            }
        });



    }



}