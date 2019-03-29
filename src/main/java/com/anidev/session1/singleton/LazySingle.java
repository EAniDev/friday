package com.anidev.session1.singleton;

import java.util.UUID;

public class LazySingle {

    private static LazySingle instance;

    public static LazySingle getInstance() {
        if (instance == null) {
            instance = new LazySingle();
        }
        return instance;
    }

    private int counter = 0;

    private UUID singletonId;

    private LazySingle() {
        singletonId = UUID.randomUUID();
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }


}
