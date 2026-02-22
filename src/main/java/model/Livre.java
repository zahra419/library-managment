package model;

import java.sql.Date;

public class Livre {
	private Integer id;
	private String titre;
	private String edition;
	private Integer quantite;
	private String description;
	private Date annePublication;
	public Livre() {
		
	}
	public Livre(Integer id,String titre,String edition,Integer quantite,String description,Date annePublication) {
		this.id=id;
		this.titre=titre;
		this.edition=edition;
		this.quantite=quantite;
		this.description=description;
		this.annePublication=annePublication;
	}
	public Livre(String titre,String edition,Integer quantite,String description,Date annePublication) {
		this.titre=titre;
		this.edition=edition;
		this.quantite=quantite;
		this.description=description;
		this.annePublication=annePublication;
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

}
