package com.example.sharad.Soda1;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class View extends AppCompatActivity {
    MySQLiteHelper myDb;
    private ArrayList<String> mUsername= new ArrayList<>();
    String date;
    EditText Et;
    Button find;

    private ListView mUserList;
    private  static final String TAG="CalenderActivity";
    private CalendarView mCalenderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //*************************************************************************************************
        mCalenderView = (CalendarView) findViewById(R.id.calendarView);
        mCalenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i + 1) + "/" + i1 + "/" + i2;
                Log.d(TAG, "onSelectedDayChange:mm/dd/yyyy" + date);
//                Intent intent = new Intent(View.this, FetchData.class);
//                intent.putExtra("date", date);
//                startActivity(intent);
                Toast.makeText(View.this, date, Toast.LENGTH_SHORT).show();

            }
        });


                //          ******************************SQLite Start*************************************************

        ListView listView=(ListView)findViewById(R.id.LV);
        myDb =new MySQLiteHelper(this);
        ArrayList<String> theList =new ArrayList<>();
        Cursor data =myDb.getDate(date);
        if(data.getCount() == 0){
//              myDb= new MySQLiteHelper(this);
            Toast.makeText(this, "DataBase is Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            while (data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);

            }

        }


//        **********************SQLite END**********************************************************
    }
}
