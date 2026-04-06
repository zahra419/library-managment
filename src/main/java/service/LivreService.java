package service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import dao.LivreDao;
import model.Auteur;
import model.Categorie;
import model.Livre;

public class LivreService {

    private LivreDao dao = new LivreDao();

    public List<Livre> getAllLivres() {
        return dao.getAllLivres();
    }

    public void addLivre(String titre, String edition, Integer quantite,
            Date annePublication, String nomAuteur, String prenomAuteur, String nomCategorie) throws Exception {

if (quantite < 0) {
throw new Exception("Quantité ne peut pas être négative");
}

// Create objects
Livre livre = new Livre();
livre.setTitre(titre);
livre.setEdition(edition);
livre.setQuantite(quantite);
livre.setDescription(""); // you can pass description as parameter if needed
livre.setAnnePublication(annePublication);

Auteur auteur = new Auteur(nomAuteur, prenomAuteur);
Categorie categorie = new Categorie(nomCategorie);

// Insert into database
dao.insert(livre, auteur, categorie);
}

    public void deleteLivre(int id) throws Exception {
       
        dao.delete(id);
    }

    public void updateQuantite(int id, int quantite) throws Exception {
        if (quantite < 0) {
            throw new Exception("Quantité invalide");
        }

        dao.updateQuantite(quantite, id);
    }

    public class TitreFilter implements Filter {
        @Override
        public boolean matches(Livre livre, String value) {
            if (value == null || value.isEmpty()) return true;
            return livre.getTitre().toLowerCase().contains(value.toLowerCase());
        }
    }
    public class AuteurFilter implements Filter {
        @Override
        public boolean matches(Livre livre, String value) {
            if (value == null || value.isEmpty()) return true;
            return livre.getAuteurs().stream()
                    .anyMatch(a -> (a.getNom() + " " + a.getPrenom())
                                     .toLowerCase().contains(value.toLowerCase()));
        }
    }
    public class CategorieFilter implements Filter {
        @Override
        public boolean matches(Livre livre, String value) {
            if (value == null || value.isEmpty()) return true;
            return livre.getCategories().stream()
                    .anyMatch(c -> c.getNomCategorie().equalsIgnoreCase(value));
        }
    }
    public List<Livre> filterBooks(String titre, String auteur, String categorie) {
        List<Livre> allBooks = dao.getAllLivres();
        
        Filter titreFilter = new TitreFilter();
        Filter auteurFilter = new AuteurFilter();
        Filter categorieFilter = new CategorieFilter();
        
        return allBooks.stream()
                .filter(l -> titreFilter.matches(l, titre))
                .filter(l -> auteurFilter.matches(l, auteur))
                .filter(l -> categorieFilter.matches(l, categorie))
                .collect(Collectors.toList());
    }
}