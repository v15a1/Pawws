package com.visal.mobilecoursework1.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.visal.mobilecoursework1.R;
import com.visal.mobilecoursework1.ui_components.AlertDialogComponent;
import com.visal.mobilecoursework1.utils.Dog;
import com.visal.mobilecoursework1.utils.DogDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class IdentifyDogActivity extends AppCompatActivity {

    private static final String TAG = IdentifyDogActivity.class.getSimpleName();
    private static final String ACTIVITY_TITLE_NAME = "Identify Dogs";

    private Context context = this;
    private static final long INTERVAL = 1000;
    DogDetails dogDetails = new DogDetails();
    private Button identifyDogButton;
    private int resourceImage1;
    private int resourceImage2;
    private int resourceImage3;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private List<String> uniqueDogBreedNames = new ArrayList<String>();
    private List<Dog> uniqueDogs = new ArrayList<Dog>();
    private String answer;
    private int randomBreedIndex;
    private Drawable correctAnswerHighlight;
    private boolean isTimerToggled;
    private ProgressBar timerProgress;
    private TextView countDown;
    private CountDownTimer countDownTimer;
    long millisRemaining;
    boolean didCountDownStart;
    boolean isAnswerSubmitted;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dog);

        //accessing elements in the UI
        identifyDogButton = (Button) findViewById(R.id.identify_dogs_button);
        imageView1 = (ImageView) findViewById(R.id.dog_image_one);
        imageView2 = (ImageView) findViewById(R.id.dog_image_two);
        imageView3 = (ImageView) findViewById(R.id.dog_image_three);
        TextView breedTextView = (TextView) findViewById(R.id.breed_name);
        timerProgress = (ProgressBar) findViewById(R.id.identify_dog_progress_bar);
        countDown = (TextView) findViewById(R.id.identify_dog_timer);

        identifyDogButton.setEnabled(false);
        countDown.setVisibility(View.GONE);
        timerProgress.setVisibility(View.GONE);
        //getting the intent
        Intent intent = getIntent();
        isTimerToggled = intent.getBooleanExtra("timerToggle", false);
//        Log.d(TAG, "onCreate: " + isTimerToggled);

        if (savedInstanceState != null) {
            uniqueDogBreedNames = savedInstanceState.getStringArrayList("uniqueDogBreedNames");
            uniqueDogs = (List<Dog>) savedInstanceState.getSerializable("uniqueDogs");
            answer = savedInstanceState.getString("answer");
            randomBreedIndex = savedInstanceState.getInt("randomBreedIndex");
            millisRemaining = savedInstanceState.getLong("millisRemaining");
            didCountDownStart = savedInstanceState.getBoolean("didCountDownStart");
            isAnswerSubmitted = savedInstanceState.getBoolean("isAnswerSubmitted");
        } else {
            millisRemaining = 11000;
            //generating unique dogs and breed images
            while (uniqueDogBreedNames.size() < 3) {
                Dog dog = dogDetails.getRandomDog();
                if (!uniqueDogBreedNames.contains(dog.getBreed())) {
                    uniqueDogBreedNames.add(dog.getBreed());
                    uniqueDogs.add(dog);
                }
            }
        }


        Log.d(TAG, Arrays.toString(uniqueDogBreedNames.toArray()));

        //assigning a dog as the correct answer
        randomBreedIndex = new Random().nextInt(3);
        answer = uniqueDogBreedNames.get(randomBreedIndex);

        resourceImage1 = getResources().getIdentifier(uniqueDogs.get(0).getResourceName(), "drawable", "com.visal.mobilecoursework1");
        resourceImage2 = getResources().getIdentifier(uniqueDogs.get(1).getResourceName(), "drawable", "com.visal.mobilecoursework1");
        resourceImage3 = getResources().getIdentifier(uniqueDogs.get(2).getResourceName(), "drawable", "com.visal.mobilecoursework1");

        imageView1.setImageResource(resourceImage1);
        imageView2.setImageResource(resourceImage2);
        imageView3.setImageResource(resourceImage3);

        breedTextView.setText(answer);


        //displaying and starting the timer if the toggle is true
        if (isTimerToggled) {
            countDown.setVisibility(View.VISIBLE);
            timerProgress.setVisibility(View.VISIBLE);
            startCountDownTimer(millisRemaining, INTERVAL);
        }

        //setting onclick Listeners
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(uniqueDogBreedNames.get(0));
                stopCountDownTimer();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(uniqueDogBreedNames.get(1));
                stopCountDownTimer();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(uniqueDogBreedNames.get(2));
                stopCountDownTimer();
            }
        });

        identifyDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IdentifyDogActivity.this, IdentifyDogActivity.class));
            }
        });

        //method to backtrack activities
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Log.i(TAG, "ActionBar != null");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(ACTIVITY_TITLE_NAME);
        }
    }

    //https://stackoverflow.com/questions/35810229/how-to-display-and-set-click-event-on-back-arrow-on-toolbar
    //method to add onPress functionality to the back button on the action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //if the id menu previous item is the home screen. Then the activity changes to the previous activity
        if (item.getItemId() == android.R.id.home) {
            //method to change activities
            startActivity(new Intent(IdentifyDogActivity.this, MainActivity.class));
            finish();       //method call to destroy the activity from the memory
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkAnswer(String selectedImage) {
        String correctAnswerMessage = "The selected answer is correct. Well done!!!";
        String wrongAnswerMessage = "Your answer is incorrect. the correct answer is image number " + (randomBreedIndex + 1) + ". Better luck next time";
        TextView correctAlertTitle = new TextView(this);
        TextView wrongAlertTitle = new TextView(this);

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

        if (selectedImage.equals(answer)) {
            AlertDialogComponent.identifyDogAlert(this, correctAlertTitle, correctAnswerMessage, false);
        } else {
            AlertDialogComponent.identifyDogAlert(this, wrongAlertTitle, wrongAnswerMessage, false);
            imageView1.setBackground(correctAnswerHighlight);
            Log.d(TAG, "Highlight set");
        }

        //disabling the imageviews to prevent further interaction
        imageView1.setEnabled(false);
        imageView2.setEnabled(false);
        imageView3.setEnabled(false);

        identifyDogButton.setEnabled(true);
    }

    //saving state
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("uniqueDogBreedNames", (ArrayList<String>) uniqueDogBreedNames);
        outState.putSerializable("uniqueDogs", (ArrayList<Dog>) uniqueDogs);
        outState.putString("answer", answer);
        outState.putInt("randomBreedIndex", randomBreedIndex);
        outState.putLong("millisRemaining", millisRemaining);
        outState.putBoolean("isAnswerSubmitted", isAnswerSubmitted);
        outState.putBoolean("didCountDownStart", didCountDownStart);
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
                    String answer = "You ran out of time. The correct answer is image number" + (randomBreedIndex + 1);
                    AlertDialogComponent.basicAlert(IdentifyDogActivity.this, answer, "You ran out of time.");
                    identifyDogButton.setEnabled(true);
                    identifyDogButton.setText(R.string.next);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCountDownTimer();
    }
}


