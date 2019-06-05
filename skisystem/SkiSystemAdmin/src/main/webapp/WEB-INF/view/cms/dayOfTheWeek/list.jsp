<%--
  Created by IntelliJ IDEA.
  User: Przemek-PC
  Date: 18.03.2019
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>SkiSystem - panel administracyjny - edycja dni tygodnia</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/cms/css/style.css"/>">
</head>

<body>
<nav class="navbar navbar-expand bg-light">
    <a class="navbar-brand" href="<spring:url value="/admin"/>">SkiSystem</a>

    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="<spring:url value="/admin/admins"/>">Administratorzy</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<spring:url value="/admin/customers"/>">Klienci</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<spring:url value="/admin/slopes"/>">Stoki</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<spring:url value="/admin/lifts"/>">Wyciągi</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Cennik</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="<spring:url value="/admin/ticket-types"/>">Rodzaje biletów</a>
                <a class="dropdown-item" href="<spring:url value="/admin/ticket-categories"/>">Kategorie cenowe</a>
                <a class="dropdown-item" href="<spring:url value="/admin/seasons"/>">Sezony</a>
                <a class="dropdown-item" href="<spring:url value="/admin/prices"/>">Ceny</a>
            </div>
        </li>
    </ul>

    <a href="<spring:url value="/admin/logout"/>" class="navbar-brand" id="logout-button">
        <i class="fas fa-sign-out-alt"></i>
    </a>
</nav>


<div class="container py-5">
    <h1 id="header">Dni tygodnia</h1>
    <form action="<spring:url value="/admin/days-of-the-week/update"/>" method="GET" class="mt-5">
        <c:if test="${alertText != null}">
            <div class="alert alert-${alertType}">
                    ${alertText}
                <button class="close" onclick="$(this).closest('.alert').slideUp(); return false;">&times;</button>
            </div>
        </c:if>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th></th>
                <th>Nazwa</th>
                <th class="text-right">Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${daysOfTheWeek}" var="dayOfTheWeek">
                <tr>
                    <td><input type="checkbox" name="ids[]" value="${dayOfTheWeek.id}"></td>
                    <td>${dayOfTheWeek.name}</td>
                    <td class="text-right py-2">
                        <div class="btn-group">
                            <a class="btn btn-primary fas fa-pen" href="<spring:url value="/admin/days-of-the-week/edit/${dayOfTheWeek.id}"/>"></a>
                            <a class="btn btn-light fas fa-trash-alt" href="<spring:url value="/admin/days-of-the-week/update?ids%5B%5D=${dayOfTheWeek.id}&action=delete"/>"></a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <a href="<spring:url value="/admin/days-of-the-week/add"/>" class="btn btn-primary float-right">Dodaj dzień tygodnia <i class="fas fa-plus"></i></a>
            <select class="form-control" name="action" onchange="$(this).closest('form').submit()" style="width:auto">
                <option value="" disabled selected>Masowa edycja</option>
                <option value="delete">Usuń zaznaczone</option>
            </select>
        </div>
    </form>
</div>


<script src="<spring:url value="/resources/common/js/jquery.js"/>"></script>
<script src="<spring:url value="/resources/common/js/bootstrap.bundle.min.js"/>"></script>

</body>
</html>


