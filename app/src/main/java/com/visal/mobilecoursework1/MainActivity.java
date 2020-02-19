package com.visal.mobilecoursework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "MainActivity";

    Button identifyDogButton;
    Button identifyBreedButton;
    Button searchBreedButton;
    BottomNavigationView bottomNavigationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiating button
        identifyDogButton = (Button) findViewById(R.id.identify_dog_button);
        identifyBreedButton = (Button) findViewById(R.id.identify_breed_button);
        searchBreedButton = (Button) findViewById(R.id.search_breed_button);


        //setting onClick listener to navigate to Identify dog_watermark page
        identifyDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IdentifyDogActivity.class));
            }
        });

        //setting onClick listener to navigate to Identify breed page
        identifyBreedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IdentifyBreedActivity.class));
            }
        });

        //setting onClick listener to navigate to Search breed page
        searchBreedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchBreedActivity.class));
            }
        });
    }
}


