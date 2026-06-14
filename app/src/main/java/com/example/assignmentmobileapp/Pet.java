package com.example.assignmentmobileapp;

import java.util.Random;

public class Pet{
    private int hunger;
    private int happiness;
    private int cleanliness;


    public Pet(){
        this.hunger=50;
        this.happiness=50;
        this.cleanliness = 50;
    }
    public void eat(){
        if(this.hunger < 100){
            this.hunger += 10;
        }
        if(this.happiness < 100){
            this.happiness += 10;
        }
    }

    public int getHunger() {
        return hunger;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public int getHappiness() {
        return happiness;
    }

    public void passTime() {
        Random random = new Random();
        int hungerDrop = random.nextInt(5) + 1;
        int happinessDrop = random.nextInt(5) + 1;
        int cleanDrop = random.nextInt(5) + 1;

        this.happiness = Math.max(0, this.happiness - happinessDrop);
        this.hunger = Math.max(0, this.hunger - hungerDrop);
        this.cleanliness = Math.max(0, this.cleanliness - cleanDrop);
    }
    public void clean(){
        if(this.cleanliness < 100){
            this.cleanliness += 10;
        }
    }
}
