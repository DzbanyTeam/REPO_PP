<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Strefa klienta SkiSystem - panel klienta</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/site/css/customer.css"/>">
    <script src="<spring:url value="/resources/common/js/jquery.js"/>"></script>
    <script src="<spring:url value="/resources/common/js/bootstrap.min.js"/>"></script>
    <style>
        .list-group {
            float:left;
            padding-left:20px;
            width: 15%;
        }
        .panel-content {
            margin-left:20px;
            float:left;
            padding:20px;
            width: 85%;
            margin-right: 20px;
            height: 800px;
        }
    </style>
</head>
<body class="bg-light">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-5 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">Strefa klienta SkiProject</h5>
    <a class="btn btn-default mr-3" href="<spring:url value="/customer/logout"/>"><i class="fas fa-sign-out-alt"></i> Wyloguj się</a>
    <a class="btn btn-secondary" href="<spring:url value="/"/>">Powrót do serwisu</a>
</div>
<div class="d-flex flex-md-row">
    <div class="list-group flex-column ">
        <a href="<spring:url value="/customer"/>" class="list-group-item"><i class="fa fa-home"></i> <span>Podgląd</span></a>
        <a href="<spring:url value="/customer/data"/>" class="list-group-item active"><i class="fa fa-address-card"></i> <span>Edycja danych</span></a>
        <a href="<spring:url value="/customer/tickets/history"/>" class="list-group-item"><i class="fa fa-barcode"></i> <span>Zakupione bilety</span></a>
        <a href="#" class="list-group-item"><i class="fa fa-chart-line"></i> <span>Moje statystyki</span></a>
        <a href="<spring:url value="/customer/tickets/purchase"/>" class="list-group-item"><i class="fa fa-money-bill"></i> <span>Kup bilet</span></a>
    </div>
    <div class="flex-column panel-content bg-white border-bottom border-top border-left border-right shadow-sm">

        <h1 id="header">Edycja danych</h1>

        <%--@elvariable id="customerAccount" type="pl.polsl.pp.model.CustomerAccount"--%>
        <form:form method="post" class="mt-5" modelAttribute="customerAccount" action="/customer/data">
            <c:if test="${alertText != null}">
                <div class="alert alert-${alertType}">
                        ${alertText}
                    <button class="close" onclick="$(this).closest('.alert').slideUp(); return false;">&times;</button>
                </div>
            </c:if>

            <form:hidden path="id" name="id"/>
            <form:hidden path="isActive"/>
            <div class="form-group">
                <label>Nazwa użytkownika</label>
                <spring:bind path="username">
                    <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="username"/>
                </spring:bind>
                <form:errors path="username" class="invalid-feedback" />
            </div>
            <div class="form-group">
                <label>Hasło</label>
                <spring:bind path="password">
                    <input class="form-control ${status.error ? 'is-invalid' : ''}" name="password" type="password" readonly onfocus="this.removeAttribute('readonly');" <c:if test="${customerAccount.id != 0}"> placeholder="Uzupełnij, aby zmienić" </c:if>>
                </spring:bind>
                <form:errors path="password" class="invalid-feedback" />

            </div>
            <div class="form-group">
                <label>Imię</label>
                <spring:bind path="name">
                    <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="name"/>
                </spring:bind>
                <form:errors path="name" class="invalid-feedback" />
            </div>
            <div class="form-group">
                <label>Nazwisko</label>
                <spring:bind path="surname">
                    <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="surname"/>
                </spring:bind>
                <form:errors path="surname" class="invalid-feedback" />
            </div>
            <div class="form-group">
                <label>Adres e-mail</label>
                <spring:bind path="email">
                    <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="email"/>
                </spring:bind>
                <form:errors path="email" class="invalid-feedback" />
            </div>
            <div class="form-group">
                <label>Numer telefonu</label>
                <spring:bind path="phoneNumber">
                    <form:input class="form-control ${status.error ? 'is-invalid' : ''}" path="phoneNumber"/>
                </spring:bind>
                <form:errors path="phoneNumber" class="invalid-feedback" />
            <div class="text-right">
                <br><button type="submit" class="btn btn-primary">Zapisz</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>