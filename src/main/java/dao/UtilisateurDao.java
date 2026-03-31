package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Utilisateur;
import test.Database;

public class UtilisateurDao {
	
	 public List<Utilisateur> getAllUtilisateurs() {
	        List<Utilisateur> utilisateurs = new ArrayList<>();
	        String sql = "SELECT id, nom, prenom, cin, telephone FROM utilisateur";

	        try (Connection conn = Database.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                Utilisateur utilisateur = new Utilisateur(
	                    rs.getInt("id"),
	                    rs.getString("nom"),
	                    rs.getString("prenom"),
	                    rs.getString("cin"),
	                    rs.getString("telephone")
	                );
	                utilisateurs.add(utilisateur);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return utilisateurs;
	    }
	 
	 public void insert(Utilisateur utilisateur) throws Exception {
	        String sql = "INSERT INTO utilisateur (nom,prenom,cin,telephone) VALUES (?,?,?,?)";

	        try (Connection conn = Database.getConnection();
	             PreparedStatement pst = conn.prepareStatement(sql)) {

	            pst.setString(1, utilisateur.getNom());
	            pst.setString(2, utilisateur.getPrenom());
	            pst.setString(3, utilisateur.getCin());
	            pst.setString(4, utilisateur.getTelephone());
	            pst.executeUpdate();
	        }
	    }

	    public void delete(int id) throws Exception {
	        String sql = "DELETE FROM utilisateur WHERE id=?";

	        try (Connection conn = Database.getConnection();
	             PreparedStatement pst = conn.prepareStatement(sql)) {

	            pst.setInt(1, id);
	            pst.executeUpdate();
	        }
	    }


}
