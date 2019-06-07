package com.example.sharad.Soda1;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener
{
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    EditText t1,t2;
    Button b1;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up);
        t1=(EditText)findViewById(R.id.email);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        t2=(EditText)findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.register);
        b1.setOnClickListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_sign_in);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.menu1:
                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                        break;
                    case R.id.menu3:
                        startActivity(new Intent(SignUpActivity.this, Welcome.class));
                        break;
                    case R.id.menu4:
                        startActivity(new Intent(SignUpActivity.this,testPage.class));
                }

                return true;
            }
        });

    }

    @Override
    public void onClick(View view)
    {
        String email=t1.getText().toString().trim();
        String pass=t2.getText().toString().trim();

        if(email.isEmpty())
        {
            t1.setError("Emal Cant't Blank");
            t1.requestFocus();
           return;
        }

        if(pass.isEmpty())
        {

            t2.setError("Pass Cant't Blank");
            t2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            t1.setError("Plz Enter Valid Format Email");
            t1.requestFocus();

        }

        if(pass.length()<6)
        {
            t2.setError("Plz Enter Minimum length pass Is 6");
            t2.requestFocus();
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("MSG", "createUserWithEmail:onComplete:" + task.isSuccessful());
                         if(task.isSuccessful())
                         {
                             Toast.makeText(SignUpActivity.this, "User Register Successfull",
                                     Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(SignUpActivity.this,Welcome.class));
                             finish();
                             progressBar.setVisibility(View.GONE);

                         }
                        if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        {
                            Toast.makeText(SignUpActivity.this, "User Already Exist",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });






    }
}
