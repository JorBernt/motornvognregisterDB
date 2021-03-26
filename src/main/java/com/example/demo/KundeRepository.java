package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {

    @Autowired
    private JdbcTemplate db;

    public void lagreKunde(Kunde kunde) {
        String sql = "INSERT INTO Kunde (personNr, navn, adresse, kjennetegn, bilmerke, biltype) VALUES(?,?,?,?,?,?)";
        db.update(sql, kunde.getPersonNr(),kunde.getNavn(), kunde.getAdresse(),
                kunde.getKjennetegn(), kunde.getBilmerke(), kunde.getBiltype());
    }

    public List<Kunde> hentAlleKunde() {
        String sql = "SELECT * FROM Kunde;";
        return db.query(sql, new BeanPropertyRowMapper(Kunde.class));
    }

    public void slettAlleKunder() {
        String sql = "DELETE FROM Kunde;";
        db.update(sql);
    }
}
