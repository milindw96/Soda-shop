package com.example.sharad.Soda1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Message;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.core.Tag;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Pageone extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    MySQLiteHelper myDb;


    private DatabaseReference mDatabase;
    private ListView mUserList;
    private ArrayList<String> mUsername= new ArrayList<>();

    private  static final String TAG="CalenderActivity";
    private CalendarView mCalenderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //*************************************************************

        //*************************************************************

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //*************************************************************************************************
        mCalenderView = (CalendarView) findViewById(R.id.calendarView);
        mCalenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = ( i + 1 ) +"/" + i1 + "/" + i2 ;
                Log.d(TAG,"onSelectedDayChange:mm/dd/yyyy"+ date);
                Intent intent= new Intent(Pageone.this,FetchData.class);
                intent.putExtra("date",date);
                startActivity(intent);
                 Toast.makeText(Pageone.this, date, Toast.LENGTH_SHORT).show();


                //****************************SQL START**************************************


                //****************************SQL END*******************************************

            }
        });

        //**********************************************************************************************



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //*********************CALENDER STart****************************************************************************************


        //*****************************CALENDER End********************************************************************************


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
        getMenuInflater().inflate(R.menu.pageone, menu);
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
            Intent intent= new Intent(this,SignInActivity.class);
            startActivity(intent);
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
            startActivity(new Intent(Pageone.this, Pageone.class));
            finish();
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(Pageone.this, SignUpActivity.class));
            finish();
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(Pageone.this, SignInActivity.class));
            finish();
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(Pageone.this,Calender.class));
            finish();
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(Pageone.this, SignInActivity.class));
            finish();
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(Pageone.this, Welcome.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void showMessage(String title , String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        //builder.setTitle(title);
        builder.setTitle(Message);
        builder.show();


    }

}

