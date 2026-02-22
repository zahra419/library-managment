package dao;
import dto.TransactionInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import test.Database;

public class TransactionDao {
	
	public void insert(Transaction transaction) throws SQLException {
		
		String sql="insert into transaction (dateEmprunt,dateRetour,utilisateur_id,livre_id,status) values(?,?,?,?,'borrowed') ";
		try(Connection conn=Database.getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
				){
			pst.setDate(1, transaction.getDateEmprunt());
			pst.setDate(2, transaction.getDateRetour());
			pst.setInt(3, transaction.getUtilisateurId());
			pst.setInt(4, transaction.getLivreId());
			pst.executeUpdate();
			
		}
	}
	
	public void updateStatus(String status,int id) throws SQLException {
		String sql="update transaction set status=? where id=? ";
		try(
		    Connection conn=Database.getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
				){
			pst.setInt(2, id);
			pst.setString(1, status);
			pst.executeUpdate();
		}
		
	}
	public void updateDateRetour(Date dateRetour,Integer id) throws SQLException {
		String sql="update transaction set dateRetour=? where id=? ";
		try(
		    Connection conn=Database.getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
				){
			pst.setDate(1, dateRetour);
			pst.setInt(2, id);
			pst.executeUpdate();
		}
		
	}
	public Integer totalByStatus(String status) throws SQLException {
		Integer total=0;
		String sql="select count(*) as total from transaction where status=? ";
		
		try(Connection conn=Database.getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
				){
			pst.setString(1, status);
			ResultSet rs=pst.executeQuery();
			total=rs.getInt("total");
			
		}
		return total;
		
	}

	public void delete(Integer id) throws SQLException {
		String sql="delete from transaction where id=?";
		try(Connection conn=Database.getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
				){
			 pst.setInt(1,id);
			 pst.executeUpdate();
		}
		
	}
	public List <TransactionInfo> getFullInfoTransaction()throws SQLException {
		List<TransactionInfo> transactionsInfo=new ArrayList<>();
		String sql="select u.nom as utilisateurNom,u.prenom as utilisateurPrenom,l.titre as titre,"
				+ "	l.edition as edition ,"
				+ " t.dateEmprunt as dateEmprunt,t.dateRetour as dateRetour, "
				+ "	t.status as status from transaction t  "
				+ "	join utilisateur u on u.id=t.utilisateur_id  "
				+ "	join livre l  on t.livre_id=l.id ";
		try(Connection conn=Database.getConnection();
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
				){
			while(rs.next()) {
				TransactionInfo transactionInfo=new TransactionInfo(
						rs.getString("utilisateurNom"),
						rs.getString("utilisateurPrenom"),
						rs.getString("titre"),
						rs.getDate("dateEmprunt"),
						rs.getDate("dateRetour"),
						rs.getString("status"),
						rs.getString("edition")
						);
				transactionsInfo.add(transactionInfo);
			}
			
		}
		return transactionsInfo;
	}
	
	public List <TransactionInfo> getFullInfoTransactionByStatus(String status)throws SQLException {
		List<TransactionInfo> transactionsInfo=new ArrayList<>();
		String sql="select u.nom as utilisateurNom,u.prenom as utilisateurPrenom,l.titre as titre,"
				+ "l.edition as edition ,"
				+ "t.dateEmprunt as dateEmprunt,t.dateRetour as dateRetour "
				+ ",t.status as status from transaction t "
				+ "join utilisateur u on u.id=t.utilisateur_id "
				+ "join livre l  on t.livre_id=l.id where status= ? " ;
		try(Connection conn=Database.getConnection();
		    PreparedStatement pst=conn.prepareStatement(sql);
			
				){
			pst.setString(1, status);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				TransactionInfo transactionInfo=new TransactionInfo(
						rs.getString("utilisateurNom"),
						rs.getString("utilisateurPrenom"),
						rs.getString("titre"),
						rs.getDate("dateEmprunt"),
						rs.getDate("dateRetour"),
						rs.getString("status"),
						rs.getString("edition")
						);
				transactionsInfo.add(transactionInfo);
			}
			
		}
		return transactionsInfo;
	}
}
