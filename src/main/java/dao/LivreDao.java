package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Livre;
import test.Database;


public class LivreDao {
	
	 public List<Livre> getAllLivres() {
	        List<Livre> livres = new ArrayList<>();
	        String sql = "SELECT id, titre, edition, quantite, description, annePublication FROM livre";

	        try (Connection conn = Database.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                Livre livre = new Livre(
	                    rs.getInt("id"),
	                    rs.getString("titre"),
	                    rs.getString("edition"),
	                    rs.getInt("quantite"),
	                    rs.getString("description"),
	                    rs.getDate("annePublication")
	                );
	                livres.add(livre);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return livres;
	    }
	   public void insert(String titre,String edition,Integer quantite,String description ,Date annePublication) throws SQLException {
		   String sql="insert into livre(titre,edition,quantite,description,annePublication)values(?,?,?,?,?)";
		   try(Connection conn=Database.getConnection();
			   PreparedStatement pst=conn.prepareStatement(sql);
				   
				   ){
			   pst.setString(1, titre);
			   pst.setString(2, edition);
			   pst.setInt(3, quantite);
			   pst.setString(4, description);
			   pst.setDate(5, annePublication);
			   pst.executeUpdate();
			   
		   }
	   }
	
       public void updateQuantite(Integer value,int id) throws SQLException {
    	   String sql="update livre set quantite=? where id=?";
    	   try (
    		   Connection conn=Database.getConnection();
    		   PreparedStatement pst=conn.prepareStatement(sql);
    		   
    	   ){
    		   pst.setInt(1,value);
    		   pst.setInt(2, id);
    		   pst.executeUpdate();
    	   }
       }
       public void delete(Integer id) throws SQLException {
    	   String sql="delete from livre where id=?";
    	   try(Connection conn=Database.getConnection();
    		   PreparedStatement  pst=conn.prepareStatement(sql);       
    			   ){
    		       pst.setInt(1, id);
    		       pst.executeUpdate();
    	   }
       }
       
       public List<Livre> getBooksByAuthor(String nomAuteur,String prenomAuteur) throws SQLException{
    	   List<Livre> livres=new ArrayList<>();
    	   String sql="select l.id,l.titre,l.edition,l.quantite,l.description,l.annePublication from livre l join auteur_livre la "
    	   		+ " on l.id = la.livre_id where la.auteur_id  = (select id from auteur where nom=? "
    	   		+ " AND prenom= ? );";
    	   try(Connection conn=Database.getConnection();
    		   PreparedStatement pst=conn.prepareStatement(sql);
    		){
    		   pst.setString(1, nomAuteur);
    		   pst.setString(2, prenomAuteur);
    		   ResultSet rs=pst.executeQuery();
    		   while (rs.next()) {
    			   Livre livre = new Livre(
   	                    rs.getInt("id"),
   	                    rs.getString("titre"),
   	                    rs.getString("edition"),
   	                    rs.getInt("quantite"),
   	                    rs.getString("description"),
   	                    rs.getDate("annePublication")
   	                );
   	                livres.add(livre);
    			   
    		   }
    		   
    	   }
    	   
    	   return livres;
       }
       
      public List<Livre> getBooksbyCategorie(String nomCategorie)throws SQLException{
    	  
    	   List<Livre> livres=new ArrayList<>();
    	   String sql="select l.id,l.titre,l.edition,l.quantite,l.description,l.annePublication from livre l join livre_categorie c "
    	   		+ "on l.id=c.livre_id where c.categorie_id in (select id from categorie where nomCategorie =?)";
    	   try(Connection conn=Database.getConnection();
    		   PreparedStatement pst=conn.prepareStatement(sql);
    		){
    		   pst.setString(1, nomCategorie);
    		   ResultSet rs=pst.executeQuery();
    		   while (rs.next()) {
    			   Livre livre = new Livre(
   	                    rs.getInt("id"),
   	                    rs.getString("titre"),
   	                    rs.getString("edition"),
   	                    rs.getInt("quantite"),
   	                    rs.getString("description"),
   	                    rs.getDate("annePublication")
   	                );
   	                livres.add(livre);
    		   }
    		   
    	   }
    	   
    	   return livres;
      }
}
