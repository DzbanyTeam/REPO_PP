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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <c:if test="${lift.id != 0}">
        <title>SkiSystem - panel administracyjny - edycja stoku ${lift.name}</title>
    </c:if>
    <c:if test="${lift.id == 0}">
        <title>SkiSystem - panel administracyjny - dodawanie stoku</title>
    </c:if>
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
        <li class="nav-item active">
            <a class="nav-link" href="<spring:url value="/admin/lifts"/>">Wyciągi</a>
        </li>
    </ul>

    <a href="<spring:url value="/admin/logout"/>" class="navbar-brand" id="logout-button">
        <i class="fas fa-sign-out-alt"></i>
    </a>
</nav>


<div class="container py-5">
    <div class="row">
        <div class="col-lg-6 offset-lg-3">
            <a class="btn btn-lg btn-light float-left mr-3" href="<spring:url value="/admin/lifts"/>"><i class="fas fa-arrow-left"></i></a>

            <c:if test="${lift.id != 0}">
                <h1 id="header">Edycja wyciągu ${lift.name}</h1>
            </c:if>
            <c:if test="${lift.id == 0}">
                <h1 id="header">Dodawanie wyciągu</h1>
            </c:if>

            <form:form method="post" class="mt-5" modelAttribute="lift" action="/admin/lifts/submit">
                <c:if test="${alertText != null}">
                    <div class="alert alert-${alertType}">
                            ${alertText}
                        <button class="close" onclick="$(this).closest('.alert').slideUp(); return false;">&times;</button>
                    </div>
                </c:if>

                <form:hidden path="id" name="id"/>
                <div class="form-group">
                    <label>Nazwa</label>
                    <spring:bind path="name">
                        <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="name"/>
                    </spring:bind>
                    <form:errors path="name" class="invalid-feedback" />
                </div>
                <div class="form-group">
                    <label>Długość</label>
                    <spring:bind path="length">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">m</div>
                            </div>
                            <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="length"/>
                        </div>
                    </spring:bind>
                    <form:errors path="length" class="invalid-feedback" />
                </div>
                <div class="form-group">
                    <label>Wysokość początkowa</label>
                    <spring:bind path="startElevation">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">mnpm</div>
                            </div>
                            <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="startElevation"/>
                        </div>
                    </spring:bind>
                    <form:errors path="startElevation" class="invalid-feedback" />
                </div>
                <div class="form-group">
                    <label>Wysokość końcowa</label>
                    <spring:bind path="endElevation">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">mnpm</div>
                            </div>
                            <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="endElevation"/>
                        </div>
                    </spring:bind>
                    <form:errors path="endElevation" class="invalid-feedback" />
                </div>

                <div class="form-check">
                    <label class="form-check-label">
                        <form:checkbox path="isActive" class="form-check-input"/>
                        Aktywny
                    </label>
                </div>
                <div class="text-right">
                    <a href="<spring:url value="/admin/lifts"/>" class="btn btn-light">Wróć</a>&ensp;
                    <button type="submit" class="btn btn-primary">Zapisz</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>

