
package service;

import java.sql.Date;
import java.util.List;

import dao.TransactionDao;
import dto.TransactionInfo;
import model.Transaction;

public class TransactionService {

    private TransactionDao dao = new TransactionDao();

  
    public List<TransactionInfo> getAllTransactions() throws Exception {
        return dao.getFullInfoTransaction();
    }

    public List<TransactionInfo> getTransactionsByStatus(String status) throws Exception {
        if (status == null || status.isEmpty()) {
            throw new Exception("Status invalide");
        }
        return dao.getFullInfoTransactionByStatus(status);
    }

    public Integer getTotalByStatus(String status) throws Exception {
        if (status == null || status.isEmpty()) {
            throw new Exception("Status invalide");
        }
        return dao.totalByStatus(status);
    }

  
    public void addTransaction(Date dateEmprunt, Date dateRetour,
                               int utilisateurId, int livreId) throws Exception {

        if (dateEmprunt == null || dateRetour == null) {
            throw new Exception("Dates invalides");
        }

        if (dateRetour.before(dateEmprunt)) {
            throw new Exception("Date retour doit être après date emprunt");
        }

        Transaction transaction = new Transaction(dateEmprunt, dateRetour, utilisateurId, livreId);
        dao.insert(transaction);
    }

    public void deleteTransaction(int id) throws Exception {
        dao.delete(id);
    }

    public void updateStatus(int id, String status) throws Exception {
        if (status == null || status.isEmpty()) {
            throw new Exception("Status invalide");
        }
        dao.updateStatus(status, id);
    }

    public void updateDateRetour(int id, Date dateRetour) throws Exception {
        if (dateRetour == null) {
            throw new Exception("Date retour invalide");
        }
        dao.updateDateRetour(dateRetour, id);
    }
}