package com.example.demo;

import java.util.*;

public class Biltype {
    private String merke;
    private List<String> type;

    public Biltype(String merke) {
        this.merke = merke;
        type = new ArrayList<>();
    }

    public String getMerke() {
        return merke;
    }

    public void setMerke(String merke) {
        this.merke = merke;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(String[] typeInn) {
        for(String s : typeInn) type.add(s);
    }
}
