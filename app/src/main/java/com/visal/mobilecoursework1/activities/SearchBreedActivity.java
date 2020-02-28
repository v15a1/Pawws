package com.visal.mobilecoursework1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.visal.mobilecoursework1.R;

public class SearchBreedActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = SearchBreedActivity.class.getSimpleName();
    private static final String ACTIVITY_TITLE_NAME = "Search for Breeds";

    private Button submitSearchButton;
    private Button stopSearchButton;
    private Button searchBreedButton;
    private ImageView breedImageView;
    private String searchValue;
    private String imageResource;
    private EditText searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_breed);

        submitSearchButton = (Button) findViewById(R.id.submit_search_button);
        stopSearchButton = (Button) findViewById(R.id.stop_search_button);
        searchBreedButton = (Button) findViewById(R.id.search_button);
        breedImageView = (ImageView) findViewById(R.id.breed_images_imageview);
        searchBar = (EditText) findViewById(R.id.search_breed);

//        stopSearchButton.setEnabled(false);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            Log.i(ACTIVITY_NAME, "ActionBar != null");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(ACTIVITY_TITLE_NAME);
        }

        searchBreedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ACTIVITY_NAME, "Search button pressed");
                stopSearchButton.setEnabled(true);
            }
        });
    }

    //https://stackoverflow.com/questions/35810229/how-to-display-and-set-click-event-on-back-arrow-on-toolbar
    //method to add onPress functionality to the back button on the action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //if the id menu previous item is the home screen. Then the activity changes to the previous activity
        if (item.getItemId() == android.R.id.home) {
            //method to change activities
            startActivity(new Intent(SearchBreedActivity.this, MainActivity.class));
            finish();       //method call to destroy the activity from the memory
            return true;
        } return super.onOptionsItemSelected(item);
    }



}
