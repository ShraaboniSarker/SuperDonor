package com.example.shraaboni.superdonor.Activity;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.shraaboni.superdonor.R;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle("Supper Donor");
//        toolbar.setSubtitle("Home Screen");
        getSupportActionBar().setTitle("Super Donor");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.Profile){
            Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.Request)
        {
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            Toast.makeText(this, "request", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.Mapview){
            Toast.makeText(this, "map view", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.Logout){
            Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
//            int id = item.getItemId();
//            if(id == R.id.Profile){
//                Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
//            }else if(id == R.id.Request)
//            {
//                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
//                Toast.makeText(this, "request", Toast.LENGTH_SHORT).show();
//            }
//            else if(id == R.id.Mapview){
//                Toast.makeText(this, "map view", Toast.LENGTH_SHORT).show();
//            }
//            else if(id == R.id.Logout){
//                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
//            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    }
