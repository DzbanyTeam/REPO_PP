<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>SkiSystem</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/site/css/home.css"/>">
</head>
<body class="home">
<div class="container">
    <header class="masthead">
        <div class="inner">
            <nav class="nav nav-masthead justify-content-center">
                <a class="nav-link active" href="<spring:url value="/"/>">Strona główna</a>
                <a class="nav-link" href="<spring:url value="/lifts"/>">Wyciągi</a>
                <a class="nav-link" href="<spring:url value="/slopes"/>">Stoki</a>
                <a class="nav-link" href="<spring:url value="/prices"/>">Cennik karnetów</a>
                <a class="nav-link" href="<spring:url value="/customer"/>">Strefa klienta</a>
            </nav>
            <h3 class="masthead-brand">SkiSystem</h3>
        </div>
    </header>

    <main class="cover">
        <h1 class="cover-heading">SkiSystem</h1>
        <p class="lead">Tekst zachęcający potencjalnych klientów do zapoznania się z ofertą i odwiedzenia kompleksu narciarskiego</p>
        <p>
            <a href="<spring:url value="/customer/tickets/purchase"/>" class="btn btn-lg btn-secondary">Kup karnet online</a>
        </p>
    </main>
</div>
</body>
</html>
