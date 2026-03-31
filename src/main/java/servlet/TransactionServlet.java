/*package servlet;
import java.io.IOException;
import java.util.List;
import dao.TransactionDao;
import dto.TransactionInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Transaction;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
	private TransactionDao dao=new TransactionDao();
	
	private void listAllTransactions(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		try {
			List<TransactionInfo> transactions=dao.getFullInfoTransaction();
			request.setAttribute("listAllTransactions", transactions);
			request.getRequestDispatcher("test.jsp").forward(request,response);
		}catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	private void listAllTransactionByStatus(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String status=request.getParameter("status");
		try {
			List<TransactionInfo> transactions=dao.getFullInfoTransactionByStatus(status);
			request.setAttribute("listByStatus", transactions);
			request.getRequestDispatcher("test.jsp").forward(request,response);
		}catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	private void getTotalTransactionsByStatus(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String status=request.getParameter("status");
		try {
			Integer total=dao.totalByStatus(status);
			request.setAttribute("total", total);
			request.getRequestDispatcher("test.jsp").forward(request,response);
		}catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	private void deleteTransaction(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Integer id=Integer.getInteger(request.getParameter("id"));
		try {
			dao.delete(id);
			response.sendRedirect("/transaction?action=listAllTransactions");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	private void addTransaction(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		java.sql.Date dateEmprunt=java.sql.Date.valueOf(request.getParameter("dateEmprunt"));
		java.sql.Date dateRetour=java.sql.Date.valueOf(request.getParameter("dateRetour"));
		Integer utilisateurId=Integer.getInteger(request.getParameter("utilisateurId"));
		Integer livreId=Integer.getInteger(request.getParameter("livreId"));
		Transaction transaction=new Transaction(dateEmprunt,dateRetour,utilisateurId,livreId);
		try {
			dao.insert(transaction);
			response.sendRedirect("/transaction?action=listAllTransactions");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	private void updateStatus(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String status=request.getParameter("status");
		Integer id=Integer.getInteger(request.getParameter("id"));
		try {
			dao.updateStatus(status,id);
			response.sendRedirect("/transaction?action=listAllTransactions");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	private void updateDateRetour(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		java.sql.Date dateRetour=java.sql.Date.valueOf(request.getParameter("dateRetour"));
		Integer id=Integer.getInteger(request.getParameter("id"));
		try {
			dao.updateDateRetour(dateRetour, id);
			response.sendRedirect("/transaction?action=listAllTransactions");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action==null ||action.equals("listAllTransactions")) {
			listAllTransactions(request,response);
		}
		if(action.equals("listByStatus")) {
			listAllTransactionByStatus(request,response);
		}
		if(action.equals("total")){
		    getTotalTransactionsByStatus(request,response);
	    }
		
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String action=request.getParameter("action");
		if(action.equals("delete")) {
			deleteTransaction(request,response);
		}
		if(action.equals("updateStatus")) {
			updateStatus(request,response);
		}
		if(action.equals("addTransaction")) {
			addTransaction(request,response);
		}
		if(action.equals("updateDateRetour")) {
			updateDateRetour(request,response);
		}
	}

}*/
package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.TransactionInfo;
import service.TransactionService;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {

    private TransactionService service = new TransactionService();

    // ========================= GET =========================
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("listAllTransactions")) {
            listAllTransactions(request, response);

        } else if (action.equals("listByStatus")) {
            listAllTransactionByStatus(request, response);

        } else if (action.equals("total")) {
            getTotalTransactionsByStatus(request, response);
        }
    }

    // ========================= POST =========================
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action.equals("delete")) {
            deleteTransaction(request, response);

        } else if (action.equals("updateStatus")) {
            updateStatus(request, response);

        } else if (action.equals("addTransaction")) {
            addTransaction(request, response);

        } else if (action.equals("updateDateRetour")) {
            updateDateRetour(request, response);
        }
    }

    // ================= METHODS =================

    private void listAllTransactions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<TransactionInfo> transactions = service.getAllTransactions();
            request.setAttribute("listAllTransactions", transactions);
            request.getRequestDispatcher("test.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur récupération transactions");
        }
    }

    private void listAllTransactionByStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = request.getParameter("status");

        try {
            List<TransactionInfo> transactions = service.getTransactionsByStatus(status);
            request.setAttribute("listByStatus", transactions);
            request.getRequestDispatcher("test.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur filtre status");
        }
    }

    private void getTotalTransactionsByStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = request.getParameter("status");

        try {
            Integer total = service.getTotalByStatus(status);
            request.setAttribute("total", total);
            request.getRequestDispatcher("test.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur calcul total");
        }
    }

    private void deleteTransaction(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id")); // FIXED
            service.deleteTransaction(id);

            response.sendRedirect("transaction?action=listAllTransactions");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur suppression");
        }
    }

    private void addTransaction(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Date dateEmprunt = Date.valueOf(request.getParameter("dateEmprunt"));
            Date dateRetour = Date.valueOf(request.getParameter("dateRetour"));

            int utilisateurId = Integer.parseInt(request.getParameter("utilisateurId")); // FIXED
            int livreId = Integer.parseInt(request.getParameter("livreId")); // FIXED

            service.addTransaction(dateEmprunt, dateRetour, utilisateurId, livreId);

            response.sendRedirect("transaction?action=listAllTransactions");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur ajout transaction");
        }
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String status = request.getParameter("status");
            int id = Integer.parseInt(request.getParameter("id")); // FIXED

            service.updateStatus(id, status);

            response.sendRedirect("transaction?action=listAllTransactions");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur update status");
        }
    }

    private void updateDateRetour(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Date dateRetour = Date.valueOf(request.getParameter("dateRetour"));
            int id = Integer.parseInt(request.getParameter("id")); // FIXED

            service.updateDateRetour(id, dateRetour);

            response.sendRedirect("transaction?action=listAllTransactions");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur update date retour");
        }
    }
}
