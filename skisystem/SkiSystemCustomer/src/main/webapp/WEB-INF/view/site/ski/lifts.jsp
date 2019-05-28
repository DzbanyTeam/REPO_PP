<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>SkiSystem</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/site/css/home.css"/>">
</head>
<body>
<div class="container">
    <header class="masthead">
        <div class="inner">
            <nav class="nav nav-masthead justify-content-center">
                <a class="nav-link" href="<spring:url value="/"/>">Strona główna</a>
                <a class="nav-link active" href="<spring:url value="/lifts"/>">Wyciągi</a>
                <a class="nav-link" href="<spring:url value="/slopes"/>">Stoki</a>
                <a class="nav-link" href="<spring:url value="/prices"/>">Cennik karnetów</a>
                <a class="nav-link" href="<spring:url value="/customer"/>">Strefa klienta</a>
            </nav>
            <h3 class="masthead-brand">SkiSystem</h3>
        </div>
    </header>

    <main class="bg-white rounded p-5">
        <h1>Wyciągi</h1>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Nazwa</th>
                <th scope="col">Dystans</th>
                <th scope="col">Wysokość</th>
                <th scope="col">Stoki, na które wiedzie wyciąg</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${lifts}" var="lift">
                <tr id="lift-${lift.id}">
                    <td>${lift.name}</td>
                    <td>${lift.length} m</td>
                    <td>${lift.startElevation} mnpm - ${lift.endElevation} mnpm</td>
                    <td>
                        <c:forEach items="${lift.getAssociatedSlopes()}" var="slope">
                            <a href="<spring:url value="/slopes#slope-${slope.id}"/>" class="badge badge-secondary">${slope.name}</a>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
</div>
</body>
</html>
