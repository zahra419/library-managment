package model;


public class Utilisateur {
	private Integer id;
	private String nom;
	private String prenom;
	private String cin;
	private String telephone;
	
	public Utilisateur() {}
	public Utilisateur(Integer id,String nom,String prenom,String cin,String telephone) {
		this.id=id;
		this.nom=nom;
		this.cin=cin;
		this.prenom=prenom;
		this.telephone=telephone;
	}
	public Utilisateur(String nom,String prenom,String cin,String telephone) {
		this.nom=nom;
		this.cin=cin;
		this.prenom=prenom;
		this.telephone=telephone;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setNom(String nom){
		this.nom=nom;
	}
	public String getNom() {
		return this.nom;
	}
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public void setCin(String cin) {
		this.cin=cin;
	}
	public String getCin() {
		return this.cin;
	}
	public void setTelephone(String telephone) {
		this.telephone=telephone;
	}
	public String getTelephone() {
		return this.telephone;
	}
}
