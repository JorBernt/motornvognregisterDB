CREATE TABLE Kunde
(
  id int IDENTITY NOT NULL,
  personNr VARCHAR(12) NOT NULL,
  navn VARCHAR(255) NOT NULL,
  adresse VARCHAR(255) NOT NULL,
  kjennetegn VARCHAR(10) NOT NULL,
  bilmerke VARCHAR(100) NOT NULL,
  biltype VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);