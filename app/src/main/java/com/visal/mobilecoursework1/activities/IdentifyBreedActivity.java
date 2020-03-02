package com.visal.mobilecoursework1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.visal.mobilecoursework1.R;
import com.visal.mobilecoursework1.ui_components.AlertDialogComponent;
import com.visal.mobilecoursework1.utils.Dog;
import com.visal.mobilecoursework1.utils.DogDetails;

public class IdentifyBreedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context context = this;
    private static final String TAG = "IdentifyBreedActivity";
    private static final String ACTIVITY_TITLE_NAME = "Identify Breeds";

    DogDetails dogDetails = new DogDetails();

    private Spinner identifyBreedSpinner;
    private Button submitBreedButton;
    private CountDownTimer countDownTimer;
    private TextView countDown;
    private ProgressBar timerProgress;
    String selectedSpinnerItem;
    Dog dogObject;
    int resourceId;
    boolean isTimerToggled;
    boolean isAnswerSubmitted;
    boolean isTimerFinished;
    int time = 10;
    String resourceName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

        //getting the intent
        Intent intent = getIntent();
        isTimerToggled = intent.getBooleanExtra("timerToggle", false);

        //initializing and accessing values after application is created
        submitBreedButton = (Button) findViewById(R.id.submit_breed_button);
        ImageView dogImageView = (ImageView) findViewById(R.id.breed_image_view);
        timerProgress = (ProgressBar) findViewById(R.id.identify_breed_progress_bar);
        countDown = (TextView) findViewById(R.id.identify_breed_timer);

        if (savedInstanceState != null){
            Log.d(TAG, "onCreate: saved instance != null");
            isTimerToggled = savedInstanceState.getBoolean("timerToggle");
            isAnswerSubmitted = savedInstanceState.getBoolean("isAnswerSubmitted");
            selectedSpinnerItem = savedInstanceState.getString("selectedSpinnerItem");
            resourceName = savedInstanceState.getString("resourceName");
            resourceId = savedInstanceState.getInt("imageResource");
            dogObject = (Dog) savedInstanceState.getSerializable("dog");
            Log.d(TAG, "onCreate: " + resourceName + ", " + resourceId+ ", " + dogObject);
        }else{
            Log.d(TAG, "onCreate: saved instance === null");
            dogObject = dogDetails.getRandomDog(); //randomizing the displayed image
            resourceName = dogObject.getResourceName();
        }

        //Assigning the dogs
//        Log.d(TAG, dogObject.toString());
        resourceId = getResources().getIdentifier(resourceName, "drawable", "com.visal.mobilecoursework1");
        dogImageView.setImageResource(resourceId);

        //Setting items into the spinner
        //https://www.tutorialspoint.com/how-to-get-spinner-value-in-android
        identifyBreedSpinner = (Spinner) findViewById(R.id.identify_breed_spinner);
        identifyBreedSpinner.setEnabled(true);        //allowing user to interact with the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dog_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        identifyBreedSpinner.setAdapter(adapter);
        identifyBreedSpinner.setOnItemSelectedListener(this);

        //changing visibility or enabling of the elements
        timerProgress.setVisibility(View.GONE);
        countDown.setVisibility(View.GONE);
//        submitBreedButton.setEnabled(false);

        //changing the name of the actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Log.i(TAG, "ActionBar != null");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(ACTIVITY_TITLE_NAME);
        }


        submitBreedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitBreedButton.getText().equals("NEXT")) {
                    startActivity(new Intent(IdentifyBreedActivity.this, IdentifyBreedActivity.class));
                } else {
                    checkAnswer(dogObject, selectedSpinnerItem);
                    //checking if an answer is selected and submitted
                    isAnswerSubmitted = true;
                }
            }
        });

        if (isTimerToggled) {
            Log.d(TAG, "onCreate: Timer toggle true");
            countDownTimer = new CountDownTimer(11000, 1000) {

                IdentifyBreedActivity identifyBreedActivity = new IdentifyBreedActivity();
                int timer = identifyBreedActivity.time;
                //initial timer value
                @Override
                public void onTick(long millisUntilFinished) {
                    timerProgress.setProgress(timer);
                    countDown.setText(Integer.toString(timer));
                    timer--;
                }

                @Override
                public void onFinish() {
                    isTimerFinished = true;
                    if (!isAnswerSubmitted && ((Activity)identifyBreedActivity.context).isFinishing()) {
                        Log.d(TAG, "onFinish: Timer finished");
                        String answer = "You ran out of time. The correct answer is " + dogObject.getBreed();
                        AlertDialogComponent.timeoutAlert(IdentifyBreedActivity.this, answer);
                    }
                }
            };
        }

        if (isTimerToggled) {
            timerProgress.setVisibility(View.VISIBLE);
            countDown.setVisibility(View.VISIBLE);
            countDownTimer.start();
        }
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
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //getting the selected value in
        selectedSpinnerItem = parent.getSelectedItem().toString();
        Log.d(TAG, "onItemSelected: " + selectedSpinnerItem);
        if (selectedSpinnerItem.equals("Select a Breed")) {
            submitBreedButton.setEnabled(false);
        } else {
            submitBreedButton.setEnabled(true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void checkAnswer(Dog dog, String spinnerItemName) {

        //setting the alert message title to a custom textview
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

        if (dog.getBreed().equals(spinnerItemName)) {
            AlertDialogComponent.identifyBreedAlert(this, submitBreedButton, correctAlertTitle, correctAnswerMessage, false);
        } else {
            AlertDialogComponent.identifyBreedAlert(this, submitBreedButton, wrongAlertTitle, wrongAnswerMessage, false);
        }

        identifyBreedSpinner.setEnabled(false);     //disabling the spinner
        //if timer toggle is enabled then the countdown will stop
        if (isTimerToggled) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("timerToggle", isTimerToggled);
        outState.putString("selectedSpinnerItem", selectedSpinnerItem);
        outState.putString("resourceName", resourceName);
        outState.putInt("imageResource", resourceId);
        outState.putBoolean("isAnswerSubmitted",isAnswerSubmitted);
        outState.putSerializable("dogObj", dogObject);
    }
}
