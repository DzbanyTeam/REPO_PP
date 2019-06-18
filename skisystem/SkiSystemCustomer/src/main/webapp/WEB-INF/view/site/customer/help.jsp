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
        [data-toggle="collapse"]:after {
            display: inline-block;
            display: inline-block;
            font-weight: bold;
            font-size: inherit;
            text-rendering: auto;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            content: "V";
            transform: rotate(180deg) ;
            transition: all linear 0.25s;
            float: right;
        }
        [data-toggle="collapse"].collapsed:after {
            transform: rotate(0deg) ;
        }
        b {
            color:#007bff;
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
        <a href="<spring:url value="/customer/tickets/history"/>" class="list-group-item"><i class="fa fa-barcode"></i> <span>Zakupione bilety</span></a>
        <a href="<spring:url value="/customer/tickets/purchase"/>" class="list-group-item"><i class="fa fa-money-bill"></i> <span>Kup bilet</span></a>
    </div>
    <div class="flex-column panel-content bg-white border-bottom border-top border-left border-right shadow-sm">
        <h1 id="header">Pomoc</h1>
        <div id="accordion" role="tablist">
            <div class="card">
                <div class="card-header" role="tab" id="headingOne">
                    <h5 class="mb-0">
                        <a class="collapsed" data-toggle="collapse" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                            Edycja danych osobowych
                        </a>
                    </h5>
                </div>
                <div id="collapseOne" class="collapse" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        <h5>Aby zmienić swoje dane osobowe: <br></h5>
                        <b>1. </b> Udaj się do zakładki <b><i class="fa fa-address-card"></i> Edycja danych</b>.<br>
                        <b>2. </b> W polach formuarza zobaczysz swoje dotychczasowe dane. <br>
                        <b>3. </b> Zmień wartości w wybranych polach i klinij przycisk <b>Zapisz</b>.<br>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" role="tab" id="headingTwo">
                    <h5 class="mb-0">
                        <a class="collapsed" data-toggle="collapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            Zakup biletu
                        </a>
                    </h5>
                </div>
                <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <h5>Aby zakupić bilet w naszym serwisie: <br></h5>
                        <b>1. </b> Udaj się do zakładki <b><i class="fa fa-money-bill"></i> Kup Bilet</b>.<br>
                        <b>2. </b> Z rozwijanej listy wybierz bilet, który chcesz kupić. <br>
                        <b>3. </b> Po wybraniu odpowiedniego biletu klinij przycisk <b>Kup</b>.<br>
                        <b>4. </b> Zostaniesz przekierowany na stronę PayPal, gdzie postępując zgodnie ze wskazówkami dokonasz płatności za bilet. <br>
                        <b>5. </b> Po poprawnie dokonanej płatności, zostaniesz przekierowany do zakładki <b><i class="fa fa-barcode"></i> Zakupione bilety</b>, gdzie zobaczysz historię zakupionych biletów. <br>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" role="tab" id="headingThree">
                    <h5 class="mb-0">
                        <a class="collapsed" data-toggle="collapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            Podgląd zakupionych biletów
                        </a>
                    </h5>
                </div>
                <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree" data-parent="#accordion">
                    <div class="card-body">
                        <h5>Aby zobaczyć bilety zakupione w naszym serwisie: <br></h5>
                        <b>1. </b> Udaj się do zakładki <b><i class="fa fa-barcode"></i> Zakupione bilety</b>.<br>
                        <b>2. </b> W tabeli zobaczysz wszystkie dotychczas zakupione bilety.<br>
                        W polu aktywny:<br>
                        <i class="fas fa-check text-success"></i> Oznacza bilet, który został poprawnie zakupiony.<br>
                        <i class="fas fa-times text-danger"></i> Oznacza bilet, za którego płatność nie przebiegła pomyślnie.<br>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" role="tab" id="headingFour">
                    <h5 class="mb-0">
                        <a class="collapsed" data-toggle="collapse" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                            Nieudana płatność za bilet
                        </a>
                    </h5>
                </div>
                <div id="collapseFour" class="collapse" role="tabpanel" aria-labelledby="headingFour" data-parent="#accordion">
                    <div class="card-body">
                        <h5>W przypadku, gdy płatność za bilet nie powiedzie się:<br></h5>
                        <b>1. </b> W zakładce <b><i class="fa fa-barcode"></i> Zakupione bilety</b> pojawi się bilet ze znakiem <i class="fas fa-times text-danger"></i> w polu aktywny.<br>
                        <b>2. </b> Z Twojego konta nie zostaną pobrane żadne środki.<br>
                        <b>3. </b> Możesz ponownie spróbować zakupić bilet w zakładce <b><i class="fa fa-money-bill"></i> Kup Bilet</b>.<br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>