package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BilRepository {
    private List<Biltype> bilListe = new ArrayList<>();

    public BilRepository() {
        Biltype volvo = new Biltype("Volvo");
        volvo.setType(new String[]{"V30", "V40", "V50"});
        Biltype bmw = new Biltype("BMW");
        bmw.setType(new String[]{"X3", "X2", "X1"});
        Biltype vw = new Biltype("VW");
        vw.setType(new String[]{"Golf", "Caravelle", "Passat"});
        bilListe.add(volvo);
        bilListe.add(vw);
        bilListe.add(bmw);
    }

    public List<Biltype> hentBiler() {
        return bilListe;
    }

    public List<String> hentType(String merke) {
        for(Biltype b : bilListe) {
            if(b.getMerke().equals(merke)) {
                return b.getType();
            }
        }
        return null;
    }
}
