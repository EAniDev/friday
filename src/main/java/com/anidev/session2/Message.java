package com.anidev.session2;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Message {

    private static final Message EMPTY = new Message(null, null);

    private static Set<Message> CACHE = new HashSet<>();

    public static Message createOrGet(Object payload, String originalAuthor) {
        for (Message m : CACHE)
            if (m.getOriginalAuthor().equals(originalAuthor) && m.getPayload().equals(payload)) {
                return m;
            }
        Message newMsg = new Message(originalAuthor, payload);
        CACHE.add(newMsg);
        return newMsg;
    }

    public static Message empty() {
        return EMPTY;
    }

    public static Message heartbeat() {
        return new Message("HB", "HB");
    }

    public static Message acknowledgement(String ackState) {
        return new Message("ACK", ackState) {
            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Message)) return false;
                Message other = (Message) obj;
                return other.originalAuthor.equals("ACK") && other.payload.equals(getPayload());
            }
        };
    }

    private Object payload;

    private String originalAuthor;

    private Instant creationTime;

    private Boolean isPermanent;

    private List<String> authors;

    private Message(String originalAuthor, Object payload) {
        this.originalAuthor = originalAuthor;
        this.payload = payload;
        creationTime = Instant.now();
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getPermanent() {
        return isPermanent;
    }

    public void setPermanent(Boolean permanent) {
        isPermanent = permanent;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
