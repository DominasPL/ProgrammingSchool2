<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 3/24/19
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie grupy</title>
</head>
<body>

<form action="/admin/group_managing/add_group" method="post">
    <h1>Dodawanie grupy</h1>
    Nazwa grupy <br>
    <input type="text" name="name"><br>
    <input type="submit" value="Wyślij">
</form>


</body>
</html>
