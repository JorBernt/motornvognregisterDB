package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

@Repository
public class KundeRepository {

    @Autowired
    private JdbcTemplate db;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());


    public boolean lagreKunde(Kunde kunde)  {
        String pass = passwordEncoder.encode(kunde.getPassord());
        String sql = "INSERT INTO Kunde (personNr, navn, adresse, kjennetegn, bilmerke, biltype, passord) VALUES(?,?,?,?,?,?,?)";
        try {
            db.update(sql, kunde.getPersonNr(),kunde.getNavn(), kunde.getAdresse(),
                    kunde.getKjennetegn(), kunde.getBilmerke(), kunde.getBiltype(), pass);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<Kunde> hentAlleKunde() {
        String sql = "SELECT * FROM Kunde;";
        try {
            return db.query(sql, new BeanPropertyRowMapper(Kunde.class));
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean slettAlleKunder() {
        String sql = "DELETE FROM Kunde;";
        try {
            db.update(sql);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


    public Kunde hentEnKunde(int id) {
        String sql ="SELECT personNr, navn, adresse, kjennetegn, bilmerke, " +
                "biltype, passord from Kunde where id = "+id+";";
        try {
            return (Kunde)db.query(sql, new BeanPropertyRowMapper(Kunde.class)).get(0);
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean endreKunde(Kunde kunde) {
        String sql ="UPDATE Kunde SET personNr=?, navn=?, adresse=?, kjennetegn=?, bilmerke=?, biltype=?, passord=? where id =?";
        try {
            db.update(sql, kunde.getPersonNr(), kunde.getNavn(),
                    kunde.getAdresse(), kunde.getKjennetegn(),
                    kunde.getBilmerke(), kunde.getBiltype(),
                    passwordEncoder.encode(kunde.getPassord()), kunde.getId());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean slettKunde(int id) {
        String sql = "DELETE FROM Kunde where id =?";
        try {
            db.update(sql, id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean sjekkNavnOgPassord(Kunde kunde) {
        String sql = "Select passord from Kunde where navn = ?";
        String pass = db.queryForObject(sql, String.class, kunde.getNavn());
        return passwordEncoder.matches(kunde.getPassord(), pass);
    }
}
