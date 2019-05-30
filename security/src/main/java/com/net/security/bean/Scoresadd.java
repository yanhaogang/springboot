package com.net.security.bean;

import java.util.HashMap;

public class Scoresadd {
    private String country;
    private String index;
    private HashMap<String,Float> scores;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public HashMap<String, Float> getScores() {
        return scores;
    }

    public void setScores(HashMap<String, Float> scores) {
        this.scores = scores;
    }
}
