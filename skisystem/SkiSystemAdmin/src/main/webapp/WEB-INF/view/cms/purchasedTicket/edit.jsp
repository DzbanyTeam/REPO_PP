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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>SkiSystem - panel administracyjny - bilet klienta ${purchasedTicket.getCustomer().getUsername()}</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap-datepicker.min.css"/>">
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
        <li class="nav-item active">
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
    <div class="row">
        <div class="col-lg-6 offset-lg-3">
            <a class="btn btn-lg btn-light float-left mr-3" href="<spring:url value="/admin/purchased-tickets/${purchasedTicket.getCustomer().getId()}"/>"><i class="fas fa-arrow-left"></i></a>

            <c:if test="${purchasedTicket.id != null}">
                <h1 id="header">Edycja biletu klienta ${purchasedTicket.getCustomer().getUsername()}</h1>
            </c:if>
            <c:if test="${purchasedTicket.id == null}">
                <h1 id="header">Dodawanie biletu klienta ${purchasedTicket.getCustomer().getUsername()}</h1>
            </c:if>

            <form:form method="post" class="mt-5" modelAttribute="purchasedTicket" action="/admin/purchased-tickets/submit">
                <c:if test="${alertText != null}">
                    <div class="alert alert-${alertType}">
                            ${alertText}
                        <button class="close" onclick="$(this).closest('.alert').slideUp(); return false;">&times;</button>
                    </div>
                </c:if>

                <form:hidden path="id"/>
                <form:hidden path="customer"/>
                <div class="form-group">
                    <label>Rodzaj biletu</label>
                    <form:select class="custom-select" path="price">
                        <form:options items="${prices}" itemValue="id" itemLabel="string"/>
                    </form:select>
                </div>
                <div class="form-group date">
                    <label>Data zakupu</label>
                    <spring:bind path="purchaseDatetime">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text"><i class="fas fa-calendar-alt"></i></div>
                            </div>
                            <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="purchaseDatetime"/>
                        </div>
                    </spring:bind>
                    <form:errors path="purchaseDatetime" class="invalid-feedback" />
                </div>
                <div class="form-group date">
                    <label>Data wygaśnięcia</label>
                    <spring:bind path="expirationDatetime">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text"><i class="fas fa-calendar-alt"></i></div>
                            </div>
                            <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="expirationDatetime"/>
                        </div>
                    </spring:bind>
                    <form:errors path="expirationDatetime" class="invalid-feedback" />
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <form:checkbox path="isActive" class="form-check-input"/>
                        Aktywny
                    </label>
                </div>
                <div class="text-right">
                    <a href="<spring:url value="/admin/purchased-tickets"/>" class="btn btn-light">Wróć</a>&ensp;
                    <button type="submit" class="btn btn-primary">Zapisz</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script src="<spring:url value="/resources/common/js/jquery.js"/>"></script>
<script src="<spring:url value="/resources/common/js/bootstrap.bundle.min.js"/>"></script>
<script src="<spring:url value="/resources/common/js/bootstrap-datepicker.min.js"/>"></script>
<script>
    $('.date').datepicker({
        'language': 'pl',
        'weekstart': 1,
        'format': 'dd.mm.yyyy'
    });
</script>

</body>
</html>

