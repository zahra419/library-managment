package model;

public class Auteur {
	private Integer id;
	private String nom;
	private String prenom;
	private String biography;
	public Auteur() {
		
	}
	public Auteur(Integer id,String nom,String prenom,String biography) {
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.biography=biography;
	}
	public Auteur(String nom,String prenom,String biography) {
		this.nom=nom;
		this.prenom=prenom;
		this.biography=biography;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom=nom;
	}
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public void setBiography(String biography) {
		this.biography=biography;
	}
	public String getBiography() {
		return this.biography;
	}

}
