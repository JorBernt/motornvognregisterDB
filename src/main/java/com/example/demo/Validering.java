package com.example.demo;

public class Validering {

    public static boolean validerKunde(Kunde kunde) {
        return  navn(kunde.getNavn()) &&
                adresse(kunde.getAdresse()) &&
                kjennetegn(kunde.getKjennetegn()) &&
                pnr(kunde.getPersonNr());
    }

    public static boolean navn(String input) {
        String regex = "[a-zæøåA-ZÆØÅ' .\\-]{2,50}";
        return input.matches(regex);
    }

    public static boolean adresse(String input) {
        String regex = "[\\da-zæøåA-ZÆØÅ' .\\-]{2,50}";
        return input.matches(regex);
    }

    public static boolean kjennetegn(String input) {
        String regex = "[\\da-zæøåA-ZÆØÅ' .\\-]{2,8}";
        return input.matches(regex);
    }

    public static boolean pnr(String input) {
        String regex = "[\\d]{12}";
        return input.matches(regex);
    }

}
