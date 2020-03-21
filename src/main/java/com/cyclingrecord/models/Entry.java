package com.cyclingrecord.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Entry {
    @Id
    @GeneratedValue
    private int id;

    private String date;
    private int speed;
    private int time;
    private int distance;

    public Entry(){ }

    public Entry(String date){
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
        return Objects.equals(date, entry.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

}

