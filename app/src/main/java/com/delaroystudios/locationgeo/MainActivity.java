package com.delaroystudios.locationgeo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.delaroystudios.locationgeo.activities.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

    }
  /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    public void sign (View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);}

    public RatingBar ratingBar;




        /**
         * Display rating by calling getRating() method.
         * @param view
         */
        public void rateMe(View view){

            Toast.makeText(getApplicationContext(),
                    String.valueOf(ratingBar.getRating()), Toast.LENGTH_LONG).show();
        }
    }

