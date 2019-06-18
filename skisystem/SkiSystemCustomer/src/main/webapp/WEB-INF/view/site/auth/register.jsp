<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Strefa klienta SkiSystem - rejestracja</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/site/css/customer.css"/>">
    <script src="<spring:url value="/resources/common/js/jquery.js"/>"></script>
</head>
<body class="bg-light">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-5 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">Strefa klienta SkiProject</h5>
    <a class="btn btn-secondary" href="<spring:url value="/"/>">Powrót do serwisu</a>
</div>
<div class="container">
        <h2>Rejestracja</h2>
            <c:if test="${alertText != null}">
                <div class="alert alert-${alertType}">
                        ${alertText}
                    <button class="close" onclick="$(this).closest('.alert').slideUp(); return false;">&times;</button>
                </div>
            </c:if>
            <form:form method="post" action="/customer/register">
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Nazwa użytkownika</label>
                        <input class="form-control" name="username">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Hasło</label>
                        <input class="form-control" name="password" type="password">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Powtórz hasło</label>
                        <input class="form-control" name="password-repeat" type="password">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2 col-6">
                    <div class="form-group">
                        <label>Imię</label>
                        <input class="form-control" name="name">
                    </div>
                </div>
                <div class="col-md-2 col-6">
                    <div class="form-group">
                        <label>Nazwisko</label>
                        <input class="form-control" name="surname">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Adres e-mail</label>
                        <input class="form-control" name="email">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Numer telefonu</label>
                        <input class="form-control" name="phoneNumber">
                    </div>
                </div>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="checkbox" name="isActive" class="form-check-input">
                    Wyrażam zgodę na przetwarzanie moich danych osobowych przez SkiProject w celu realizacji zamówień, przetwarzania karnetów i generowania danych statystycznych.
                </label>
            </div>
            <div class="mt-3">
                <a href="<spring:url value="/customer/login"/>" class="btn btn-outline-primary">Powrót do logowania</a>
                <button type="submit" class="btn float-right btn-primary">Załóż konto</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
