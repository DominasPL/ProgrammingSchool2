<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Szczegóły użytkownika</title>
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
                    <th scope="col">Id Użytkownika</th>
                    <th scope="col">Id Grupy</th>
                    <th scope="col">Nazwa Użytkownika</th>
                    <th scope="col">Email</th>
                    <th scope="col">Rozwiązania użytkownika</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.group_id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>

                        <td>
                            <table class="table table-bordered">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Lp. </th>
                                    <th scope="col">Id Rozwiązania</th>
                                    <th scope="col">Data Utworzenia</th>
                                    <th scope="col">Opis</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userSolutions}" var="userSolution" varStatus="stat">
                                    <tr>
                                        <td>${stat.count}</td>
                                        <td>${userSolution.id}</td>
                                        <td>${userSolution.created}</td>
                                        <td>${userSolution.description}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </td>
                    </tr>
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
