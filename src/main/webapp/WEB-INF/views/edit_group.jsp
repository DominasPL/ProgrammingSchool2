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
    <title>Edycja grupy</title>
</head>
<body>

    <form action="/admin/group_managing/edit_group" method="post">
        <h1>Edycja grupy</h1>
        ID grupy: ${id}<br>
        <input type="hidden" name="id" value="${id}">
        Nowa nazwa grupy <br>
        <input type="text" name="name"><br>
        <input type="submit" value="Wyślij">
    </form>


</body>
</html>
