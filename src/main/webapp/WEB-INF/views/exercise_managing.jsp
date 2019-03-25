<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zadania</title>
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
                    <th scope="col">Id zadania</th>
                    <th scope="col">Tytuł zadania</th>
                    <th scope="col">Opis zadania</th>
                    <th scope="col">Edycja zadania</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${exercises}" var="exercise" varStatus="stat">
                    <tr>
                        <td>${stat.count}</td>
                        <td>${exercise.id}</td>
                        <td>${exercise.title}</td>
                        <td>${exercise.description}</td>
                        <td><a href="/admin/exercise_managing/edit_exercise?id=${exercise.id}">Link</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <input type="button" value="Dodanie zadania" onclick="window.location.href='/admin/exercise_managing/add_exercise'">

        </div>
    </div>
</div>
<footer class="page-footer font-small">
    <jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</footer>
</body>
</html>
