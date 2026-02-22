CREATE DATABASE managment;
USE managment;

CREATE TABLE categorie(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nomCategorie VARCHAR(50)
);

CREATE TABLE utilisateur(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    cin VARCHAR(10),
    telephone VARCHAR(10)
);

CREATE TABLE auteur(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    biography TEXT
);

CREATE TABLE livre(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(200),
    edition VARCHAR(10),
    quantite Integer,
    description TEXT,
    annePublication DATE
);

CREATE TABLE auteur_livre (
    auteur_id INTEGER,
    livre_id INTEGER,
    PRIMARY KEY (auteur_id, livre_id),
    FOREIGN KEY (auteur_id) REFERENCES auteur(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (livre_id) REFERENCES livre(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE livre_categorie (
    livre_id INTEGER,
    categorie_id INTEGER,
    PRIMARY KEY (livre_id, categorie_id),
    FOREIGN KEY (livre_id) REFERENCES livre(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (categorie_id) REFERENCES categorie(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE transaction (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    dateEmprunt DATE,
    dateRetour DATE,
    status VARCHAR(20),
    utilisateur_id INTEGER,
    livre_id INTEGER,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (livre_id) REFERENCES livre(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
select version();
INSERT INTO categorie (nomCategorie) VALUES
('Roman'),
('Science'),
('Informatique'),
('Histoire'),
('Philosophie');

INSERT INTO utilisateur (nom, prenom, cin, telephone) VALUES
('Ahmed', 'Ali', 'AA123456', '0612345678'),
('Sara', 'Ben', 'BB234567', '0623456789'),
('Youssef', 'Karim', 'CC345678', '0634567890'),
('Lina', 'Amrani', 'DD456789', '0645678901');

INSERT INTO auteur (nom, prenom, biography) VALUES
('Orwell', 'George', 'British writer, author of 1984 and Animal Farm'),
('Rowling', 'J.K.', 'British author of Harry Potter series'),
('Hugo', 'Victor', 'French writer of Les Miserables'),
('Tolkien', 'J.R.R.', 'Author of The Lord of the Rings');

INSERT INTO livre (titre, edition, disponible, description, annePublication)
VALUES
('1984', '1', 4, 'Dystopian novel', '1949-06-08'),
('Harry Potter', '1', 5, 'Fantasy novel', '1997-06-26'),
('Les Miserables', '2', 10, 'Historical novel', '1862-01-01'),
('The Hobbit', '1', 12, 'Fantasy adventure', '1937-09-21');

INSERT INTO auteur_livre (auteur_id, livre_id) VALUES
(1, 1), -- Orwell → 1984
(2, 2), -- Rowling → Harry Potter
(3, 3), -- Hugo → Les Miserables
(4, 4); -- Tolkien → The Hobbit

INSERT INTO livre_categorie (livre_id, categorie_id) VALUES
(1, 1), -- 1984 → Roman
(2, 1), -- Harry Potter → Roman
(2, 3), -- Harry Potter → Informatique (example multi category)
(3, 4), -- Les Miserables → Histoire
(4, 1); -- Hobbit → Roman

INSERT INTO transaction (dateEmprunt, dateRetour, status, utilisateur_id, livre_id) VALUES
('2026-02-01', NULL, 'BORROWED', 1, 1),
('2026-02-05', '2026-02-15', 'RETURNED', 2, 2),
('2026-02-10', NULL, 'BORROWED', 3, 3),
('2026-02-12', '2026-02-18', 'RETURNED', 4, 4);

select u.nom as nomUtilisateur,u.prenom as prenomUtilisateur,l.titre as titre,
				l.edition as edition ,
				a.nom as nomAuteur,a.prenom as prenomAuteur,t.dateEmprunt as dateEmprunt,t.dateRetour as dateRetour
				,t.status as status from transaction t
				join utilisateur u on u.id=t.utilisateur_id
                join livre l on l.id=t.livre_id
				join auteur_livre al on t.auteur_id=al.auteur_id
                join auteur a on al.auteur_id=a.id;
                
select u.nom as nomUtilisateur,u.prenom as prenomUtilisateur,l.titre as titre,
				l.edition as edition ,
                t.dateEmprunt as dateEmprunt,t.dateRetour as dateRetour
				,t.status as status from transaction t 
				join utilisateur u on u.id=t.utilisateur_id 
				join livre l  on t.livre_id=l.id;
alter table livre add column quantite integer;
select * from livre;