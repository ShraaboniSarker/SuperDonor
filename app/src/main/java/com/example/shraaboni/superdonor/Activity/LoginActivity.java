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

public class LoginActivity extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
    }

    public void login(View view) {
        final String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //  Toast.makeText(LoginActivity.this, "" + firebaseAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "" + firebaseAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,NavigateHome.class);
                    startActivity(intent);
                }
            }
        });

    }
}
