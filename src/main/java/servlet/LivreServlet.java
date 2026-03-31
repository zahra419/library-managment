/*package servlet;
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
public class LivreServlet extends HttpServlet {
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
		Integer quantity=Integer.getInteger(request.getParameter("quantite"));
		Integer id=Integer.parseInt(request.getParameter("bookId"));
		try{
		dao.updateQuantite(quantity,id);
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
}*/
package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Livre;
import service.LivreService;

@WebServlet("/livres")
public class LivreServlet extends HttpServlet {

    private LivreService service = new LivreService();

    // get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("listAll")) {
            listAll(request, response);

        }else if (action.equals("filter")) {
            filterBooks(request, response);
        }
    }

   //posts
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action.equals("deleteBook")) {
            deleteBook(request, response);

        } else if (action.equals("updateQuantite")) {
            updateQuantite(request, response);

        } else if (action.equals("addBook")) {
            addBook(request, response);
        }
    }

   //methods

    private void listAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Livre> livres = service.getAllLivres();
        request.setAttribute("listAll", livres);
        request.getRequestDispatcher("test.jsp").forward(request, response);
    }

    private void filterBooks(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String titre = request.getParameter("titre");
        String auteurNom = request.getParameter("auteurNom");
        String auteurPrenom = request.getParameter("auteurPrenom");
        String categorie = request.getParameter("categorie");

        try {
         
            LivreService livreService = new LivreService();
            List<Livre> allBooks = livreService.getAllLivres();
            List<Livre> filteredBooks = allBooks.stream()
                .filter(l -> {
                  
                    return titre == null || titre.isEmpty() || 
                           l.getTitre().toLowerCase().contains(titre.toLowerCase());
                })
                .filter(l -> {
                   
                    if ((auteurNom == null || auteurNom.isEmpty()) && (auteurPrenom == null || auteurPrenom.isEmpty())) {
                        return true; // no author filter
                    }
                    return l.getAuteurs().stream()
                            .anyMatch(a -> (auteurNom == null || auteurNom.isEmpty() || a.getNom().equalsIgnoreCase(auteurNom)) &&
                                           (auteurPrenom == null || auteurPrenom.isEmpty() || a.getPrenom().equalsIgnoreCase(auteurPrenom)));
                })
                .filter(l -> {
                    
                    return categorie == null || categorie.isEmpty() || 
                           l.getCategories().stream()
                            .anyMatch(c -> c.getNomCategorie().equalsIgnoreCase(categorie));
                })
                .collect(Collectors.toList());
            request.setAttribute("livres", filteredBooks);
            request.getRequestDispatcher("test.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur récupération livres");
        }
    }

   
    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("bookId"));
            service.deleteLivre(id);

            response.sendRedirect("livres?action=listAll");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur lors de la suppression");
        }
    }

    private void updateQuantite(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("bookId"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));

            service.updateQuantite(id, quantite);

            response.sendRedirect("livres?action=listAll");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur lors de la mise à jour");
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String titre = request.getParameter("titre");
            String edition = request.getParameter("edition");
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            String description = request.getParameter("description");

            String strDate = request.getParameter("annePublication");
            Date annePublication = Date.valueOf(strDate);

            service.addLivre(titre, edition, quantite, description, annePublication);

            response.sendRedirect("livres?action=listAll");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Erreur lors de l'ajout");
        }
    }
}
