<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Szczegóły rozwiązania</title>
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
                    <th scope="col">Data Dodania</th>
                    <th scope="col">Data Modyfikacji</th>
                    <th scope="col">Opis Rozwiązania</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${solution.created}</td>
                        <td>${solution.updated}</td>
                        <td>${solution.description}</td>
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
