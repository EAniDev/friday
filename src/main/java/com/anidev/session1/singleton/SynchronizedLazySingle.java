package com.anidev.session1.singleton;

import java.util.UUID;

public class SynchronizedLazySingle {

    private static SynchronizedLazySingle instance;

    synchronized public static SynchronizedLazySingle getInstance() {
        if (instance == null) {
            instance = new SynchronizedLazySingle();
        }
        return instance;
    }

    private int counter = 0;

    private UUID singletonId;

    private SynchronizedLazySingle() {
        singletonId = UUID.randomUUID();
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }


}
