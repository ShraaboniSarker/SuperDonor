package com.example.shraaboni.superdonor.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shraaboni.superdonor.Activity.Model.Request;
import com.example.shraaboni.superdonor.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.Arrays;

public class RequestPanel extends AppCompatActivity {
    EditText requestET;
    EditText bagET;
    EditText areaET;
    String bloodgrp;
    String noofbags;
    String area;
    public Context context;
    DatabaseReference databaseReference;
    LoginButton login_button;
    CallbackManager callback;
    JSONObject response, profile_pic_data, profile_pic_url;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    ShareDialog shareDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_request_panel);
        callback = CallbackManager.Factory.create();
        login_button= (LoginButton) findViewById(R.id.login_button);
        login_button.setReadPermissions("email, publish_actions");
       // login_button.setPublishPermissions("publish_actions");
       // login_button.setPublishPermissions(Arrays.asList("publish_actions","publish_pages"));
        loginWithFB();
        areaET= (EditText) findViewById(R.id.area);
        requestET= (EditText) findViewById(R.id.request);
        bagET= (EditText) findViewById(R.id.bag);
        context = this;
        Button button = (Button) findViewById(R.id.button);
        shareDialog = new ShareDialog(this);  // initialize facebook shareDialog.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Android Facebook Integration and Login Tutorial")
                            .setImageUrl(Uri.parse("https://www.studytutorial.in/wp-content/uploads/2017/02/FacebookLoginButton-min-300x136.png"))
                            .setContentDescription(
                                    "this is a blood donation app")
                            .setContentUrl(Uri.parse("https://www.facebook.com/groups/2363344107223078/?source=create_flow"))
                            .setQuote("I need " + bloodgrp +" Blood "+ noofbags+ " bags in " +area)
                            .build();
                    shareDialog.show(linkContent);  // Show facebook ShareDialog
                }
            }
        });
    }
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void loginWithFB() {
        LoginManager.getInstance().registerCallback(callback, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               // startActivity(new Intent(FrontPage.this,NewsActivity.class));
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    public void PostRequest(View view) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Request");
        bloodgrp = requestET.getText().toString();
        noofbags = bagET.getText().toString();
        area= areaET.getText().toString();
        Request request = new Request(bloodgrp,noofbags,area);
        String reqId =databaseReference.push().getKey();
        databaseReference.child(reqId).setValue(request);
        Toast.makeText(this, "Your Request will be broadcast very soon !!", Toast.LENGTH_SHORT).show();
        Notification notification =new Notification.Builder(RequestPanel.this)
                .setSmallIcon(R.drawable.super_donor)
                .setContentTitle("BloodBank App")
                .setContentText("I need " + bloodgrp +" Blood "+ noofbags+ " bags in " +area)
                .setAutoCancel(true).build();
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,notification);
        Bundle params = new Bundle();
        params.putString("message", "This is a test message");
/* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/2363344107223078/feed",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.e("SSSSSSSSS",""+ response.toString());
                    }
                }
        ).executeAsync();
    }
}
