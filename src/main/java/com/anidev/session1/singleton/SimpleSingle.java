package com.anidev.session1.singleton;

import java.util.UUID;

public class SimpleSingle {

    private static SimpleSingle instance = new SimpleSingle();

    public static SimpleSingle getInstance() {
        return instance;
    }

    private int counter = 0;

    private UUID singletonId;

    SimpleSingle() {
        singletonId = UUID.randomUUID();
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public UUID getSingletonId() {
        return singletonId;
    }

}
