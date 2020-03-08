package com.visal.mobilecoursework1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.visal.mobilecoursework1.R;
import com.visal.mobilecoursework1.ui_components.AlertDialogComponent;
import com.visal.mobilecoursework1.utils.DogDetails;

public class SearchBreedActivity extends AppCompatActivity {

    private static final String TAG = SearchBreedActivity.class.getSimpleName();
    private static final String ACTIVITY_TITLE_NAME = "Search for Breeds";
    private static final int DELAY = 5000;

    private Button submitSearchButton;
    private Button stopSearchButton;
    private ImageView breedImageView;
    private String searchValue;
    private String imageResource;
    private EditText searchBar;
    private DogDetails dogDetails;
    private boolean loop = false;
    private boolean isSubmitButtonVisible;
    private boolean isStopButtonVisible;
    private boolean isSearchEnabled;
    Handler handler;
    int resourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_breed);

        submitSearchButton = (Button) findViewById(R.id.submit_search_button);
        stopSearchButton = (Button) findViewById(R.id.stop_search_button);
        breedImageView = (ImageView) findViewById(R.id.breed_images_imageview);
        searchBar = (EditText) findViewById(R.id.search_breed);
        dogDetails = new DogDetails();
        handler = new Handler();

        //disabling the button and setting opacity to 0.5
        stopSearchButton.setEnabled(false);
        stopSearchButton.setAlpha(0.3f);

        if (savedInstanceState != null){
            imageResource = savedInstanceState.getString("imageResource");
            loop = savedInstanceState.getBoolean("isLooping");
            searchValue = savedInstanceState.getString("searchValue");
            isSubmitButtonVisible = savedInstanceState.getBoolean("isSubmitButtonVisible");
            isStopButtonVisible = savedInstanceState.getBoolean("isStopButtonVisible");
            stopSearchButton.setEnabled(savedInstanceState.getBoolean("isStopButtonVisible"));
            searchBar.setEnabled(savedInstanceState.getBoolean("isSearchEnabled"));
            if (loop){
                Log.d(TAG, "onCreate: looping is true");
                resourceId = getResources().getIdentifier(imageResource, "drawable", "com.visal.mobilecoursework1");
                breedImageView.setImageResource(resourceId);
                handler.postDelayed(getSlideShow(), DELAY);
            }

        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Log.i(TAG, "ActionBar != null");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(ACTIVITY_TITLE_NAME);
        }

        submitSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStopButtonVisible = true;
                isSubmitButtonVisible = false;
                isSearchEnabled = false;
                searchValue = searchBar.getText().toString();

                //validating if search-bar is empty
                if (!searchValue.equals("")) {
                    //getting image resource from a method
                    imageResource = dogDetails.getSearchedBreedDetails(searchValue);
                    if (imageResource != null && !isSubmitButtonVisible) {

                        loop = true;
                        //making the stop search button enabled and a graphical hint to show that the button is clickable
                        stopSearchButton.setAlpha(1f);
                        stopSearchButton.setEnabled(true);
                        searchBar.setEnabled(false);

                        //disabling the button and setting opacity to 0.5
                        submitSearchButton.setAlpha(0.3f);
                        submitSearchButton.setEnabled(false);

                        //setting initial image onto the ImageView
                        resourceId = getResources().getIdentifier(imageResource, "drawable", "com.visal.mobilecoursework1");
                        breedImageView.setImageResource(resourceId);
                        handler.postDelayed(getSlideShow(), DELAY);
                    } else {
                        String noBreedFoundMessage = "No Breed in the name " + searchValue + " was found. Check case and spellings.";
                        AlertDialogComponent.basicAlert(SearchBreedActivity.this, noBreedFoundMessage, "No such Breed");
                        Log.d(TAG, "value was not found");
                    }

                } else {
                    AlertDialogComponent.basicAlert(SearchBreedActivity.this, "Please enter a Breed to search", "Empty search");
                    Log.d(TAG, "Searchbar is null");
                }
            }
        });

        stopSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSubmitButtonVisible = true;
                isSearchEnabled = false;
                if (!isStopButtonVisible){
                    loop = false;
                   //making the stop search button enabled and a graphical hint to show that the button is clickable
                   submitSearchButton.setAlpha(1f);
                   submitSearchButton.setEnabled(true);

                   stopSearchButton.setAlpha(0.3f);
                   stopSearchButton.setEnabled(false);

                   searchBar.setEnabled(true);
               }
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isLooping", loop);
        outState.putString("imageResource", imageResource);
        outState.putString("searchValue", searchValue);
        outState.putBoolean("isSubmitButtonVisible", isSubmitButtonVisible);
        outState.putBoolean("isStopButtonVisible", isStopButtonVisible);
        outState.putBoolean("isSearchEnabled", isSearchEnabled);
    }

    public Runnable getSlideShow() {
        return new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "searchbar is not null");
                Log.d(TAG, imageResource);
                imageResource = dogDetails.getSearchedBreedDetails(searchValue);
                resourceId = getResources().getIdentifier(imageResource, "drawable", "com.visal.mobilecoursework1");
                breedImageView.setImageResource(resourceId);

                //if loop is true then the handler is executed
                if (loop) {
                    //recursive function invoke every 5 seconds
                    handler.postDelayed(this, DELAY);
                }
            }
        };
    }
}
