<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add a Book — Library</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <main>
        <h1 class="page-title">Add a New Book</h1>
        <p class="page-subtitle">Register a new book into the library collection.</p>
        <div class="divider"></div>

        <% if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("successMessage") %></div>
        <% } %>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <div class="form-container">
           <form action="livres" method="post">
               <input type="hidden" name="action" value="addBook" />

               <div class="form-row">
                   <div class="form-group">
                       <label for="titre">Title <span class="required">*</span></label>
                       <input type="text" name="titre" id="titre" required />
                   </div>

                   <div class="form-group">
                       <label for="edition">Edition <span class="required">*</span></label>
                       <input type="text" name="edition" id="edition" required />
                   </div>
               </div>

               <div class="form-row">
                   <div class="form-group">
                       <label for="quantite">Quantity <span class="required">*</span></label>
                       <input type="number" name="quantite" id="quantite" min="0" value="0" required />
                   </div>

                   <div class="form-group">
                       <label for="annePublication">Publication Date <span class="required">*</span></label>
                       <input type="date" name="annePublication" id="annePublication" required />
                   </div>
               </div>

               <div class="form-group">
                   <label for="description">Description</label>
                   <textarea name="description" id="description" rows="4"></textarea>
               </div>

               <!-- Author fields -->
               <div class="form-row">
                   <div class="form-group">
                       <label for="nomAuteur">Author Last Name <span class="required">*</span></label>
                       <input type="text" name="nomAuteur" id="nomAuteur" required />
                   </div>
                   <div class="form-group">
                       <label for="prenomAuteur">Author First Name <span class="required">*</span></label>
                       <input type="text" name="prenomAuteur" id="prenomAuteur" required />
                   </div>
               </div>

               <!-- Category / Genre field -->
               <div class="form-group">
                   <label for="nomCategorie">Category <span class="required">*</span></label>
                   <input type="text" name="nomCategorie" id="nomCategorie" required />
               </div>

               <div class="form-actions">
                   <a href="livres?action=listAll" class="btn">Cancel</a>
                   <button type="reset" class="btn">Reset</button>
                   <button type="submit" class="btn btn-primary">Add Book</button>
               </div>
           </form>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>