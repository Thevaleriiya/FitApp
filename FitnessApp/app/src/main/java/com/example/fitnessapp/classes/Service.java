package com.example.fitnessapp.classes;

public class Service {
    String id;
    String name;
    String srok;
    String time;
    String visit;
    String price;
    String zamorozka;

    public Service(String id, String name, String srok, String time, String visit, String price, String zamorozka) {
        this.id = id;
        this.name = name;
        this.srok = srok;
        this.time = time;
        this.visit = visit;
        this.price = price;
        this.zamorozka = zamorozka;
    }

    public Service() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrok() {
        return srok;
    }

    public void setSrok(String srok) {
        this.srok = srok;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getZamorozka() {
        return zamorozka;
    }

    public void setZamorozka(String zamorozka) {
        this.zamorozka = zamorozka;
    }
}
