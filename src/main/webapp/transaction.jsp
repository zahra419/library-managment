<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dto.TransactionInfo" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Books — Library</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <%@ include file="navbar.jsp" %>

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
            <a href="add-book.jsp" class="btn btn-primary">Add New Book</a>
        </div>

        <!-- BOOKS TABLE -->
        <table id="booksTable">
            <thead>
                <tr>
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
                <%
                    List<TransactionInfo> books = (List<TransactionInfo>) request.getAttribute("listAllTransactions");
                    if (books != null && !books.isEmpty()) {
                        for (TransactionInfo b : books) {
                %>
                <tr data-status="<%= b.isAvailable() ? "available" : "borrowed" %>">
                    <td><%= b.getTitle() %></td>
                    <td><%= b.getAuthor() %></td>
                    <td><%= b.getCategory() %></td>
                    <td>
                        <span class="badge <%= b.isAvailable() ? "badge-available" : "badge-borrowed" %>">
                            <%= b.isAvailable() ? "Available" : "Borrowed" %>
                        </span>
                    </td>
                    <td><%= b.getBorrowedBy() == null ? "—" : b.getBorrowedBy() %></td>
                    <td><%= b.getReturnDate() == null ? "—" : b.getReturnDate() %></td>
                    <td>
                        <!-- Delete -->
                        <form action="books" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="deleteBook">
                            <input type="hidden" name="bookId" value="<%= b.getBookId() %>">
                            <button type="submit" class="btn btn-sm btn-danger" 
                                    onclick="return confirm('Delete this book?')">Delete</button>
                        </form>

                        <!-- Update Quantity -->
                        <form action="books" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="updateQuantite">
                            <input type="hidden" name="bookId" value="<%= b.getBookId() %>">
                            <input type="number" name="quantite" value="<%= b.isAvailable() ? 1 : 0 %>" min="0" style="width:70px;">
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

        <div id="emptyState" class="empty-state" style="display:none;">
            <p>No books match your search.</p>
        </div>
    </main>

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