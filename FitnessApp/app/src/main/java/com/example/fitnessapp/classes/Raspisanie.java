package com.example.fitnessapp.classes;

public class Raspisanie {
    String name, time, trener_name;

    public Raspisanie() {
    }

    public Raspisanie(String name, String time, String trener_name) {
        this.name = name;
        this.time = time;
        this.trener_name = trener_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrener_name() {
        return trener_name;
    }

    public void setTrener_name(String trener_name) {
        this.trener_name = trener_name;
    }
}
