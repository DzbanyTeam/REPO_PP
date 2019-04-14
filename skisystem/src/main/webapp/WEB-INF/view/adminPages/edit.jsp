<%@ page import="java.util.List" %>
<%@ page import="org.springframework.validation.FieldError" %>
<%@ page import="org.eclipse.jdt.internal.compiler.ast.ArrayReference" %>
<%@ page import="java.util.ArrayList" %><%--
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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <c:if test="${adminAccount.id != 0}">
        <title>SkiSystem - panel administracyjny - edycja administratora ${adminAccount.username}</title>
    </c:if>
    <c:if test="${adminAccount.id == 0}">
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

            <c:if test="${adminAccount.id != 0}">
                <h1 id="header">Edycja administratora ${adminAccount.username}</h1>
            </c:if>
            <c:if test="${adminAccount.id == 0}">
                <h1 id="header">Dodawanie administratora</h1>
            </c:if>

            <form:form method="post" class="mt-5" modelAttribute="adminAccount" action="/admin/admins/submit">
                <c:if test="${alertText != null}">
                    <div class="alert alert-${alertType}">
                            ${alertText}
                        <button class="close" onclick="$(this.closest('.alert')).slideUp(); return false;">&times;</button>
                    </div>
                </c:if>

                <form:hidden path="id" name="id"/>
                <div class="form-group">
                    <label>Nazwa użytkownika</label>
                    <spring:bind path="username">
                        <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="username" name="email"/>
                    </spring:bind>
                    <form:errors path="username" class="invalid-feedback" />
                </div>
                <div class="form-group">
                    <label>Hasło</label>
                    <spring:bind path="password">
                        <input class="form-control ${status.error ? 'is-invalid' : ''}" name="password" type="password" readonly onfocus="this.removeAttribute('readonly');" <c:if test="${adminAccount.id != 0}"> placeholder="Uzupełnij, aby zmienić" </c:if>>
                    </spring:bind>
                    <form:errors path="password" class="invalid-feedback" />

                </div>
                <div class="form-group">
                    <label>Imię</label>
                    <spring:bind path="name">
                        <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="name" name="email"/>
                    </spring:bind>
                    <form:errors path="name" class="invalid-feedback" />
                </div>
                <div class="form-group">
                    <label>Nazwisko</label>
                    <spring:bind path="surname">
                        <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="surname" name="email"/>
                    </spring:bind>
                    <form:errors path="surname" class="invalid-feedback" />
                </div>
                <div class="form-group">
                    <label>Adres e-mail</label>
                    <spring:bind path="email">
                        <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="email" name="email"/>
                    </spring:bind>
                    <form:errors path="email" class="invalid-feedback" />
                </div>
                <div class="form-group">
                    <label>Numer telefonu</label>
                    <spring:bind path="phoneNumber">
                        <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="phoneNumber" name="email"/>
                    </spring:bind>
                    <form:errors path="phoneNumber" class="invalid-feedback" />
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <form:checkbox path="isActive" class="form-check-input"/>
                        Aktywny
                    </label>
                </div>
                <div class="text-right">
                    <a href="<springc:url value="/admin/admins"/>" class="btn btn-light">Wróć</a>&ensp;
                    <button type="submit" class="btn btn-primary">Zapisz</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>

