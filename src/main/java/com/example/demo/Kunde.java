package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Kunde {
    private int id;
    private String personNr, navn, adresse, kjennetegn, bilmerke, biltype;

    public Kunde(int id, String personNr, String navn, String adresse, String kjennetegn,
                 String bilmerke, String biltype) {
        this.personNr = personNr;
        this.navn = navn;
        this.adresse = adresse;
        this.kjennetegn = kjennetegn;
        this.bilmerke = bilmerke;
        this.biltype = biltype;
    }

    public Kunde() {}


    public void setId(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public String getPersonNr() {
        return personNr;
    }

    public void setPersonNr(String personNr) {
        this.personNr = personNr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getKjennetegn() {
        return kjennetegn;
    }

    public void setKjennetegn(String kjennetegn) {
        this.kjennetegn = kjennetegn;
    }

    public String getBilmerke() {
        return bilmerke;
    }

    public void setBilmerke(String bilmerke) {
        this.bilmerke = bilmerke;
    }

    public String getBiltype() {
        return biltype;
    }

    public void setBiltype(String biltype) {
        this.biltype = biltype;
    }
}
