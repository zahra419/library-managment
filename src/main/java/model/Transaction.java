package model;

import java.sql.Date;

public class Transaction {
	private Integer id;
	private Date dateEmprunt;
	private Date dateRetour;
	private String status;
	private Integer utilisateurId;
	private Integer livreId;
	public Transaction() {
		
	}
	public Transaction(Integer id,Date dateEmprunt,Date dateRetour,String status,Integer utilisateurId,Integer livreId) {
		this.id=id;
		this.dateEmprunt=dateEmprunt;
		this.dateRetour=dateRetour;
		this.status=status;
		this.utilisateurId=utilisateurId;
		this.livreId=livreId;
	}
	public Transaction(Integer id,Date dateEmprunt,Date dateRetour,Integer utilisateurId,Integer livreId) {
		this.id=id;
		this.dateEmprunt=dateEmprunt;
		this.dateRetour=dateRetour;
		this.utilisateurId=utilisateurId;
		this.livreId=livreId;
	}
	public Transaction(Date dateEmprunt,Date dateRetour,Integer utilisateurId,Integer livreId) {
		this.dateEmprunt=dateEmprunt;
		this.dateRetour=dateRetour;
		this.utilisateurId=utilisateurId;
		this.livreId=livreId;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Date getDateEmprunt() {
		return this.dateEmprunt;
	}
	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt=dateEmprunt;
	}
	public Date getDateRetour() {
		return this.dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour=dateRetour;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public void setUtilisateurId(Integer utilisateurId) {
		this.utilisateurId=utilisateurId;
	}
	public Integer getUtilisateurId() {
		return this.utilisateurId;
	}
	public Integer getLivreId() {
		return this.livreId;
	}
	public Integer setLivreId(Integer livreId) {
		return this.livreId=livreId;
	}

}
