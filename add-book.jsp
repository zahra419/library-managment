<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add a Book — Library</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <!-- HEADER / NAVBAR -->
    <header>
        <h1>📚 Library Management</h1>
        <nav>
            <a href="index.jsp">Dashboard</a>
            <a href="books.jsp">All Books</a>
            <a href="borrow-form.jsp">Borrow a Book</a>
            <a href="add-book.jsp" class="active">Add a Book</a>
        </nav>
    </header>

    <!-- MAIN -->
    <main>

        <h1 class="page-title">Add a New Book</h1>
        <p class="page-subtitle">Register a new book into the library collection.</p>
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
            <form action="AddBookServlet" method="post">

                <!-- TITLE & AUTHOR -->
                <div class="form-row">
                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" name="title" id="title" placeholder="e.g. The Name of the Rose" required />
                    </div>
                    <div class="form-group">
                        <label for="author">Author</label>
                        <input type="text" name="author" id="author" placeholder="e.g. Umberto Eco" required />
                    </div>
                </div>

                <!-- GENRE & YEAR -->
                <div class="form-row">
                    <div class="form-group">
                        <label for="genre">Genre</label>
                        <select name="genre" id="genre" required>
                            <option value="" disabled selected>— Select a genre —</option>
                            <option value="Classic">Classic</option>
                            <option value="Mystery">Mystery</option>
                            <option value="Science Fiction">Science Fiction</option>
                            <option value="Fantasy">Fantasy</option>
                            <option value="History">History</option>
                            <option value="Philosophy">Philosophy</option>
                            <option value="Biography">Biography</option>
                            <option value="Romance">Romance</option>
                            <option value="Thriller">Thriller</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="year">Publication Year</label>
                        <input type="text" name="year" id="year" placeholder="e.g. 1980" maxlength="4" />
                    </div>
                </div>

                <!-- ISBN & PUBLISHER -->
                <div class="form-row">
                    <div class="form-group">
                        <label for="isbn">ISBN</label>
                        <input type="text" name="isbn" id="isbn" placeholder="e.g. 978-3-16-148410-0" />
                    </div>
                    <div class="form-group">
                        <label for="publisher">Publisher</label>
                        <input type="text" name="publisher" id="publisher" placeholder="e.g. Harcourt" />
                    </div>
                </div>

                <!-- DESCRIPTION -->
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea name="description" id="description" rows="4" placeholder="A short summary of the book..."></textarea>
                </div>

                <!-- ACTIONS -->
                <div class="form-actions">
                    <a href="books.jsp" class="btn">Cancel</a>
                    <button type="reset" class="btn">Reset</button>
                    <button type="submit" class="btn btn-primary">Add Book</button>
                </div>

            </form>
        </div>

    </main>

    <!-- FOOTER -->
    <footer>
        Library Management System &mdash; All rights reserved.
    </footer>

</body>
</html>
