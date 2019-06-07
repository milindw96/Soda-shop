package com.example.sharad.Soda1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText t1,t2;
    Button b1;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_in);
        t1=(EditText)findViewById(R.id.email);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        t2=(EditText)findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.login);
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final String email = t1.getText().toString().trim();
        String pass = t2.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);


        if (email.isEmpty()) {
            t1.setError("Emal Cant't Blank");
            t1.requestFocus();
            return;
        }

        if (pass.isEmpty()) {

            t2.setError("Pass Cant't Blank");
            t2.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            t1.setError("Plz Enter Valid Format Email");
            t1.requestFocus();

        }

        if (pass.length() < 6) {
            t2.setError("Plz Enter Minimum length pass Is 6");
            t2.requestFocus();
        }
        if( email.toString().trim().equals("admin@gmail.com") && pass.toString().trim().equals("admin@123")){
            Toast.makeText(SignInActivity.this, "User Login Successfull",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInActivity.this, testPage.class));
            finish();
            progressBar.setVisibility(View.GONE);

        }
else {

            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("MSG", "signInWithEmail:onComplete:" + task.isSuccessful());

                            if (task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "User Login Successfull",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, BuySoda.class));
                                finish();
                                progressBar.setVisibility(View.GONE);

                            }
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("MSG", "signInWithEmail", task.getException());
                                Toast.makeText(SignInActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });


        }

    }

//    public void newUser(View view) {
//
//        startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
//
//    }
}