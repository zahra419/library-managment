<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Books — Library</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <!-- HEADER / NAVBAR -->
    <header>
        <h1>📚 Library Management</h1>
        <nav>
            <a href="index.jsp">Dashboard</a>
            <a href="books.jsp" class="active">All Books</a>
            <a href="borrow-form.jsp">Borrow a Book</a>
            <a href="add-book.jsp">Add a Book</a>
        </nav>
    </header>

    <!-- MAIN -->
    <main>

        <h1 class="page-title">All Books</h1>
        <p class="page-subtitle">Browse the full collection — available and borrowed.</p>
        <div class="divider"></div>

        <!-- SEARCH & FILTER BAR -->
        <div class="toolbar">
            <input type="text" id="searchInput" placeholder="Search by title or author..." onkeyup="filterTable()" />
            <select id="statusFilter" onchange="filterTable()">
                <option value="all">All Status</option>
                <option value="available">Available</option>
                <option value="borrowed">Borrowed</option>
            </select>
            <a href="add-book.jsp" class="btn btn-primary">➕ Add New Book</a>
        </div>

        <!-- BOOKS TABLE -->
        <table id="booksTable">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Genre</th>
                    <th>Status</th>
                    <th>Borrowed By</th>
                    <th>Return Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>

                <%-- This will be populated by the backend servlet --%>
                <%-- Example static rows for frontend preview --%>
                <tr data-status="available">
                    <td>1</td>
                    <td>The Name of the Rose</td>
                    <td>Umberto Eco</td>
                    <td>Mystery</td>
                    <td><span class="badge badge-available">Available</span></td>
                    <td>—</td>
                    <td>—</td>
                    <td>
                        <a href="book-details.jsp?id=1" class="btn btn-sm">Details</a>
                    </td>
                </tr>
                <tr data-status="borrowed">
                    <td>2</td>
                    <td>Don Quixote</td>
                    <td>Miguel de Cervantes</td>
                    <td>Classic</td>
                    <td><span class="badge badge-borrowed">Borrowed</span></td>
                    <td>Sarah Johnson</td>
                    <td>2025-03-01</td>
                    <td>
                        <a href="book-details.jsp?id=2" class="btn btn-sm">Details</a>
                        <a href="return.jsp?id=2" class="btn btn-sm btn-return">Return</a>
                    </td>
                </tr>
                <tr data-status="available">
                    <td>3</td>
                    <td>Meditations</td>
                    <td>Marcus Aurelius</td>
                    <td>Philosophy</td>
                    <td><span class="badge badge-available">Available</span></td>
                    <td>—</td>
                    <td>—</td>
                    <td>
                        <a href="book-details.jsp?id=3" class="btn btn-sm">Details</a>
                    </td>
                </tr>

            </tbody>
        </table>

        <!-- EMPTY STATE -->
        <div id="emptyState" class="empty-state" style="display:none;">
            <p>📭 No books match your search.</p>
        </div>

    </main>

    <!-- FOOTER -->
    <footer>
        Library Management System &mdash; All rights reserved.
    </footer>

    <script>
        function filterTable() {
            const search = document.getElementById('searchInput').value.toLowerCase();
            const status = document.getElementById('statusFilter').value;
            const rows = document.querySelectorAll('#booksTable tbody tr');
            let visibleCount = 0;

            rows.forEach(row => {
                const text = row.innerText.toLowerCase();
                const rowStatus = row.getAttribute('data-status');
                const matchesSearch = text.includes(search);
                const matchesStatus = status === 'all' || rowStatus === status;

                if (matchesSearch && matchesStatus) {
                    row.style.display = '';
                    visibleCount++;
                } else {
                    row.style.display = 'none';
                }
            });

            document.getElementById('emptyState').style.display = visibleCount === 0 ? 'block' : 'none';
        }
    </script>

</body>
</html>
