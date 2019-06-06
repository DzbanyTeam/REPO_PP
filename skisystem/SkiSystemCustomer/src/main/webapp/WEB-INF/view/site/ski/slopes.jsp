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
                <a class="nav-link" href="<spring:url value="/lifts"/>">Wyciągi</a>
                <a class="nav-link active" href="<spring:url value="/slopes"/>">Stoki</a>
                <a class="nav-link" href="<spring:url value="/prices"/>">Cennik karnetów</a>
                <a class="nav-link" href="<spring:url value="/customer"/>">Strefa klienta</a>
            </nav>
            <h3 class="masthead-brand">SkiSystem</h3>
        </div>
    </header>

    <main class="bg-white rounded p-5">
        <h1>Stoki</h1>
        <c:forEach items="${slopes}" var="slope">
            <hr>
            <div id="slope-${slope.id}">
                <h3>${slope.name}</h3>
                <div class="row">
                    <div class="col-sm-4">
                        <p><b>Długość</b></p>
                        <p>${slope.length} m</p>
                    </div>
                    <div class="col-sm-4">
                        <p><b>Wysokość</b></p>
                        <p>${slope.startElevation} mnpm - ${slope.endElevation} mnpm</p>
                    </div>
                    <div class="col-sm-4">
                        <p><b>Trudność</b></p>
                        <p><c:if test="${slope.difficulty != null}"><span class="badge badge-primary badge-pill">${slope.difficulty.getName()}</span></c:if></p>
                    </div>
                </div>
                <c:if test="${slope.getAssociatedLifts().size() > 0}">
                    <p><b>Wyciągi prowadzące na stok</b></p>
                    <p>
                        <c:forEach items="${slope.getAssociatedLifts()}" var="lift">
                            <a href="<spring:url value="/lifts#lift-${lift.id}"/>" class="badge badge-secondary">${lift.name}</a>
                        </c:forEach>
                    </p>
                </c:if>
                <c:if test="${slope.getSlopeBusinessHours().size() > 0}">
                    <p><b>Stok czynny w godzinach:</b></p>
                    <table class="table table-sm" style="max-width:300px">
                        <tbody>
                        <c:forEach items="${slope.getSlopeBusinessHours()}" var="businessHours">
                            <tr>
                                <td>${businessHours.dayOfTheWeek.name}</td>
                                <td class="text-right">${businessHours.openingHourString} - ${businessHours.closingHourString}</td>
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
