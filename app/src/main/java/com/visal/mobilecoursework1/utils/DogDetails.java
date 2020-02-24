package com.visal.mobilecoursework1.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DogDetails {

    private Map<String, String> dogDetails = new Hashtable<String, String>();
    private String[] dogBreeds;
    Integer[] dogImageCount;

    public DogDetails() {
        dogBreeds = new String[]{
          "n02108915",
//          "n02105855",
//          "n02112018",
//          "n02111889",
//          "",
//          "",
//          "",
//          "",
//          "",
//          "",
        };
        dogImageCount = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        addDogDetails();
    }

    private void addDogDetails(){
        dogDetails.put("n02108915", "French Bulldog");
//        dogDetails.put("n02105855", "Shetland Sheepdog");
//        dogDetails.put("n02112018", "Pomeranian");
//        dogDetails.put("n02111889", "Samoyed");
//        dogDetails.put("n02110185", "Siberian Husky");
//        dogDetails.put("n02110063", "Malamute");
//        dogDetails.put("n02108551", "Tibetan Mastiff");
//        dogDetails.put("n02108422", "Bull Mastiff");
//        dogDetails.put("n02107142", "Doberman");
//        dogDetails.put("n02106662", "German Shepard");
    }

    public String getRandomDog(){
        Random random = new Random();

        int randBreedIndex = random.nextInt(dogBreeds.length);
        Log.d("DogDetails", Integer.toString(randBreedIndex));
        int randDogNumIndex = random.nextInt(dogImageCount.length);
        Log.d("DogDetails", Integer.toString(randDogNumIndex));
        return dogBreeds[randBreedIndex] + "_" + dogImageCount[randDogNumIndex];
    }
}
