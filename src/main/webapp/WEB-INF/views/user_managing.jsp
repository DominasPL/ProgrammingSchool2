<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Użytkownicy</title>
    <jsp:include page="/WEB-INF/views/fragments/bootstrap.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col12 m-2"></div>
    </div>
    <div class="row">
        <div class="col12 m-2">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Lp.</th>
                    <th scope="col">Id Użytkownika</th>
                    <th scope="col">Id Grupy</th>
                    <th scope="col">Nazwa użytkownika</th>
                    <th scope="col">Email</th>
                    <th scope="col">Edycja użytkownika</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user" varStatus="stat">
                    <tr>
                        <td>${stat.count}</td>
                        <td>${user.id}</td>
                        <td>${user.group_id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td><a href="/admin/user_managing/edit_user?id=${user.id}">Link</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <input type="button" value="Dodanie użytkownika" onclick="window.location.href='/admin/user_managing/add_user'">

        </div>
    </div>
</div>
<footer class="page-footer font-small">
    <jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</footer>
</body>
</html>
