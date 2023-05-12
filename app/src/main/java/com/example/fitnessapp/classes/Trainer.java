package com.example.fitnessapp.classes;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    String about;
    String foto;
    String id;
    String name;
    String surname;
    ArrayList<String> spec = new ArrayList<String>();

    public Trainer() {
    }

    public Trainer(String about, String foto, String id, String name, String surname, ArrayList<String> spec) {
        this.about = about;
        this.foto = foto;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.spec = spec;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List getSpec() {
        return spec;
    }

    public void setSpec(ArrayList<String> spec) {
        this.spec = spec;
    }
}
