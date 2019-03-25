<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 3/26/19
  Time: 12:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie użytkownika</title>
</head>
<body>

<form action="/admin/user_managing/add_user" method="post">
    <h1>Dodawanie użytkownika</h1>
    ID grupy: <br>
    <input type="number" name="group_id"><br>
    Nazwa użytkownika: <br>
    <input type="text" name="username"><br>
    Hasło: <br>
    <input type="text" name="password"><br>
    Adres email: <br>
    <input type="text" name="email"><br>
    <input type="submit" value="Wyślij">
</form>


</body>
</html>
