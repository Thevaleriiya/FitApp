package com.example.fitnessapp.classes;

public class UserZapis {
    String data, time, trener;
    Integer status;

    public UserZapis(String data, String time, String trener, Integer status) {
        this.data = data;
        this.time = time;
        this.trener = trener;
        this.status = status;
    }

    public UserZapis() {
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
