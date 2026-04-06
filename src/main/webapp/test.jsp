<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.List" %>
<%@ page import="dto.TransactionInfo" %>
<%@ page import="model.Livre" %>
    <title>All Transactions</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            padding: 8px 12px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even){background-color: #f9f9f9;}
    </style>
</head>
<body>

   
    <h2>All Books</h2>

    <%
        // The servlet should set this attribute:
        List<Livre> books = (List<Livre>) request.getAttribute("listAll");
        if (books == null || books.isEmpty()) {
    %>
            <p>No books found.</p>
    <%
        } else {
    %>
        <table>
            <tr>
               
                <th>Title</th>
                <th>Edition</th>
                <th>Quantity</th>
                <th>Description</th>
                <th>Year of Publication</th>
            </tr>
    <%
            for (Livre book : books) {
    %>
            <tr>
             
                <td><%= book.getTitre() %></td>
                <td><%= book.getEdition() %></td>
                <td><%= book.getQuantite() %></td>
                <td><%= book.getDescription() %></td>
                <td><%= book.getAnnePublication() %></td>
            </tr>
    <%
            }
    %>
        </table>
    <%
        }
    %>
</body>
</html>