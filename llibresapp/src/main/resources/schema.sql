CREATE TABLE IF NOT EXISTS usuari (
    usuari VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS llibre (
    id_Llibre INT NOT NULL AUTO_INCREMENT,
    titol VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editorial VARCHAR(255),
    datapublicacio DATE,
    tematica VARCHAR(100),
    isbn VARCHAR(30),
    PRIMARY KEY (idLlibre)
);