package service;

import java.util.List;

import dao.UtilisateurDao;
import model.Utilisateur;

public class UtilisateurService {
    private UtilisateurDao dao = new UtilisateurDao();
    
    public List<Utilisateur> getAllUtilisateurs() {
        return dao.getAllUtilisateurs();
    }
    public void addUtilisateur(Utilisateur u) throws Exception {
        if (u.getNom() == null || u.getNom().isEmpty()) {
            throw new Exception("Nom required");
        }
        dao.insert(u);
    }
    
    public void deleteUtilisateur(int id) throws Exception {
        dao.delete(id);
    }
}