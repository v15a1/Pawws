package com.visal.mobilecoursework1.utils;

import java.io.Serializable;

public class Dog implements Serializable {
    private String breed;
    private String resourceName;

    public Dog(String breed, String resourceName) {
        this.breed = breed;
        this.resourceName = resourceName;
    }

    public String getBreed() {
        return breed;
    }

    public String getResourceName() {
        return resourceName;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", resourceName='" + resourceName + '\'' +
                '}';
    }
}
