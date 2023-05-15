package com.example.fitnessapp.classes;

public class UserUslugi {
    String date_end, date_start, usluga_number;

    public UserUslugi(String date_start, String date_end, String usluga_number) {
        this.date_end = date_end;
        this.date_start = date_start;
        this.usluga_number = usluga_number;
    }

    public UserUslugi() {
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getUsluga_number() {
        return usluga_number;
    }

    public void setUsluga_number(String usluga_number) {
        this.usluga_number = usluga_number;
    }
}
