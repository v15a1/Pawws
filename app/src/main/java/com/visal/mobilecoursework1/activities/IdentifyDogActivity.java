package com.visal.mobilecoursework1.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.visal.mobilecoursework1.R;
import com.visal.mobilecoursework1.utils.Dog;
import com.visal.mobilecoursework1.utils.DogDetails;

import java.util.ArrayList;
import java.util.List;


public class IdentifyDogActivity extends AppCompatActivity{

    private static final String ACTIVITY_NAME = "IdentifyDogActivity";
    private static final String ACTIVITY_TITLE_NAME = "Identify Dogs";

    DogDetails dogDetails = new DogDetails();
    int resourceImage1;
    int resourceImage2;
    int resourceImage3;
    List<Dog> uniqueDogList = new ArrayList<Dog>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dog);

        ImageView imageView1 = (ImageView) findViewById(R.id.dog_image_one);
        ImageView imageView2 = (ImageView) findViewById(R.id.dog_image_two);
        ImageView imageView3 = (ImageView) findViewById(R.id.dog_image_three);

        Dog dog1 = dogDetails.getRandomDog();
        Dog dog2 = dogDetails.getRandomDog();
        Dog dog3 = dogDetails.getRandomDog();

//        while (uniqueDogList.size() != 3){
//            Dog dog = dogDetails.getRandomDog();
////            if (!uniqueDogList.contains(dog.getBreed()))
//        }

        resourceImage1 = getResources().getIdentifier(dog1.getResourceName(), "drawable", "com.visal.mobilecoursework1");
        resourceImage2 = getResources().getIdentifier(dog2.getResourceName(), "drawable", "com.visal.mobilecoursework1");
        resourceImage3 = getResources().getIdentifier(dog3.getResourceName(), "drawable", "com.visal.mobilecoursework1");

        imageView1.setImageResource(resourceImage1);
        imageView2.setImageResource(resourceImage2);
        imageView3.setImageResource(resourceImage3);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

}
