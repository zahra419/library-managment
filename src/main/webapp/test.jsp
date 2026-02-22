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
<%@ page import="java.util.*,  model.Livre" %>
<%
   
List<Livre> livres = (List<Livre>) request.getAttribute("listAll");
   if(livres!=null){
    for(Livre l : livres) {
%>
        <p>
            <b>ID:</b> <%= l.getId() %> <br>
            <b>Title:</b> <%= l.getTitre() %> <br>
            <b>Edition:</b> <%= l.getEdition() %> <br>
            <b>Disponible:</b> <%= l.getQuantite() %> <br>
            <b>Description:</b> <%= l.getDescription() %> <br>
            <b>Publication:</b> <%= l.getAnnePublication() %>
        </p>
        <hr>
<%
    }}
%>
</body>
</html>