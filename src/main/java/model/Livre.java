package model;

import java.sql.Date;
import java.util.List;

public class Livre {
	private Integer id;
	private String titre;
	private String edition;
	private Integer quantite;
	private String description;
	private Date annePublication;
	private List<Auteur> auteurs;     
    private List<Categorie> categories; 

	
	public Livre(Integer id,String titre,String edition,Integer quantite,String description,Date annePublication) {
		this.id=id;
		this.titre=titre;
		this.edition=edition;
		this.quantite=quantite;
		this.description=description;
		this.annePublication=annePublication;
	}
	public Livre(int id,String titre,String edition,Integer quantite,String description,Date annePublication,List<Auteur> auteurs,List<Categorie> categories) {
		this.titre=titre;
		this.edition=edition;
		this.quantite=quantite;
		this.description=description;
		this.annePublication=annePublication;
		this.auteurs=auteurs;
		this.categories=categories;
	}
	public Livre() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getTitre() {
	    return this.titre;
	}

	public void setTitre(String titre) {
	    this.titre = titre;
	}

	public String getEdition() {
	    return edition;
	}

	public void setEdition(String edition) {
	    this.edition = edition;
	}

	public Integer getQuantite() {   
	    return quantite;
	}

	public void setQuantite(Integer quantite) {
	    this.quantite = quantite;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public Date getAnnePublication() {
	    return annePublication;
	}

	public void setAnnePublication(Date annePublication) {
	    this.annePublication = annePublication;
	} 
	 public List<Auteur> getAuteurs() { return auteurs; }
	 public void setAuteurs(List<Auteur> auteurs) {
		 this.auteurs = auteurs; 
     }

	public List<Categorie> getCategories() { return categories; }
	public void setCategories(List<Categorie> categories) { 
		this.categories = categories; 
	}

}
