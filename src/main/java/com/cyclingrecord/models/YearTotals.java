package com.cyclingrecord.models;

import javax.persistence.Entity;

@Entity
public class YearTotals extends AbstractEntity{

    private int year;
    private String month;
    private int total;
    private int grandTotal;

    public YearTotals(){}

    public YearTotals(int year, String month, int total, int grandTotal){
        this.year = year;
        this.month = month;
        this.total = total;
        this.grandTotal = grandTotal;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }
}
