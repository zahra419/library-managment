package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Auteur;
import database.Database;

public class AuteurDao {
	
	 public List<Auteur> getAllAuteur() {
	        List<Auteur> auteurs = new ArrayList<>();
	        String sql = "SELECT id, nom, prenom, biography FROM auteur";

	        try (Connection conn = Database.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                Auteur auteur = new Auteur(
	                    rs.getInt("id"),
	                    rs.getString("nom"),
	                    rs.getString("prenom"),
	                    rs.getString("biography")
	                );
	                auteurs.add(auteur);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return auteurs;
	    }
	 public void insert(Auteur auteur) throws Exception {
	        String sql = "INSERT INTO auteur (nom,prenom,biography) VALUES (?,?,?)";

	        try (Connection conn = Database.getConnection();
	             PreparedStatement pst = conn.prepareStatement(sql)) {

	            pst.setString(1, auteur.getNom());
	            pst.setString(2, auteur.getPrenom());
	            pst.setString(3, auteur.getBiography());
	            pst.executeUpdate();
	        }
	    }

	    public void delete(int id) throws Exception {
	        String sql = "DELETE FROM auteur WHERE id=?";

	        try (Connection conn = Database.getConnection();
	             PreparedStatement pst = conn.prepareStatement(sql)) {

	            pst.setInt(1, id);
	            pst.executeUpdate();
	        }
	    }

}
