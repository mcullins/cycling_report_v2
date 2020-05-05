package com.cyclingrecord.models;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class Entry extends AbstractEntity{


    private String date;
    private double speed;
    private Float time;
    private Float distance;
    private Float totalDistance;

    public Entry(){ }

    public Entry(String date){
        this.date = date;
    }

    public Entry(Float time, Float distance){
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

    public void setTime(Float time) {
        this.time = time;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

}

