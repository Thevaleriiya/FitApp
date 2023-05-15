package com.example.fitnessapp.classes;

public class UserZapis {
    String data, time, trener, day_of_week;
    Integer status;

    public UserZapis(String data, String time, String trener, String day_of_week, Integer status) {
        this.data = data;
        this.time = time;
        this.trener = trener;
        this.day_of_week = day_of_week;
        this.status = status;
    }

    public UserZapis() {
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrener() {
        return trener;
    }

    public void setTrener(String trener) {
        this.trener = trener;
    }
}
