package model;

public class Categorie {
	private Integer id;
	private String nomCategorie;
	public Categorie() {
		
	}
	public Categorie(Integer id,String nomCategorie) {
		this.id=id;
		this.nomCategorie=nomCategorie;
	}
	public Categorie(String nomCategorie) {
		this.nomCategorie=nomCategorie;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie=nomCategorie;
	}
	public String getNomCategorie() {
		return this.nomCategorie;
	}

}
