<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Użytkownicy grupy</title>
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
                    <th scope="col">Nazwa Użytkownika</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user" varStatus="stat">
                    <tr>
                        <td>${stat.count}.</td>
                        <td><a href="/groups/group_users/user_details?id=${user.id}">${user.id}</a></td>
                        <td>${user.username}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<footer class="page-footer font-small">
    <jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</footer>
</body>
</html>
