package com.example.andrzejdevcom.sdacademyaplikacja.model;

/**
 * Created by RENT on 2017-05-29.
 */

public class Deck {
    private boolean succes;
    private String deckId;
    private boolean shuffled;
    private int remaining;

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
