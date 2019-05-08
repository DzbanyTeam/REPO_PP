<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Strefa klienta SkiSystem - logowanie</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/site/css/customer.css"/>">
</head>
<body class="bg-light">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-5 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">Strefa klienta SkiProject</h5>
    <a class="btn btn-secondary" href="<spring:url value="/"/>">Powrót do serwisu</a>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-6 px-5">
            <h2 class="mb-4">Zaloguj się</h2>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    Podano błędne dane logowania.
                    <button class="close" onclick="$(this).closest('.alert').slideUp(); return false;">&times;</button>
                </div>
            </c:if>
            <form:form method="post" action="/customer/login">
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <label>Nazwa użytkownika</label>
                            <input class="form-control" name="username">
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label>Hasło</label>
                            <input class="form-control" name="password" type="password">
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-lg btn-block btn-primary">Zaloguj się</button>
            </form:form>
        </div>
        <div class="col-sm-6 px-5">
            <h2 class="mb-3">Nie posiadasz konta?</h2>
            <p class="lead">Załóż konto, aby zamawiać karnety online i przeglądać statystyki swoich zjazdów.</p>
            <a href="<spring:url value="/customer/register"/>" class="btn btn-lg btn-block btn-outline-primary">Przejdź do formularza rejestracji</a>
        </div>
    </div>
</div>

<script src="<spring:url value="/resources/common/js/jquery.js"/>"></script>

</body>
</html>
