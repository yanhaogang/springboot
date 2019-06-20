package com.net.security.bean;

public class Archive {
    private int id;
    private int mainid;
    private int index1id;
    private String index1name;
    private Float score;
    private int setid;
    private String mainname;

    public String getMainname() {
        return mainname;
    }

    public void setMainname(String mainname) {
        this.mainname = mainname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainid() {
        return mainid;
    }

    public void setMainid(int mainid) {
        this.mainid = mainid;
    }

    public int getIndex1id() {
        return index1id;
    }

    public void setIndex1id(int index1id) {
        this.index1id = index1id;
    }

    public String getIndex1name() {
        return index1name;
    }

    public void setIndex1name(String index1name) {
        this.index1name = index1name;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public int getSetid() {
        return setid;
    }

    public void setSetid(int setid) {
        this.setid = setid;
    }
}
