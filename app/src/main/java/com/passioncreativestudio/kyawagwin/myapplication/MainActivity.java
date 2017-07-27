package com.passioncreativestudio.kyawagwin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        int id = view.getId();

        Intent intent = null;
        Context context = this.getApplicationContext();
        switch (id) {
            case R.id.activity_main_mapsButton:
                intent = new Intent(context, MapsActivity.class);
                break;
            case R.id.activity_main_loginButton:
                intent = new Intent(context, LoginActivity.class);
                break;
            case R.id.activity_main_imagePickButton:
                intent = new Intent(context, ImagePickActivity.class);
                break;
            case R.id.activity_main_takeImageButton:
                intent = new Intent(context, TakeImageActivity.class);
                break;
            case R.id.activity_main_geocodingButton:
                intent = new Intent(context, GeocodingActivity.class);
                break;
        }

        startActivity(intent);
    }
}
