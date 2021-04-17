package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BilRegisterController {


    @Autowired
    private KundeRepository rep;
    @Autowired
    private BilRepository bilRep;
    @Autowired
    private HttpSession session;

    Logger logger = LoggerFactory.getLogger(BilRegisterController.class);


    @PostMapping("/lagre")
    public void lagreKunde(Kunde innKunde, HttpServletResponse response) throws IOException {
        if(!Validering.validerKunde(innKunde)) {
            logger.error("Valideringsfeil");
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i inputvalidering");
        }
        else {
            if(!rep.lagreKunde(innKunde)) {
                logger.error("Feil i DB");
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
            }
        }
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
    public List<Kunde> hentAlle(HttpServletResponse response) throws IOException {
        List<Kunde> kunder;
        if(session.getAttribute("Innlogget") != null) {
            kunder = rep.hentAlleKunde();
            if(kunder == null) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
                logger.error("Feil i uthenting av kunder fra databasen");
            }
            return kunder;
        }
        response.sendError(HttpStatus.NOT_FOUND.value());
        return null;
    }

    @GetMapping("/slettAlle")
    public void slettAlle(HttpServletResponse response) throws IOException {
        if(!rep.slettAlleKunder()) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            logger.error("Feil i sletting i databasen");
        }
    }

    @GetMapping("/hentEn")
    public Kunde hentEn(int id, HttpServletResponse response) throws IOException {
        Kunde kunde = rep.hentEnKunde(id);
        if(kunde == null) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igen senere");
            logger.error("Finner ikke id i databasen");
            return null;
        }
        return kunde;
    }

    @PostMapping("/endreKunde")
    public void endreKunde(Kunde kunde, HttpServletResponse response) throws IOException {
        if(!Validering.validerKunde(kunde)) {
            logger.error("Valideringsfeil");
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i inputvalidering");
        }
        else {
            if(!rep.endreKunde(kunde)) {
                logger.error("Feil i DB");
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
            }
        }
    }

    @GetMapping("/slettLinje")
    public void slettLinje(int id, HttpServletResponse response) throws IOException {
        if(!rep.slettKunde(id)) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - Prøv igjen senere");
            logger.error("Feil i databasen, kunne ikke slette linje");
        }
    }

    @GetMapping("/logginn")
    public boolean loggInn(Kunde kunde) {
        if(rep.sjekkNavnOgPassord(kunde)) {
            session.setAttribute("Innlogget", kunde);
            return true;
        }
        return false;
    }

    @GetMapping("/loggut")
    public void loggUt() {
        session.removeAttribute("Innlogget");
    }
}
