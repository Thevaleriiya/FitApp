package com.example.fitnessapp.classes;

public class UserZapis {
    String data, time, trener;

    public UserZapis(String data, String time, String trener) {
        this.data = data;
        this.time = time;
        this.trener = trener;
    }

    public UserZapis() {
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
