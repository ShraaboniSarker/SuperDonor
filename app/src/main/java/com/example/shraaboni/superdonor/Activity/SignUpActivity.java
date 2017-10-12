package com.example.shraaboni.superdonor.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shraaboni.superdonor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailET;
    private EditText passwordET;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void SignUp(View view) {
        final String email = emailET.getText().toString();
        final String password = passwordET.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(SignUpActivity.this,UserDetails.class));
               }else{
                   Toast.makeText(SignUpActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    public void LoginPage(View view) {
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
    }
}
