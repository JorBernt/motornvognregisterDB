package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BilRegisterController {


    @Autowired
    KundeRepository rep;
    @Autowired
    BilRepository bilRep;


    @PostMapping("/lagre")
    public void BilRegisterController(Kunde innKunde) {
        rep.lagreKunde(innKunde);
    }

    @PostMapping("/hentBiler")
    public List<Biltype> hentBiler() {
        return bilRep.hentBiler();
    }

    @GetMapping("/hentType")
    public List<String> hentType(String merke) {
        return bilRep.hentType(merke);
    }

    @GetMapping("/hentAlle")
    public List<Kunde> hentAlle() {
        return rep.hentAlleKunde();
    }

    @GetMapping("/slettAlle")
    public void slettAlle() {
        rep.slettAlleKunder();
    }

    @GetMapping("/hentEn")
    public Kunde hentEn(int id) {
        return rep.hentEnKunde(id);
    }

    @PostMapping("/endreKunde")
    public void endreKunde(Kunde kunde) {
        rep.endreKunde(kunde);
    }

    @GetMapping("slettLinje")
    public void slettLinje(int id) {
        rep.slettKunde(id);
    }
}
