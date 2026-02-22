package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Categorie;
import test.Database;

public class CategorieDao {
	
	 public List<Categorie> getAllLivres() {
	        List<Categorie> categories = new ArrayList<>();
	        String sql = "SELECT id, nomCategorie FROM categorie";

	        try (Connection conn = Database.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                Categorie categorie = new Categorie(
	                    rs.getInt("id"),
	                    rs.getString("nomCategorie")
	                   );
	                categories.add(categorie);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return categories;
	    }
	 public void insert(Categorie categorie) throws Exception {
	        String sql = "INSERT INTO categorie (nomCategorie) VALUES (?)";

	        try (Connection conn = Database.getConnection();
	             PreparedStatement pst = conn.prepareStatement(sql)) {

	            pst.setString(1, categorie.getNomCategorie());
	            pst.executeUpdate();
	        }
	    }

	    public void delete(int id) throws Exception {
	        String sql = "DELETE FROM categorie WHERE id=?";

	        try (Connection conn = Database.getConnection();
	             PreparedStatement pst = conn.prepareStatement(sql)) {

	            pst.setInt(1, id);
	            pst.executeUpdate();
	        }
	    }

}
