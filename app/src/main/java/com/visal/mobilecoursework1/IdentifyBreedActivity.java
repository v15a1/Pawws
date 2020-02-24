package com.visal.mobilecoursework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.visal.mobilecoursework1.ui_components.AlertDialogComponent;
import com.visal.mobilecoursework1.utils.DogDetails;

import java.util.List;

public class IdentifyBreedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String ACTIVITY_NAME = "IdentifyBreedActivity";
    private static final String ACTIVITY_TITLE_NAME = "Identify Breeds";

    DogDetails dogDetails = new DogDetails();

    private Spinner identifyBreedSpinner;
    private Button submitBreedButton;
    private ImageView dogImageView;
    List<String> dogImageResourceList;
    String resource;
    int resourceId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);
//        Log.d(ACTIVITY_NAME, Integer.toString(resourceIndex));

        //initializing and accessing values after application is created
        submitBreedButton = (Button) findViewById(R.id.submit_breed_button);
        dogImageView = (ImageView) findViewById(R.id.breed_image_view);

        //Assigning the dogs
        resource = dogDetails.getRandomDog(); //randomizing the displayed image
        Log.d(ACTIVITY_NAME, resource);
        resourceId = getResources().getIdentifier(resource, "drawable", "com.visal.mobilecoursework1");
        dogImageView.setImageResource(resourceId);


        //Setting items into the spinner
        //https://www.tutorialspoint.com/how-to-get-spinner-value-in-android
        identifyBreedSpinner = (Spinner) findViewById(R.id.identify_breed_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dog_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        identifyBreedSpinner.setAdapter(adapter);
        identifyBreedSpinner.setOnItemSelectedListener(this);


        //changing the name of the actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            Log.i(ACTIVITY_NAME, "ActionBar != null");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(ACTIVITY_TITLE_NAME);
        }

        Log.i(ACTIVITY_NAME, identifyBreedSpinner.getSelectedItem().toString());
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //getting the selected value in
        String selectedVal = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //method that displays an alertbox with the answer
    public void alertAnswer(View view) {
        AlertDialogComponent.showSingleButtonAlert(
                IdentifyBreedActivity.this, submitBreedButton, "DOGSSSS", "adjfasdfasfaskfjaskfjaksdfjksd", "NEXT", false);
        resource = dogDetails.getRandomDog();
        resourceId = getResources().getIdentifier(resource, "drawable", "com.visal.mobilecoursework1");
        dogImageView.setImageResource(resourceId);

    }


}
