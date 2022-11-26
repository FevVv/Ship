package com.acmegroup.ship;

import java.util.Random;

public class RandomNumber{
    Random r = new Random();
    int randomNumber;
    private boolean running = true;

    public int getRandomNumber() {
        return randomNumber;
    }

    public RandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;

    }
    public void update(){
        randomNumber = r.nextInt(5);
    }

}
