<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Borrow a Book — Library</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <!-- HEADER / NAVBAR -->
    <header>
        <h1>📚 Library Management</h1>
        <nav>
            <a href="index.jsp">Dashboard</a>
            <a href="books.jsp">All Books</a>
            <a href="borrow-form.jsp" class="active">Borrow a Book</a>
            <a href="add-book.jsp">Add a Book</a>
        </nav>
    </header>

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
            <form action="BorrowServlet" method="post">

                <!-- BOOK SELECTION -->
                <div class="form-group">
                    <label for="bookId">Book</label>
                    <select name="bookId" id="bookId" required>
                        <option value="" disabled selected>— Select a book —</option>
                        <%-- Backend will populate this list dynamically --%>
                        <%-- Example static options for frontend preview: --%>
                        <option value="1">The Name of the Rose — Umberto Eco</option>
                        <option value="3">Meditations — Marcus Aurelius</option>
                    </select>
                </div>

                <div class="form-divider"></div>

                <!-- BORROWER INFO -->
                <!-- <p class="form-section-title">Borrower Information</p> -->
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
                <!-- <p class="form-section-title">Dates</p> -->

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
                    <a href="books.jsp" class="btn">Cancel</a>
                    <button type="submit" class="btn btn-primary">Confirm Borrow</button>
                </div>

            </form>
        </div>

    </main>

    <!-- FOOTER -->
    <footer>
        Library Management System &mdash; All rights reserved.
    </footer>

    <script>
        // Auto-set today's date for borrow date
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('borrowDate').value = today;

        // Auto-set return date to 2 weeks from today
        const returnDate = new Date();
        returnDate.setDate(returnDate.getDate() + 14);
        document.getElementById('returnDate').value = returnDate.toISOString().split('T')[0];
    </script>

</body>
</html>
