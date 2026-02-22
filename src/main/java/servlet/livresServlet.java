package servlet;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import dao.LivreDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Livre;


@WebServlet("/livres")
public class livresServlet extends HttpServlet {
	private LivreDao dao=new LivreDao();
	private void listAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<Livre> livres=dao.getAllLivres();
		request.setAttribute("listAll", livres);
		request.getRequestDispatcher("test.jsp").forward(request,response);
	}
	private void listBooksByAuthor(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		try{
		List<Livre> livres=dao.getBooksByAuthor(nom, prenom);
		request.setAttribute("listByAuthor", livres);
		request.getRequestDispatcher("test.jsp").forward(request,response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void listBooksByCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String categorie=request.getParameter("categorie");
		try{
		List<Livre> livres=dao.getBooksbyCategorie(categorie);
		request.setAttribute("listByCategory", livres);
		request.getRequestDispatcher("test.jsp").forward(request,response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void deleteBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Integer id=Integer.parseInt(request.getParameter("bookId"));
		try{
		dao.delete(id);
		response.sendRedirect("livres?action=listAll");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void updateQuantite(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Integer disponible=Integer.getInteger(request.getParameter("quantite"));
		Integer id=Integer.parseInt(request.getParameter("bookId"));
		try{
		dao.updateQuantite(disponible,id);
		response.sendRedirect("livres?action=listAll");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void addBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String titre=request.getParameter("titre");
		String edition=request.getParameter("edtion");
		Integer quantite=Integer.getInteger(request.getParameter("quantite"));
		String description=request.getParameter("description");
		String strdate=request.getParameter("annePublication");
		java.sql.Date annePublication=java.sql.Date.valueOf(strdate);
		try {
			dao.insert(titre,edition,quantite,description,annePublication);
			response.sendRedirect("livre?action=listAll");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action==null ||action.equals("listAll")) {
			listAll(request,response);
		}
		if(action.equals("listByAuthor")) {
			listBooksByAuthor(request,response);
		}
		if(action.equals("listByCategorie")){
		    listBooksByCategory(request,response);
	    }
		
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String action=request.getParameter("action");
		if(action.equals("deleteBook")) {
			deleteBook(request,response);
		}
		if(action.equals("updateQuantite")) {
			updateQuantite(request,response);
		}
		if(action.equals("addBook")) {
			addBook(request,response);
		}
	}
}
