package com.visal.mobilecoursework1.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.visal.mobilecoursework1.R;
import com.visal.mobilecoursework1.ui_components.AlertDialogComponent;
import com.visal.mobilecoursework1.utils.AlertMessages;
import com.visal.mobilecoursework1.utils.Dog;
import com.visal.mobilecoursework1.utils.DogDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class IdentifyDogActivity extends AppCompatActivity{

    private static final String ACTIVITY_NAME = "IdentifyDogActivity";
    private static final String ACTIVITY_TITLE_NAME = "Identify Dogs";

    DogDetails dogDetails = new DogDetails();

    private Button identifyDogButton;
    int resourceImage1;
    int resourceImage2;
    int resourceImage3;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    List<String> uniqueDogBreedNames = new ArrayList<String>();
    List<Dog> uniqueDogs = new ArrayList<Dog>();
    String answer;
    int randomBreedIndex;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dog);

        identifyDogButton = (Button) findViewById(R.id.identify_dogs_button);
        imageView1 = (ImageView) findViewById(R.id.dog_image_one);
        imageView2 = (ImageView) findViewById(R.id.dog_image_two);
        imageView3 = (ImageView) findViewById(R.id.dog_image_three);
        TextView breedTextView = (TextView) findViewById(R.id.breed_name);
        identifyDogButton.setEnabled(false);

        //generating unique dogs and breed images
        while (uniqueDogBreedNames.size() < 3){
            Dog dog = dogDetails.getRandomDog();
            if (!uniqueDogBreedNames.contains(dog.getBreed())){
                uniqueDogBreedNames.add(dog.getBreed());
                uniqueDogs.add(dog);
            }
        }
        Log.d(ACTIVITY_NAME, Arrays.toString(uniqueDogBreedNames.toArray()));

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
//        identifyDogButton.setEnabled(false);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(uniqueDogBreedNames.get(0));
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(uniqueDogBreedNames.get(1));
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(uniqueDogBreedNames.get(2));
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
        if (actionBar!= null){
            Log.i(ACTIVITY_NAME, "ActionBar != null");
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
        } return super.onOptionsItemSelected(item);
    }

    public void checkAnswer(String selectedImage){
        String correctAnswerMessage = "The selected answer is correct. Well done!!!";
        String wrongAnswerMessage = "Your answer is incorrect. the correct answer is " + (randomBreedIndex + 1) + " image. Better luck next time";
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

        if (selectedImage.equals(answer)){
            AlertDialogComponent.identifyDogAlert(this, correctAlertTitle, correctAnswerMessage, false);
        }else{
            Drawable correctAnswerHighlight = getResources().getDrawable(R.drawable.correct_answer_highlight);
            AlertDialogComponent.identifyDogAlert(this, wrongAlertTitle, wrongAnswerMessage, false);
            imageView1.setBackground(correctAnswerHighlight);
            Log.d(ACTIVITY_NAME, "Highlight set");
        }

        //disabling the imageviews to prevent further interaction
        imageView1.setEnabled(false);
        imageView2.setEnabled(false);
        imageView3.setEnabled(false);

        identifyDogButton.setEnabled(true);
    }

}
