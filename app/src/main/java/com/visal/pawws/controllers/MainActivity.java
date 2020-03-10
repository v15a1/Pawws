package com.visal.pawws.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.visal.pawws.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button identifyDogButton;
    Button identifyBreedButton;
    Button searchBreedButton;
    SwitchMaterial switchMaterial;
    boolean isTimerToggled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiating button
        Log.i(TAG, "Successfully launched " + TAG);
        identifyDogButton = (Button) findViewById(R.id.identify_dog_button);
        identifyBreedButton = (Button) findViewById(R.id.identify_breed_button);
        searchBreedButton = (Button) findViewById(R.id.search_breed_button);
        switchMaterial = (SwitchMaterial) findViewById(R.id.timer_toggle);

        if (savedInstanceState != null){
            switchMaterial.setActivated(savedInstanceState.getBoolean("timerToggle"));
        }

        //method to toggle the timer switch
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isTimerToggled = true;
                    Log.d(TAG, "onCheckedChanged: true");
                } else {
                    isTimerToggled = false;
                    Log.d(TAG, "onCheckedChanged: false");
                }
            }
        });
        //setting onClick listener to navigate to Identify dog_watermark page
        identifyDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IdentifyDogActivity.class)
                        .putExtra("timerToggle", isTimerToggled));
            }
        });

        //setting onClick listener to navigate to Identify breed page
        identifyBreedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IdentifyBreedActivity.class)
                        .putExtra("timerToggle", isTimerToggled));
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


