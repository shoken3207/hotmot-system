<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Names</title>
</head>
<body>
    <h2>Names:</h2>
    <ul>
        <% 
            List<String> names = (List<String>)request.getAttribute("names");
            for (String name : names) {
        %>
            <li><%= name %></li>
        <% } %>
    </ul>
</body>
</html>