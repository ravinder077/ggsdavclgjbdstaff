package com.tuespotsolutions.ravinder077.ggsdavstaff;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ravinder077 on 08-10-2017.
 */

public class EventShare extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shareevent);


        final EditText btnback=(EditText) findViewById(R.id.etndate);


         btnback.setOnClickListener(new View.OnClickListener() {



            int mYear;
            int mMonth;
            int mDay;
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                mYear=mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(EventShare.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth=selectedmonth+1;

                        Toast.makeText(EventShare.this, selectedday+"/"+selectedmonth+"/"+selectedyear, Toast.LENGTH_SHORT).show();
                        btnback.setText(selectedday+"/"+selectedmonth+"/"+selectedyear);

                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });





       /* submit.setOnClickListener(new View.OnClickListener() {
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

                Toast.makeText(EventShare.this, "Notification shared", Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(NotificationShare.this,MainActivity.class);
                //startActivity(intent);
            }
        });*/



    }



}