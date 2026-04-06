package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Auteur;
import model.Categorie;
import model.Livre;
import database.Database;

public class LivreDao {

    // Get all books with authors and categories
	public List<Livre> getAllLivres() {
	    List<Livre> livres = new ArrayList<>();
	    String sql = "SELECT l.id, l.titre, l.edition, l.quantite, l.description, l.annePublication, " +
	                 "a.id AS auteurId, a.nom AS auteurNom, a.prenom AS auteurPrenom, " +
	                 "c.id AS categorieId, c.nomCategorie " +
	                 "FROM livre l " +
	                 "LEFT JOIN auteur_livre al ON l.id = al.livre_id " +
	                 "LEFT JOIN auteur a ON al.auteur_id = a.id " +
	                 "LEFT JOIN livre_categorie lc ON l.id = lc.livre_id " +
	                 "LEFT JOIN categorie c ON lc.categorie_id = c.id " +
	                 "ORDER BY l.id";

	    try (Connection conn = Database.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        Map<Integer, Livre> map = new HashMap<>();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            Livre livre = map.get(id);

	            if (livre == null) {
	                livre = new Livre(
	                        id,
	                        rs.getString("titre"),
	                        rs.getString("edition"),
	                        rs.getInt("quantite"),
	                        rs.getString("description"),
	                        rs.getDate("annePublication"),
	                        new ArrayList<>(),  // authors
	                        new ArrayList<>()   // categories
	                );
	                map.put(id, livre);
	            }

	            // Add author if exists
	            int auteurId = rs.getInt("auteurId");
	            if (!rs.wasNull()) {
	                Auteur auteur = new Auteur(auteurId, rs.getString("auteurNom"), rs.getString("auteurPrenom"));
	                if (!livre.getAuteurs().contains(auteur)) {
	                    livre.getAuteurs().add(auteur);
	                }
	            }

	            // Add category if exists
	            int categorieId = rs.getInt("categorieId");
	            if (!rs.wasNull()) {
	                Categorie categorie = new Categorie(categorieId, rs.getString("nomCategorie"));
	                if (!livre.getCategories().contains(categorie)) {
	                    livre.getCategories().add(categorie);
	                }
	            }
	        }

	        livres.addAll(map.values());

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return livres;
	}
    // Insert book (without authors/categories)
   /* public void insert(String titre, String edition, Integer quantite, String description, Date annePublication) throws SQLException {
        String sql = "INSERT INTO livre(titre, edition, quantite, description, annePublication) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, titre);
            pst.setString(2, edition);
            pst.setInt(3, quantite);
            pst.setString(4, description);
            pst.setDate(5, annePublication);
           
            pst.executeUpdate();
        }
    }*/
	public void insert(Livre livre, Auteur auteur, Categorie categorie) throws SQLException {
	    String sqlLivre = "INSERT INTO livre(titre, edition, quantite, description, annePublication) VALUES (?, ?, ?, ?, ?)";
	    String sqlAuteur = "INSERT INTO auteur(nom, prenom) VALUES (?, ?)";
	    String sqlCategorie = "INSERT INTO categorie(nomCategorie) VALUES (?)";
	    String sqlAuteurLivre = "INSERT INTO auteur_livre(auteur_id, livre_id) VALUES (?, ?)";
	    String sqlLivreCategorie = "INSERT INTO livre_categorie(livre_id, categorie_id) VALUES (?, ?)";

	    try (Connection conn = Database.getConnection()) {
	        conn.setAutoCommit(false); // Start transaction

	        try (
	            PreparedStatement pstLivre = conn.prepareStatement(sqlLivre, Statement.RETURN_GENERATED_KEYS);
	            PreparedStatement pstAuteur = conn.prepareStatement(sqlAuteur, Statement.RETURN_GENERATED_KEYS);
	            PreparedStatement pstCategorie = conn.prepareStatement(sqlCategorie, Statement.RETURN_GENERATED_KEYS);
	            PreparedStatement pstAuteurLivre = conn.prepareStatement(sqlAuteurLivre);
	            PreparedStatement pstLivreCategorie = conn.prepareStatement(sqlLivreCategorie)
	        ) {
	            // Insert Livre
	            pstLivre.setString(1, livre.getTitre());
	            pstLivre.setString(2, livre.getEdition());
	            pstLivre.setInt(3, livre.getQuantite());
	            pstLivre.setString(4, livre.getDescription());
	            pstLivre.setDate(5, livre.getAnnePublication());
	            pstLivre.executeUpdate();

	            ResultSet rsLivre = pstLivre.getGeneratedKeys();
	            if (!rsLivre.next()) throw new SQLException("Failed to retrieve livre ID.");
	            int livreId = rsLivre.getInt(1);

	            // Insert Auteur
	            pstAuteur.setString(1, auteur.getNom());
	            pstAuteur.setString(2, auteur.getPrenom());
	            pstAuteur.executeUpdate();

	            ResultSet rsAuteur = pstAuteur.getGeneratedKeys();
	            if (!rsAuteur.next()) throw new SQLException("Failed to retrieve auteur ID.");
	            int auteurId = rsAuteur.getInt(1);

	            // Insert Categorie
	            pstCategorie.setString(1, categorie.getNomCategorie());
	            pstCategorie.executeUpdate();

	            ResultSet rsCategorie = pstCategorie.getGeneratedKeys();
	            if (!rsCategorie.next()) throw new SQLException("Failed to retrieve categorie ID.");
	            int categorieId = rsCategorie.getInt(1);

	            // Link Livre and Auteur
	            pstAuteurLivre.setInt(1, auteurId);
	            pstAuteurLivre.setInt(2, livreId);
	            pstAuteurLivre.executeUpdate();

	            // Link Livre and Categorie
	            pstLivreCategorie.setInt(1, livreId);
	            pstLivreCategorie.setInt(2, categorieId);
	            pstLivreCategorie.executeUpdate();

	            conn.commit();

	        } catch (Exception e) {
	            conn.rollback();
	            throw e;
	        } finally {
	            conn.setAutoCommit(true);
	        }
	    }
	}

    // Update quantity
    public void updateQuantite(Integer value, int id) throws SQLException {
        String sql = "UPDATE livre SET quantite=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, value);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    // Delete book
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM livre WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

}