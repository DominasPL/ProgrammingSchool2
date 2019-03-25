<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 3/26/19
  Time: 12:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja zadania</title>
</head>
<body>

<form action="/admin/exercise_managing/edit_exercise" method="post">
    <h1>Edycja zadania</h1>
    ID zadania: ${id}<br>
    <input type="hidden" name="id" value="${id}">
    Nowy tytuł zadania: <br>
    <input type="text" name="title"><br>
    Nowy opis zadania: <br>
    <input type="text" name="description"><br>
    <input type="submit" value="Wyślij">
</form>


</body>
</html>

