package com.example.sharad.Soda1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BuySoda extends AppCompatActivity {
    MySQLiteHelper myDb;
    ImageButton ten,twenty,thity,fifteen;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_soda);
        //****************************************************************************************
            myDb=new MySQLiteHelper(this);

            ten=(ImageButton)findViewById(R.id.ten);
            fifteen=(ImageButton)findViewById(R.id.fifteen);
            twenty=(ImageButton)findViewById(R.id.twenty);
            thity=(ImageButton)findViewById(R.id.thirthy);
            logout=(Button)findViewById(R.id.logout);
        //****************************************************************************************
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(BuySoda.this, SignInActivity.class));
            finish();
        }
    });
        addData();
    }


    //************************BUTTON ADD DATA**********************
    public void addData(){
            ten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String time = DateFormat.getDateTimeInstance().format(new Date());
                    String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                    //String date = DateFormat.getDateTimeInstance().format(new Date());
                    Integer cost =10;
                    Toast.makeText(BuySoda.this," 10 Rs Added", Toast.LENGTH_SHORT).show();

                    myDb.insertData(date,time,cost);

                }
            });
            twenty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String time = DateFormat.getDateTimeInstance().format(new Date());
                    String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                    //String date = DateFormat.getDateTimeInstance().format(new Date());
                    Integer cost =20;
                    Toast.makeText(BuySoda.this," 20 Rs Added", Toast.LENGTH_SHORT).show();

                    myDb.insertData(date,time,cost);
                }
            });
        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time = DateFormat.getDateTimeInstance().format(new Date());
                String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                //String date = DateFormat.getDateTimeInstance().format(new Date());
                Integer cost =15;
                Toast.makeText(BuySoda.this," 15 Rs Added", Toast.LENGTH_SHORT).show();

                myDb.insertData(date,time,cost);
            }
        });

        twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time = DateFormat.getDateTimeInstance().format(new Date());
                String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                //String date = DateFormat.getDateTimeInstance().format(new Date());
                Integer cost =20;
                Toast.makeText(BuySoda.this," 20Rs Added", Toast.LENGTH_SHORT).show();

                myDb.insertData(date,time,cost);

            }
        });

        thity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time = DateFormat.getDateTimeInstance().format(new Date());
                String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                //String date = DateFormat.getDateTimeInstance().format(new Date());
                Integer cost =30;
                Toast.makeText(BuySoda.this," 30 Rs Added", Toast.LENGTH_SHORT).show();
                myDb.insertData(date,time,cost);
            }
        });
    }
    //************************BUTTON ADD DATA END**********************

}
