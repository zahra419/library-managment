package servlet;
import java.io.IOException;
import java.util.List;
import dao.UtilisateurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;


@WebServlet("/clients")
public class UtilisateurServlet extends HttpServlet {
	private UtilisateurDao dao=new UtilisateurDao();
	
	private void listAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<Utilisateur> utilisateur=dao.getAllUtilisateurs();
		request.setAttribute("listAll", utilisateur);
		request.getRequestDispatcher("test.jsp").forward(request,response);
	}
	private void deleteUtilisateur(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Integer id=Integer.parseInt(request.getParameter("utilisateurId"));
		try{
		dao.delete(id);
		response.sendRedirect("clients?action=listAll");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void addUtilisateur(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String cin=request.getParameter("cin");
		String telephone=request.getParameter("telephone");
		Utilisateur utilisateur=new Utilisateur(nom,prenom,cin,telephone);
		try{
		dao.insert(utilisateur);
		response.sendRedirect("clients?action=listAll");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action==null ||action.equals("listAll")) {
			listAll(request,response);
		}
		
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action==null ||action.equals("inset")) {
			addUtilisateur(request,response);
		}
		if(action.equals("delete")){
		    deleteUtilisateur(request,response);
	    }
		
	}
	

}
