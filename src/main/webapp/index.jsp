<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <!-- HEADER / NAVBAR -->
    <%@ include file="navbar.jsp" %>

    <!-- MAIN -->
    <main>

        <h1 class="page-title">Dashboard</h1>
        <p class="page-subtitle">Welcome back — here's your library at a glance.</p>
        <div class="divider"></div>

        <!-- STATS CARDS -->
        <section class="stats">

            <div class="card">
                <h2>Total Books</h2>
                <p class="count"><%= request.getAttribute("totalBooks") != null ? request.getAttribute("totalBooks") : "—" %></p>
                <span class="card-icon">📚</span>
            </div>

            <div class="card">
                <h2>Borrowed</h2>
                <p class="count borrowed"><%= request.getAttribute("borrowedBooks") != null ? request.getAttribute("borrowedBooks") : "—" %></p>
                <span class="card-icon">📖</span>
            </div>

            <div class="card">
                <h2>Available</h2>
                <p class="count available"><%= request.getAttribute("availableBooks") != null ? request.getAttribute("availableBooks") : "—" %></p>
                <span class="card-icon">✅</span>
            </div>

        </section>

        <!-- QUICK LINKS -->
        <h3 class="section-heading">Quick Actions</h3>
        <section class="quick-links">
            <a href="books.jsp" class="btn">View All Books</a>
            <a href="borrow-form.jsp" class="btn">Borrow a Book</a>
            <a href="add-book.jsp" class="btn btn-primary">Add New Book</a>
        </section>

    </main>

    <!-- FOOTER -->
    <%@ include file="footer.jsp" %>

</body>
</html>
