<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Livre" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Borrow a Book — Library</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <!-- HEADER / NAVBAR -->
    <%@ include file="navbar.jsp" %>

    <!-- MAIN -->
    <main>
        <h1 class="page-title">Borrow a Book</h1>
        <p class="page-subtitle">Fill in the details below to register a borrowing.</p>
        <div class="divider"></div>

        <!-- SUCCESS / ERROR MESSAGES -->
        <% if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("successMessage") %></div>
        <% } %>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <!-- FORM -->
        <div class="form-container">
            <form action="<%=request.getContextPath()%>/livres" method="post">
                <input type="hidden" name="action" value="addBorrow">

                <!-- BOOK SELECTION -->
                <div class="form-group">
                    <label for="bookId">Book</label>
                    <select name="bookId" id="bookId" required>
                        <option value="" disabled selected>— Select a book —</option>
                        <%
                            List<Livre> livres = (List<Livre>) request.getAttribute("listAllBorrowPage");
                            if (livres != null && !livres.isEmpty()) {
                                for (Livre livre : livres) {
                        %>
                            <option value="<%= livre.getId() %>">
                                <%= livre.getTitre() %>
                                <%
                                    if (!livre.getAuteurs().isEmpty()) {
                                        out.print(" — " + livre.getAuteurs().get(0).getPrenom() + " " + livre.getAuteurs().get(0).getNom());
                                    }
                                %>
                            </option>
                        <%
                                }
                            } else {
                        %>
                            <option disabled>No books available</option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <div class="form-divider"></div>

                <!-- BORROWER INFO -->
                <div class="form-row">
                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <input type="text" name="fullName" id="fullName" placeholder="e.g. Sarah Johnson" required />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" name="email" id="email" placeholder="e.g. sarah@email.com" required />
                    </div>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" name="phone" id="phone" placeholder="e.g. +212 6 00 00 00 00" />
                </div>

                <div class="form-divider"></div>

                <!-- DATES -->
                <div class="form-row">
                    <div class="form-group">
                        <label for="borrowDate">Borrow Date</label>
                        <input type="date" name="borrowDate" id="borrowDate" required />
                    </div>
                    <div class="form-group">
                        <label for="returnDate">Expected Return Date</label>
                        <input type="date" name="returnDate" id="returnDate" required />
                    </div>
                </div>

                <!-- ACTIONS -->
                <div class="form-actions">
                    <a href="<%=request.getContextPath()%>/livres?action=borrow" class="btn">Cancel</a>
                    <button type="submit" class="btn btn-primary">Confirm Borrow</button>
                </div>

            </form>
        </div>

    </main>

    <!-- FOOTER -->
    <%@ include file="footer.jsp" %>

    <script>
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('borrowDate').value = today;

        const returnDate = new Date();
        returnDate.setDate(returnDate.getDate() + 14);
        document.getElementById('returnDate').value = returnDate.toISOString().split('T')[0];
    </script>

</body>
</html>