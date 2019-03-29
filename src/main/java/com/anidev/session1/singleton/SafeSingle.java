package com.anidev.session1.singleton;

import java.util.UUID;

public enum SafeSingle {

    INSTANCE;

    private UUID uuid;

    private int counter = 0;

    SafeSingle() {
        uuid = UUID.randomUUID();
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public UUID getUuid() {
        return uuid;
    }

}
