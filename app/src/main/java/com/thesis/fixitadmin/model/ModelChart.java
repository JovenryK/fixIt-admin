package com.thesis.fixitadmin.model;

public class ModelChart {
    String month;
    int Sales;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getSales() {
        return Sales;
    }

    public void setSales(int sales) {
        Sales = sales;
    }

    public ModelChart(String month, int sales) {
        this.month = month;
        Sales = sales;
    }
}