package dto;

import java.sql.Date;

public class TransactionInfo {
	private Integer transactionId;
	private Integer utilisateurId;
	private String utilisateurNom;
	private String utilisateurPenom;
	private Date dateEmprunt;
	private Date dateRetour;
	private String status;
	private String edition;
	private String titre;
	

    public TransactionInfo() {
    }

 
    public TransactionInfo(Integer utilisateurId,
    		               Integer transactionId,
    		               String titre,
                          String utilisateurNom,
                          String utilisateurPenom,
                          Date dateEmprunt,
                          Date dateRetour,
                          String status,
                          String edition) {

        this.utilisateurId = utilisateurId;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPenom = utilisateurPenom;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.status = status;
        this.edition = edition;
        this.transactionId=transactionId;
        this.titre=titre;
    }
    public TransactionInfo(
           String utilisateurNom,
           String utilisateurPenom,
           String titre,
           Date dateEmprunt,
           Date dateRetour,
           String status,
           String edition) {
this.utilisateurNom = utilisateurNom;
this.utilisateurPenom = utilisateurPenom;
this.dateEmprunt = dateEmprunt;
this.dateRetour = dateRetour;
this.status = status;
this.edition = edition;
this.titre=titre;
}


    // ========= Getters and Setters =========

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
    
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public String getUtilisateurPenom() {
        return utilisateurPenom;
    }

    public void setUtilisateurPenom(String utilisateurPenom) {
        this.utilisateurPenom = utilisateurPenom;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
