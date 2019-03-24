<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona główna</title>
    <jsp:include page="/WEB-INF/views/fragments/bootstrap.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col12 m-2"></div>
    </div>
    <div class="row">
        <div class="col12 m-2"><h1>Ostatnie rozwiązania</h1></div>
    </div>
    <div class="row">
        <div class="col12 m-2">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Lp.</th>
                    <th scope="col">Id Rozwiązania</th>
                    <th scope="col">Id Zadania</th>
                    <th scope="col">Id Autora</th>
                    <th scope="col">Data Dodania</th>
                    <th scope="col">Szczegóły</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${solutions}" var="solution" varStatus="stat">
                    <tr>
                        <td>${stat.count}.</td>
                        <td>${solution.id}</td>
                        <td>${solution.exercise_id}</td>
                        <td>${solution.user_id}</td>
                        <td>${solution.created}</td>
                        <td><a href="/solution_detail?id=${solution.id}">Szczegóły rozwiązania</a></td>
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
