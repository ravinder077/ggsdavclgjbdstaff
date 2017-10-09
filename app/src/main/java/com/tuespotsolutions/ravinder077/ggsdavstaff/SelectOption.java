package com.tuespotsolutions.ravinder077.ggsdavstaff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by ravinder077 on 08-10-2017.
 */

public class SelectOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share1);

        Button btnevent=(Button)findViewById(R.id.btnsotpevent);
        Button btnnotification=(Button)findViewById(R.id.btnsotpnotification);
        Button btnimages=(Button)findViewById(R.id.btnimages);
        Button btnback=(Button)findViewById(R.id.btnsotpcancel);
         btnback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(SelectOption.this,MainActivity.class);
                 startActivity(intent);
             }
         });

        btnevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectOption.this,EventShare.class);
                startActivity(intent);
            }
        });

        btnnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SelectOption.this,NotificationShare.class);
                startActivity(intent);

            }
        });

        btnimages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectOption.this, "images", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
