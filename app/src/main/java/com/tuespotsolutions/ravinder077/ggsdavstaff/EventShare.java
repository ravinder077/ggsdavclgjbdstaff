package com.tuespotsolutions.ravinder077.ggsdavstaff;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
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



        //etnvenue  edittext
        final EditText  etnvenue =(EditText)findViewById(R.id.etnvenue);

        //etnname  edittext
        final EditText  etnname =(EditText)findViewById(R.id.etnname);

        //starttime  edittext
        final EditText  starttime =(EditText)findViewById(R.id.etnstime);


        //endtime edittext
        final EditText  endtime =(EditText)findViewById(R.id.entendtime);

        //date edittext
        final EditText btnback=(EditText) findViewById(R.id.etndate);

        //entorgnaizer  edittext
        final EditText  entorgnaizer =(EditText)findViewById(R.id.entorgnaizer);


        //entcontact  edittext
        final EditText  entcontact =(EditText)findViewById(R.id.entcontact);

        //submit button
        Button btnsubmit=(Button)findViewById(R.id.btnsubmit);


        //back button
        Button btncancel=(Button)findViewById(R.id.btncancel);


        //setting on click event starts








        //setting back button to home

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EventShare.this,MainActivity.class);

                startActivity(intent);


            }
        });




        endtime.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int minute = mcurrentTime.get(Calendar.MINUTE);
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);

                mTimePicker = new TimePickerDialog(EventShare.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endtime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        starttime.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int minute = mcurrentTime.get(Calendar.MINUTE);
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);

                mTimePicker = new TimePickerDialog(EventShare.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        starttime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });



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





        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //etnvenue  edittext
                final EditText  etnvenueinner =(EditText)findViewById(R.id.etnvenue);

                //etnname  edittext
                final EditText  etnnameinner =(EditText)findViewById(R.id.etnname);

                //starttime  edittext
                 EditText  starttimeinner =(EditText)findViewById(R.id.etnstime);


                //endtime edittext
                 EditText  endtimeinner =(EditText)findViewById(R.id.entendtime);

                //date edittext
                 EditText btnbackinner  =(EditText) findViewById(R.id.etndate);

                //entorgnaizer  edittext
                 EditText  entorgnaizerinner =(EditText)findViewById(R.id.entorgnaizer);


                //entcontact  edittext
                 EditText  entcontactinner =(EditText)findViewById(R.id.entcontact);


               // Toast.makeText(NotificationShare.this, sub.getText().toString(), Toast.LENGTH_SHORT).show();

               // Toast.makeText(NotificationShare.this, info.getText().toString(), Toast.LENGTH_SHORT).show();

                String urlfile="activitysubmit.php";
                Map<String,String> map=new HashMap<String,String>();
                map.put("name",etnnameinner.getText().toString());
                map.put("venue",etnvenueinner.getText().toString());
                map.put("mydate",btnbackinner.getText().toString());
                map.put("stdate",starttimeinner.getText().toString());
                map.put("edate",endtimeinner.getText().toString());
                map.put("orgnaizer",entorgnaizerinner.getText().toString());
                map.put("contact",entcontactinner.getText().toString());

                FormDataSubmit ff=new FormDataSubmit();

                ff.formSubmit(urlfile,map);

                Toast.makeText(EventShare.this, "Event shared", Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(NotificationShare.this,MainActivity.class);
                //startActivity(intent);
            }
        });



    }



}