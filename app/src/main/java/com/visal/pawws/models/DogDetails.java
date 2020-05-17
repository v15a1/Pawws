package com.visal.pawws.models;

import android.util.Log;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class DogDetails {

    private static final String CLASS_NAME = DogDetails.class.getSimpleName();

    private Map<String, String> dogDetails = new Hashtable<String, String>();
    private String[] dogBreeds;
    private Integer[] dogImageCount;

    public DogDetails() {
        dogBreeds = new String[]{
                "n02108915",
                "n02105855",
                "n02112018",
                "n02111889",
                "n02110185",
                "n02110063",
                "n02108551",
                "n02108422",
                "n02107142",
                "n02106662",
        };
        dogImageCount = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        addDogDetails();
    }

    private void addDogDetails() {
        dogDetails.put("n02108915", "French Bulldog");
        dogDetails.put("n02105855", "Shetland Sheepdog");
        dogDetails.put("n02112018", "Pomeranian");
        dogDetails.put("n02111889", "Samoyed");
        dogDetails.put("n02110185", "Siberian Husky");
        dogDetails.put("n02110063", "Malamute");
        dogDetails.put("n02108551", "Tibetan Mastiff");
        dogDetails.put("n02108422", "Bull Mastiff");
        dogDetails.put("n02107142", "Doberman");
        dogDetails.put("n02106662", "German Shepard");
    }

    //method to get a random Dog object
    public Dog getRandomDog() {
        //using Random class to generate a random number
        Random random = new Random();

        //getting random indices
        int randBreed = random.nextInt(dogBreeds.length);
        int randDogNumIndex = random.nextInt(dogImageCount.length - 1) + 1;

        String resource = dogBreeds[randBreed] + "_" + dogImageCount[randDogNumIndex];
        return new Dog(dogDetails.get(dogBreeds[randBreed]), resource);
    }

    //method to display searched breed details
    public String getSearchedBreedDetails(String searchValue) {
        if (dogDetails.containsValue(searchValue)) {
            Log.d(CLASS_NAME, "contains" + searchValue);
            //traversing the map of breeds to get the details if found
            for (Map.Entry entry : dogDetails.entrySet()) {
                Log.d(CLASS_NAME, entry.toString());
                if (entry.getValue().equals(searchValue)) {
                    String breedKey = entry.getKey().toString();
                    String resource = breedKey + "_" + (new Random().nextInt(dogBreeds.length - 1) + 1);
                    Log.d(CLASS_NAME, resource);
                    return resource;
                }
            }
        }
        return null;
    }
}
