package com.visal.mobilecoursework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class IdentifyBreedActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "IdentifyBreedActivity";
    private static final String ACTIVITY_TITLE_NAME = "Identify Breeds";

    Spinner identifyBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);
        identifyBreed = (Spinner) findViewById(R.id.identify_breed_spinner);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            Log.i(ACTIVITY_NAME, "ActionBar != null");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(ACTIVITY_TITLE_NAME);
        }

        Log.i(ACTIVITY_NAME, identifyBreed.getSelectedItem().toString());
    }

    //https://stackoverflow.com/questions/35810229/how-to-display-and-set-click-event-on-back-arrow-on-toolbar
    //method to add onPress functionality to the back button on the action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //if the id menu previous item is the home screen. Then the activity changes to the previous activity
        if (item.getItemId() == android.R.id.home) {
            //method to change activities
            startActivity(new Intent(IdentifyBreedActivity.this, MainActivity.class));
            finish();       //method call to destroy the activity from the memory
            return true;
        } return super.onOptionsItemSelected(item);
    }
}
