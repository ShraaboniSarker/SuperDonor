package com.example.shraaboni.superdonor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.shraaboni.superdonor.R;

public class UserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
    }

    public void goToHome(View view) {

        startActivity(new Intent(UserDetails.this,NavigateHome   .class));

    }
}
