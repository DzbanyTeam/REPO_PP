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

        <c:forEach items="${lifts}" var="lift">
            <hr>
            <div id="slope-${lift.id}">
                <h3>${lift.name}</h3>
                <div class="row">
                    <div class="col-sm-6">
                        <p><b>Długość</b></p>
                        <p>${lift.length} m</p>
                    </div>
                    <div class="col-sm-6">
                        <p><b>Wysokość</b></p>
                        <p>${lift.startElevation} mnpm - ${lift.endElevation} mnpm</p>
                    </div>
                </div>
                <c:if test="${lift.getAssociatedSlopes().size() > 0}">
                    <p><b>Stoki, na które prowadzi wyciąg</b></p>
                    <p>
                        <c:forEach items="${lift.getAssociatedSlopes()}" var="slope">
                            <a href="<spring:url value="/slopes#slope-${slope.id}"/>" class="badge badge-secondary">${slope.name}</a>
                        </c:forEach>
                    </p>
                </c:if>
                <c:if test="${lift.getLiftBusinessHours().size() > 0}">
                    <p><b>Wyciąg czynny w godzinach:</b></p>
                    <table class="table table-sm">
                        <tbody>
                        <c:forEach items="${lift.getLiftBusinessHours()}" var="businessHours">
                            <tr>
                                <td>${businessHours.dayOfTheWeek.name}</td>
                                <td>${businessHours.openingHour} - ${businessHours.closingHour}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </c:forEach>
    </main>
</div>
</body>
</html>
