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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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

import java.io.Serializable;

public class IdentifyBreedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context context = this;
    private static final String TAG = "IdentifyBreedActivity";
    private static final String ACTIVITY_TITLE_NAME = "Identify Breeds";
    private static final int INTERVAL = 1000;

    DogDetails dogDetails = new DogDetails();

    private Spinner identifyBreedSpinner;
    private Button submitBreedButton;
    private TextView countDown;
    private ProgressBar timerProgress;
    private TextView scoreText;
    String selectedSpinnerItem;
    Dog dogObject;
    int resourceId;
    boolean isTimerToggled;
    boolean isAnswerSubmitted;
    int score;
    String resourceName;
    long millisRemaining;
    private CountDownTimer countDownTimer;
    boolean didCountDownStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);
        //getting the intent
        final Intent intent = getIntent();
        isTimerToggled = intent.getBooleanExtra("timerToggle", false);


        //initializing and accessing values after application is created
        submitBreedButton = (Button) findViewById(R.id.submit_breed_button);
        ImageView dogImageView = (ImageView) findViewById(R.id.breed_image_view);
        timerProgress = (ProgressBar) findViewById(R.id.identify_breed_progress_bar);
        countDown = (TextView) findViewById(R.id.identify_breed_timer);
        scoreText = (TextView) findViewById(R.id.score_text);

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate: saved instance != null");
            isTimerToggled = savedInstanceState.getBoolean("timerToggle");
            isAnswerSubmitted = savedInstanceState.getBoolean("isAnswerSubmitted");
            selectedSpinnerItem = savedInstanceState.getString("selectedSpinnerItem");
            resourceName = savedInstanceState.getString("resourceName");
            resourceId = savedInstanceState.getInt("imageResource");
            dogObject = (Dog) savedInstanceState.getSerializable("dogObj");
            millisRemaining = savedInstanceState.getLong("millisRemaining");
            didCountDownStart = savedInstanceState.getBoolean("didCountDownStart");
            score = savedInstanceState.getInt("score");
            if (didCountDownStart && isTimerToggled) {
                startCountDownTimer(millisRemaining, INTERVAL);
            }
        } else {
            Log.d(TAG, "onCreate: saved instance == null");
            dogObject = dogDetails.getRandomDog(); //randomizing the displayed image
            resourceName = dogObject.getResourceName();
            millisRemaining = 11000;
        }
        String scoreMessage = "Your score is " + score;
//        scoreText.setText(scoreMessage);
        //Assigning the dogs
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
                    Intent intent1 = new Intent(IdentifyBreedActivity.this, IdentifyBreedActivity.class);
                    intent1.putExtra("timerToggle", isTimerToggled);
                    startActivity(intent1);
                }
//                else if(!isAnswerSubmitted){
//                    ;
//                }
                else{
                    checkAnswer(dogObject, selectedSpinnerItem);
                    //checking if an answer is selected and submitted
                    isAnswerSubmitted = true;
                }
            }
        });

        if (isTimerToggled) {
            timerProgress.setVisibility(View.VISIBLE);
            countDown.setVisibility(View.VISIBLE);
            //starting the timer
            startCountDownTimer(millisRemaining, INTERVAL);
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

        //using spannable string to change colours of a string
        SpannableString wrongAnswerSS = new SpannableString(wrongAnswerMessage);
        SpannableString correctAnswerSS = new SpannableString(correctAnswerMessage);
        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        int answerLength = dog.getBreed().length();
        wrongAnswerSS.setSpan(fcsBlue, 48, (49 + answerLength + 1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        correctAlertTitle.setTextColor(Color.parseColor("#1BE59D"));
        correctAlertTitle.setText(R.string.correct_answer_title);
        correctAlertTitle.setPadding(40, 30, 20, 30);
        correctAlertTitle.setTextSize(20F);
        correctAlertTitle.setTypeface(null, Typeface.BOLD);

        wrongAlertTitle.setTextColor(Color.parseColor("#FF0000"));
        wrongAlertTitle.setText("WRONG!");
        wrongAlertTitle.setPadding(40, 30, 20, 30);
        wrongAlertTitle.setTextSize(20F);
        wrongAlertTitle.setTypeface(null, Typeface.BOLD);

        if (dog.getBreed().equals(spinnerItemName)) {
            score++;
            scoreText.setText(Integer.toString(score));
            AlertDialogComponent.identifyBreedAlert(this, submitBreedButton, correctAlertTitle, correctAnswerSS, false);
        } else {
            AlertDialogComponent.identifyBreedAlert(this, submitBreedButton, wrongAlertTitle, wrongAnswerSS, false);
        }

        identifyBreedSpinner.setEnabled(false);     //disabling the spinner
        //if timer toggle is enabled then the countdown will stop
        if (isTimerToggled) {
            stopCountDownTimer();
        }
    }

    //saving the state
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("timerToggle", isTimerToggled);
        outState.putString("selectedSpinnerItem", selectedSpinnerItem);
        outState.putString("resourceName", resourceName);
        outState.putInt("imageResource", resourceId);
        outState.putBoolean("isAnswerSubmitted", isAnswerSubmitted);
        outState.putSerializable("dogObj", dogObject);
        outState.putLong("millisRemaining", millisRemaining);
        outState.putBoolean("didCountDownStart", didCountDownStart);
        outState.putInt("score", score);
//        Log.d(TAG, "onSaveInstanceState: " + randVal);
    }

    //method to start countdown timer
    private void startCountDownTimer(long duration, long interval) {
        countDownTimer = new CountDownTimer(duration, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                Log.d(TAG, "onTick: " + seconds);
                timerProgress.setProgress(seconds);
                countDown.setText(Integer.toString(seconds));
                millisRemaining = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                countDown.setText("0");
                if (!isAnswerSubmitted && !((Activity) context).isFinishing()) {
                    Log.d(TAG, "onFinish: Timer finished");
                    String answer = "You ran out of time. The correct answer is " + dogObject.getBreed();
                    AlertDialogComponent.basicAlert(IdentifyBreedActivity.this, answer, "You ran out of time.");
                    if (!isAnswerSubmitted){
                        submitBreedButton.setEnabled(true);
                        submitBreedButton.setText(R.string.next);
                        identifyBreedSpinner.setEnabled(false);
                    }
                }
            }
        };

        didCountDownStart = true;
        //starting the countdown
        countDownTimer.start();
    }

    //method to cancel the countdown timer
    private void stopCountDownTimer() {
        if (isTimerToggled) {
            countDownTimer.cancel();
            didCountDownStart = false;
        }
    }

    //stopping the countdown when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCountDownTimer();
    }
}
