package com.cyclingrecord.models;

import javax.persistence.Entity;

@Entity
public class YearTotals extends AbstractEntity{

    private Integer year;
    private String month;
    private Float total;
    private int grandTotal;
    private String monthAbbr;

    public YearTotals(){}

    public YearTotals(Integer year, String month, Float total, int grandTotal, String monthAbbr){
        this.year = year;
        this.month = month;
        this.total = total;
        this.grandTotal = grandTotal;
        this.monthAbbr = monthAbbr;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getMonthAbbr() {
        return monthAbbr;
    }

    public void setMonthAbbr(String monthAbbr) {
        this.monthAbbr = monthAbbr;
    }
}
