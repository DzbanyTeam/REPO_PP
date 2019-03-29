<%--
  Created by IntelliJ IDEA.
  User: Przemek-PC
  Date: 18.03.2019
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springc" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>SkiProject - panel administracyjny - edycja administratorów</title>
    <link rel="stylesheet" href="<springc:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<springc:url value="/resources/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<springc:url value="/resources/css/style.css"/>">
</head>

<body>
<nav class="navbar navbar-expand bg-light">
    <a class="navbar-brand" href="<springc:url value="/admin"/>">SkiProject</a>

    <ul class="navbar-nav mr-auto" id="navbar">
        <li class="nav-item active" id="navbar-item-schedule">
            <a class="nav-link" href="<springc:url value="/admin/admins"/>">Administratorzy</a>
        </li>
    </ul>

    <a href="<springc:url value="/admin/logout"/>" class="navbar-brand" id="logout-button">
        <i class="fas fa-sign-out-alt"></i>
    </a>
</nav>


<div class="container py-5">
    <h1 id="header">Administratorzy</h1>
    <form action="<springc:url value="/admin/admins/update"/>" method="GET">
        <table class="table mt-5">
            <thead class="thead-dark">
            <tr>
                <th></th>
                <th>Nazwa użytkownika</th>
                <th>Imię i nazwisko</th>
                <th>Aktywny</th>
                <th class="text-right">Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${admins}" var="admin">
            <tr>
                <td><input type="checkbox" name="ids[]" value="${admin.id}"></td>
                <td>${admin.username}</td>
                <td>${admin.name} ${admin.surname}</td>
                <c:if test="${admin.isActive()}">
                    <td><a class="text-success" href="<springc:url value="/admin/admins/update?ids%5B%5D=${admin.id}&action=deactivate"/>" title="Dezaktywuj"><i class="fas fa-check text-success"></i></a></td>
                </c:if>
                <c:if test="${!admin.isActive()}">
                    <td><a class="text-success" href="<springc:url value="/admin/admins/update?ids%5B%5D=${admin.id}&action=activate"/>" title="Aktywuj"><i class="fas fa-times text-danger"></i></a></td>
                </c:if>
                <td class="text-right py-2">
                    <div class="btn-group">
                        <a class="btn btn-primary fas fa-pen" href="<springc:url value="/admin/admins/edit/${admin.id}"/>"></a>
                        <a class="btn btn-light fas fa-trash-alt" href="<springc:url value="/admin/admins/update?ids%5B%5D=${admin.id}&action=delete"/>"></a>
                    </div>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <a href="<springc:url value="/admin/admins/add"/>" class="btn btn-primary float-right">Dodaj administratora&ensp;<i class="fas fa-plus"></i></a>
            <select class="form-control" name="action" onchange="$(this).closest('form').submit()" style="width:auto">
                <option value="" disabled selected>Masowa edycja</option>
                <option value="delete">Usuń zaznaczone</option>
                <option value="activate">Aktywuj zaznaczone</option>
                <option value="deactivate">Dezaktywuj zaznaczone</option>
            </select>
        </div>
    </form>
</div>

<script src="<springc:url value="/resources/js/jquery.js"/>"></script>

</body>
</html>


