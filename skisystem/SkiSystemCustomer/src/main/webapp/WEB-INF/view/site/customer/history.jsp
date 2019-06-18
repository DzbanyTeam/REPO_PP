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
            height: auto;
        }
    </style>
</head>
<body class="bg-light">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-5 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">Strefa klienta SkiProject</h5>
    <a class="btn btn-default mr-3" href="<spring:url value="/customer/help"/>"><i class="fas fa-question-circle"></i> Pomoc</a>
    <a class="btn btn-default mr-3" href="<spring:url value="/customer/logout"/>"><i class="fas fa-sign-out-alt"></i> Wyloguj się</a>
    <a class="btn btn-secondary" href="<spring:url value="/"/>">Powrót do serwisu</a>
</div>
<div class="d-flex flex-md-row">
    <div class="list-group flex-column ">
        <a href="<spring:url value="/customer"/>" class="list-group-item"><i class="fa fa-home"></i> <span>Podgląd</span></a>
        <a href="<spring:url value="/customer/data"/>" class="list-group-item"><i class="fa fa-address-card"></i> <span>Edycja danych</span></a>
        <a href="<spring:url value="/customer/tickets/history"/>" class="list-group-item active"><i class="fa fa-barcode"></i> <span>Zakupione bilety</span></a>
        <a href="<spring:url value="/customer/tickets/purchase"/>" class="list-group-item"><i class="fa fa-money-bill"></i> <span>Kup bilet</span></a>
    </div>
    <div class="flex-column panel-content bg-white border-bottom border-top border-left border-right shadow-sm">
        <h1 id="header">Historia biletów</h1>
        <br>
        <c:if test="${alertText != null}">
            <div class="alert alert-${alertType}">
                    ${alertText}
                <button class="close" onclick="$(this).closest('.alert').slideUp(); return false;">&times;</button>
            </div>
        </c:if>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Bilet</th>
                <th>Data zakupu</th>
                <th>Aktywny</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tickets}" var="purchasedTicket">
                <tr>
                    <td>${purchasedTicket.getPrice().getTicketType().getName()} ${purchasedTicket.getPrice().getTicketCategory().getName()}</td>
                    <td>${purchasedTicket.purchaseDatetimeString}</td>
                    <c:if test="${purchasedTicket.getIsActive()}">
                        <td><a class="text-success"><i class="fas fa-check text-success"></i></a></td>
                    </c:if>
                    <c:if test="${!purchasedTicket.getIsActive()}">
                        <td><a class="text-success"><i class="fas fa-times text-danger"></i></a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>