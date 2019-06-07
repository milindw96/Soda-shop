package com.example.sharad.Soda1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Calender extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MySQLiteHelper myDb;

    private ArrayList<String> mUsername= new ArrayList<>();
    static String date;

    private  static final String TAG="CalenderActivity";
    private CalendarView mCalenderView;
    private ListView ListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        //*******************************CALENDER START******************************************************************
        mCalenderView = (CalendarView) findViewById(R.id.CV);
        mCalenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
               if(i1==3 ){ date = i +"/0" + (i1+1) + "/" + i2 ;}
                if(i1==4 ){ date = i +"/0" + (i1+1) + "/0" + i2 ;}
                   Log.d(TAG,"onSelectedDayChange:mm/dd/yyyy"+ date);
                   Intent intent = new Intent(Calender.this,FetchData.class);
                   intent.putExtra("date",date);
                   startActivity(intent);


                Toast.makeText(Calender.this, date, Toast.LENGTH_SHORT).show();


            }

        });
//**********************************CALENDER END*******************************************
//  ******************************SQLite Start*************************************************

            ListView listView=(ListView)findViewById(R.id.LV);
            myDb =new MySQLiteHelper(this);
            ArrayList<String> theList =new ArrayList<>();

        Toast.makeText(this,date, Toast.LENGTH_SHORT).show();
//            Cursor data =myDb.getDate(date);

        Cursor data =myDb.getAllData();
            if(data.getCount() == 0){
//              myDb= new MySQLiteHelper(this);
                Toast.makeText(this, "DataBase is Empty", Toast.LENGTH_SHORT).show();

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


//        **********************SQLite END**********************************************************

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                startActivity(new Intent(Calender.this,testPage.class));
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calender, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(Calender.this,testPage.class));
            finish();
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(Calender.this,SignUpActivity.class));
            finish();

        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(Calender.this,SignInActivity.class));
            finish();
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(Calender.this,testPage.class));
            finish();
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(Calender.this,testPage.class));
            finish();
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(Calender.this,testPage.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
