package com.example.sharad.Soda1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
 import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
public class MainActivity extends AppCompatActivity {
     EditText t1,t2;
   //Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // t1=(EditText)findViewById(R.id.editText);
        //t2=(EditText)findViewById(R.id.editText2);
         Firebase.setAndroidContext(getApplicationContext());
         //firebase=new Firebase(Config.url);
    }

    public void click(View view) {

        String name = t1.getText().toString();
        String address = t2.getText().toString();
        if (name.equals("") | address.equals("")) {
            Toast.makeText(this, "Plz fill all the filds", Toast.LENGTH_SHORT).show();

        } else {

            User user = new User();
            user.setName(name);
            user.setAddress(address);

          // firebase.child("person").push().setValue(user);
            //firebase.child("person").setValue(user);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();

            myRef.child("users").push().setValue(user);

            Toast.makeText(this, "Data is saved....", Toast.LENGTH_SHORT).show();


        }}

/*
    public void load(View v)
    {

    firebase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {

            if(dataSnapshot.hasChildren())
            {
            for(DataSnapshot cSnapshot:dataSnapshot.getChildren())
            {
                User user=cSnapshot.getValue(User.class);
                Toast.makeText(MainActivity.this, "UserData="+user.getAddress()+" "+user.getName(), Toast.LENGTH_SHORT).show();
            }

            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });
    }
    */
}
