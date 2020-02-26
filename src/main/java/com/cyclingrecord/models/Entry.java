package com.cyclingrecord.models;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Entry {
    private String date;
    private int speed;
    private int time;
    private int distance;
    private int id;
    private int nextId = 1;

    public Entry(){
        this.id=nextId;
        nextId++;
    }

    public Entry(String date){
        this();
        this.date = date;
    }

    public Entry(String date, int time, int distance){
        this();
        this.speed = distance/(time/60);
        this.time = time;
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return id == entry.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

