package com.cyclingrecord.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Entry extends AbstractEntity{


    private String date;
    private double speed;
    private float time;
    private float distance;
    private float totalDistance;

    public Entry(){ }

    public Entry(String date){
        this.date = date;
    }

    public Entry(float time, float distance){
        this();
        this.time = time;
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) { this.speed = speed; }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


    public float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }


}

