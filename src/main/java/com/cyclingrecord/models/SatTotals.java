package com.cyclingrecord.models;

public class SatTotals {
    int totalDistance;
    String date;

    public SatTotals(){}

    public SatTotals(int totalDistance, String date) {
        this.totalDistance = totalDistance;
        this.date = date;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
