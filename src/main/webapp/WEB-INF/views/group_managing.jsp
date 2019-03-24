<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Grupy</title>
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
                    <th scope="col">Id Grupy</th>
                    <th scope="col">Nazwa grupy</th>
                    <th scope="col">Edycja grupy</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userGroups}" var="userGroup" varStatus="stat">
                    <tr>
                        <td>${stat.count}</td>
                        <td>${userGroup.id}</td>
                        <td>${userGroup.name}</td>
                        <td><a href="/admin/group_managing/edit_group?id=${userGroup.id}">Link</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <input type="button" value="Dodanie grupy" onclick="window.location.href='/admin/group_managing/add_group'">

        </div>
    </div>
</div>
<footer class="page-footer font-small">
    <jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</footer>
</body>
</html>
