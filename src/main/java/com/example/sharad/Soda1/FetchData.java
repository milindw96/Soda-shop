package com.example.sharad.Soda1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FetchData extends AppCompatActivity {
    private DatabaseReference mDatabase;
    MySQLiteHelper myDb;
    Button total;
    private ArrayList<String> mUsername= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);
        //**************************BUTTON*******************************************
        total=(Button)findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   myDb =new MySQLiteHelper(this);
                Intent incomiIntent =getIntent();
                String date=incomiIntent.getStringExtra("date");
                Cursor sum =myDb.sum(date);
                if(sum.getCount()==0){return;}
                StringBuffer buffer=new StringBuffer();
                while (sum.moveToNext()){
                    buffer.append("Sum="+sum.getString(0));
                }
                //show Sum
                showMessage("Towdays Income is ",buffer.toString() );





            }
        });



        //**************************BUTTON end*******************************************

        Intent incomiIntent =getIntent();
        String date=incomiIntent.getStringExtra("date");
//        String date ="'2018/04/16'";
//  *******************************************************************************
            ListView listView=(ListView)findViewById(R.id.user_List);
            myDb =new MySQLiteHelper(this);

            ArrayList<String> theList =new ArrayList<>();
//        Cursor data =myDb.getDate(date);
        Cursor data =myDb.getDate(date);
      //  Toast.makeText(this,"after   "+date ,Toast.LENGTH_SHORT).show();
        if(data.getCount() == 0){
//              myDb= new MySQLiteHelper(this);
            Toast.makeText(this, "DataBase is Empty", Toast.LENGTH_SHORT).show();
          //  Toast.makeText(this,date ,Toast.LENGTH_SHORT).show();
        }
            else {
                while (data.moveToNext()){
                    theList.add(data.getString(1));
                    theList.add(data.getString(2));
                    theList.add(data.getString(3));
                    ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                    listView.setAdapter(listAdapter);

                }

        }

//        ********************************************************************************
    }
    //*************************ALERT BOX**************************************
public void  showMessage(String tittle,String message){

    AlertDialog.Builder  builder =new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(tittle);
    builder.setMessage(message);
    builder.show();
}
    //*************************ALERT BOX END**************************************
}
