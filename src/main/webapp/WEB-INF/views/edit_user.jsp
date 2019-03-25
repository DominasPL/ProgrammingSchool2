<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 3/25/19
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja użytkownika</title>
</head>
<body>

<form action="/admin/user_managing/edit_user" method="post">
    <h1>Edycja użytkownika</h1>
    ID użytkownika: ${id}<br>
    <input type="hidden" name="id" value="${id}">
    Nowy ID grupy: <br>
    <input type="number" name="group_id"><br>
    Nowa nazwa użytkownika: <br>
    <input type="text" name="username"><br>
    Nowe hasło: <br>
    <input type="text" name="password"><br>
    Nowy adres email: <br>
    <input type="text" name="email"><br>
    <input type="submit" value="Wyślij">
</form>


</body>
</html>