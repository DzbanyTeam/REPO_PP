<%--
  Created by IntelliJ IDEA.
  User: Przemek-PC
  Date: 24.03.2019
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springc" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <c:if test="${admin.id != 0}">
        <title>SkiSystem - panel administracyjny - edycja administratora ${admin.username}</title>
    </c:if>
    <c:if test="${admin.id == 0}">
        <title>SkiSystem - panel administracyjny - dodawanie administratora</title>
    </c:if>
    <link rel="stylesheet" href="<springc:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<springc:url value="/resources/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<springc:url value="/resources/css/style.css"/>">
</head>

<body>
<nav class="navbar navbar-expand bg-light">
    <a class="navbar-brand" href="<springc:url value="/admin"/>">SkiSystem</a>

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
    <div class="row">
        <div class="col-lg-6 offset-lg-3">
            <a class="btn btn-lg btn-light float-left mr-3" href="<springc:url value="/admin/admins"/>"><i class="fas fa-arrow-left"></i></a>

            <c:if test="${admin.id != 0}">
                <h1 id="header">Edycja administratora ${admin.username}</h1>
            </c:if>
            <c:if test="${admin.id == 0}">
                <h1 id="header">Dodawanie administratora</h1>
            </c:if>

            <form class="mt-5" action="<springc:url value="/admin/admins/submit"/>" method="POST">
                <form:hidden path="admin.id" name="id"/>
                <div class="form-group">
                    <label>Login</label>
                    <form:input class="form-control" path="admin.username" name="username"/>
                    <form:errors path="username" style="color:red" class="form-text text-muted" />
                </div>
                <div class="form-group">
                    <label>Hasło</label>
                    <input class="form-control" name="password" type="password" <c:if test="${admin.id != 0}"> placeholder="Uzupełnij, aby zmienić" </c:if>>
                </div>
                <div class="form-group">
                    <label>Imię</label>
                    <form:input class="form-control" path="admin.name" name="name"/>
                </div>
                <div class="form-group">
                    <label>Nazwisko</label>
                    <form:input class="form-control" path="admin.surname" name="surname"/>
                </div>
                <div class="form-group">
                    <label>Adres e-mail</label>
                    <form:input class="form-control" path="admin.email" name="email"/>
                </div>
                <div class="form-group">
                    <label>Numer telefonu</label>
                    <form:input class="form-control" path="admin.phoneNumber" name="phoneNumber"/>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <input class="form-check-input" type="checkbox" value="1" name="isActive" <c:if test="${admin.isActive()}"> checked </c:if>>
                        Aktywny
                    </label>
                </div>
                <div class="text-right">
                    <a href="<springc:url value="/admin/admins"/>" class="btn btn-light">Wróć</a>&ensp;
                    <button type="submit" class="btn btn-primary">Zapisz</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>

