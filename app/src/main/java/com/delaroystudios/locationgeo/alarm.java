package com.delaroystudios.locationgeo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class alarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void map (View view){
        Intent intent = new Intent(this, Mactivity.class);
        startActivity(intent);}

    public void info (View view){
        Intent intent = new Intent(this, about.class);
        startActivity(intent);}

    public void feed (View view){
        Intent intent = new Intent(this, feedback.class);
        startActivity(intent);}
}
