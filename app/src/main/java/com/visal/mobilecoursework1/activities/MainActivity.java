package com.visal.mobilecoursework1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.visal.mobilecoursework1.R;

public class MainActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "MainActivity";

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
        identifyDogButton = (Button) findViewById(R.id.identify_dog_button);
        identifyBreedButton = (Button) findViewById(R.id.identify_breed_button);
        searchBreedButton = (Button) findViewById(R.id.search_breed_button);
        switchMaterial = (SwitchMaterial) findViewById(R.id.timer_toggle);

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isTimerToggled = true;
                    Log.d(ACTIVITY_NAME, "onCheckedChanged: true");
                }else{
                    isTimerToggled = false;
                    Log.d(ACTIVITY_NAME, "onCheckedChanged: false");
                }
            }
        });
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


