package com.example.fitnessapp.classes;

import java.util.ArrayList;

public class User {
    String name;
    ArrayList<ArrayList<UserUslugi>> uslugi;



    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, ArrayList<ArrayList<UserUslugi>> uslugi) {
        this.name = name;
        this.uslugi = uslugi;
    }

    public ArrayList<ArrayList<UserUslugi>> getUslugi() {
        return uslugi;
    }

    public void setUslugi(ArrayList<ArrayList<UserUslugi>> uslugi) {
        this.uslugi = uslugi;
    }
}
