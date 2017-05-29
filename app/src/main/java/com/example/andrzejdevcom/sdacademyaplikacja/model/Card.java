package com.example.andrzejdevcom.sdacademyaplikacja.model;

/**
 * Created by RENT on 2017-05-29.
 */

public class Card {

    private String image;
    private int rank;
    private String suit;
    private String code;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
