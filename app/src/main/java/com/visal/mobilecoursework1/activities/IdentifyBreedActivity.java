package com.visal.mobilecoursework1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.visal.mobilecoursework1.R;
import com.visal.mobilecoursework1.ui_components.AlertDialogComponent;
import com.visal.mobilecoursework1.utils.Dog;
import com.visal.mobilecoursework1.utils.DogDetails;

import java.util.List;

public class IdentifyBreedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String ACTIVITY_NAME = "IdentifyBreedActivity";
    private static final String ACTIVITY_TITLE_NAME = "Identify Breeds";

    DogDetails dogDetails = new DogDetails();

    private Spinner identifyBreedSpinner;
    private Button submitBreedButton;
    String selectedSpinnerItem;
    Dog dogObject;
    int resourceId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);
//        Log.d(ACTIVITY_NAME, Integer.toString(resourceIndex));

        //initializing and accessing values after application is created
        submitBreedButton = (Button) findViewById(R.id.submit_breed_button);
        ImageView dogImageView = (ImageView) findViewById(R.id.breed_image_view);

        //Assigning the dogs
        dogObject = dogDetails.getRandomDog(); //randomizing the displayed image
        Log.d(ACTIVITY_NAME, dogObject.toString());
        resourceId = getResources().getIdentifier(dogObject.getResourceName(), "drawable", "com.visal.mobilecoursework1");
        dogImageView.setImageResource(resourceId);

        submitBreedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitBreedButton.getText().equals("NEXT")){
                    startActivity(new Intent(IdentifyBreedActivity.this, IdentifyBreedActivity.class));
                }else{
                    checkAnswer(dogObject, selectedSpinnerItem);
                }
            }
        });


        //Setting items into the spinner
        //https://www.tutorialspoint.com/how-to-get-spinner-value-in-android
        identifyBreedSpinner = (Spinner) findViewById(R.id.identify_breed_spinner);
        identifyBreedSpinner.setEnabled(true);        //allowing user to interact with the spinner
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
        selectedSpinnerItem = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void checkAnswer(Dog dog, String spinnerItemName){
        TextView correctAlertTitle = new TextView(this);
        TextView wrongAlertTitle = new TextView(this);
        String correctAnswerMessage = "The selected answer is correct. Well done!!!";
        String wrongAnswerMessage = "Your answer is incorrect. the correct answer is " + dog.getBreed() + ". Better luck next time";

        correctAlertTitle.setTextColor(Color.parseColor("#1BE59D"));
        correctAlertTitle.setText("CORRECT!");
        correctAlertTitle.setPadding(40, 30, 20, 30);
        correctAlertTitle.setTextSize(20F);
        correctAlertTitle.setTypeface(null, Typeface.BOLD);

        wrongAlertTitle.setTextColor(Color.parseColor("#FF0000"));
        wrongAlertTitle.setText("WRONG!");
        wrongAlertTitle.setPadding(40, 30, 20, 30);
        wrongAlertTitle.setTextSize(20F);
        wrongAlertTitle.setTypeface(null, Typeface.BOLD);

        if (dog.getBreed().equals(spinnerItemName)){
            AlertDialogComponent.IdentifyBreedAnswerAlert(this, submitBreedButton, correctAlertTitle, correctAnswerMessage,false);
            identifyBreedSpinner.setEnabled(false);     //disabling the spinner
        }else if(spinnerItemName.equals("Select a Breed")){
            AlertDialogComponent.emptySelectionAlert(this);
        }else{
            AlertDialogComponent.IdentifyBreedAnswerAlert(this, submitBreedButton, wrongAlertTitle, wrongAnswerMessage,false);
            identifyBreedSpinner.setEnabled(false);     //disabling the spinner
        }
    }



}
