<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        }
    </style>
    <script language="JavaScript">
        $(document).ready(function() {
            $('.list-group-item').click(function(e) {
                e.preventDefault();
                $('.list-group-item').removeClass('active');
                $(this).addClass('active');
            });
        });
    </script>
</head>
<body class="bg-light">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-5 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">Strefa klienta SkiProject</h5>
    <a class="btn btn-default mr-3" href="<spring:url value="/customer/logout"/>"><i class="fas fa-sign-out-alt"></i> Wyloguj się</a>
    <a class="btn btn-secondary" href="<spring:url value="/"/>">Powrót do serwisu</a>
</div>
<div class="d-flex flex-md-row">
    <div class="list-group flex-column ">
        <a href="#" class="list-group-item active"><i class="fa fa-home"></i> <span>Podgląd</span></a>
        <a href="#" class="list-group-item"><i class="fa fa-address-card"></i> <span>Edycja danych</span></a>
        <a href="#" class="list-group-item"><i class="fa fa-barcode"></i> <span>Zakupione bilety</span></a>
        <a href="#" class="list-group-item"><i class="fa fa-chart-line"></i> <span>Moje statystyki</span></a>
    </div>
    <div class="flex-column panel-content bg-white border-bottom border-top border-left border-right shadow-sm">
        Panel klienta
    </div>
</body>
</html>