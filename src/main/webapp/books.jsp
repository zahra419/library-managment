<%@ page import="java.util.List" %>
<%@ page import="model.Livre" %>
<%@ page import="model.Auteur" %>
<%@ page import="model.Categorie" %>


<head>
<link rel="stylesheet" href="style.css">
</head>


<%@ include file="navbar.jsp" %>
<!-- SEARCH BAR -->
<div class="toolbar">
    <input type="text" id="searchInput" placeholder="Search by title or author..." onkeyup="filterTable()" />
    <a href="add-book.jsp" class="btn btn-primary">Add New Book</a>
</div>

<!-- BOOKS TABLE -->
<table id="booksTable">
    <thead>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Quantity</th>
            <th>Description</th>
            <th>Publication Date</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Livre> livres = (List<Livre>) request.getAttribute("listAll");
            if (livres != null && !livres.isEmpty()) {
                for (Livre l : livres) {
        %>
        <tr>
            <td><%= l.getTitre() %></td>

            <td>
                <%= l.getAuteurs().isEmpty() ? "—" :
                    l.getAuteurs().get(0).getPrenom() + " " + l.getAuteurs().get(0).getNom() %>
            </td>

            <td>
                <%= l.getCategories().isEmpty() ? "—" : l.getCategories().get(0).getNomCategorie() %>
            </td>

            <td><%= l.getQuantite() %></td>

            <td><%= l.getDescription() %></td>

            <td>
                <%= l.getAnnePublication() != null ? l.getAnnePublication() : "—" %>
            </td>

            <td>
                <!-- Delete -->
                <form action="livres" method="post" style="display:inline;">
				    <input type="hidden" name="action" value="deleteBook">
				    <input type="hidden" name="bookId" value="<%= l.getId() %>">
				    <button type="submit" class="btn btn-sm btn-danger"
				            onclick="return confirm('Delete this book?')">Delete</button>
				</form>

                <!-- Update Quantity -->
                <form action="livres" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="updateQuantite">
                    <input type="hidden" name="bookId" value="<%= l.getId() %>">
                    <button type="submit" class="btn btn-sm btn-warning">Update</button>
                </form>
            </td>
        </tr>
        <% 
                } 
            } else { 
        %>
        <tr><td colspan="7" style="text-align:center;">No books found.</td></tr>
        <% } %>
    </tbody>
</table>

<script>
function filterTable() {
    // Get input value and convert to lowercase
    const input = document.getElementById("searchInput").value.toLowerCase();
    const table = document.getElementById("booksTable");
    const rows = table.getElementsByTagName("tr");

    // Loop through table rows (skip header)
    for (let i = 1; i < rows.length; i++) {
        const titleCell = rows[i].getElementsByTagName("td")[0];
        const categoryCell = rows[i].getElementsByTagName("td")[2];
        if (titleCell && categoryCell) {
            const titleText = titleCell.textContent.toLowerCase();
            const categoryText = categoryCell.textContent.toLowerCase();
            
            // Show row if title OR category matches input
            if (titleText.includes(input) || categoryText.includes(input)) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }
}
</script>
