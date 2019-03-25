<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 3/26/19
  Time: 12:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie zadania</title>
</head>
<body>

<form action="/admin/exercise_managing/add_exercise" method="post">
    <h1>Dodawanie zadania</h1>
    Tytuł zadania: <br>
    <input type="text" name="title"><br>
    Opis zadania: <br>
    <input type="text" name="description"><br>
    <input type="submit" value="Wyślij">
</form>


</body>
</html>
