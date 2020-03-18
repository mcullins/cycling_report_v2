package com.cyclingrecord.models;

import java.util.Objects;

public class SatTotal {
    private float total;
    private String date;

    public SatTotal(){};

    public SatTotal(float total, String date) {
        this.total = total;
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SatTotal satTotal = (SatTotal) o;
        return Objects.equals(date, satTotal.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
