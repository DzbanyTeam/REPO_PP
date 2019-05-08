<%--
  Created by IntelliJ IDEA.
  User: Przemek-PC
  Date: 24.03.2019
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>SkiSystem - panel administracyjny - logowanie</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/cms/css/style.css"/>">
</head>

<body class="bg-light">
<div class="container">
    <div class="row">
        <div class="col-lg-4 offset-lg-4">
            <div class="login">
                <div class="text-center">
                    <h1 id="header">SkiSystem</h1>
                </div>

                <div class="text-center">
                    <p class="lead mb-5">Logowanie do panelu administracyjnego</p>
                </div>

                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        Podano błędne dane logowania.
                        <button class="close" onclick="$(this).closest('.alert').slideUp(); return false;">&times;</button>
                    </div>
                </c:if>

                <form method="POST">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text"><i class="fas fa-user fa-fw"></i></div>
                            </div>
                            <input type="text" class="form-control" placeholder="Nazwa użytkownika" name="username">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text"><i class="fas fa-key fa-fw"></i></div>
                            </div>
                            <input type="password" class="form-control" placeholder="Hasło" name="password">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Zaloguj się</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="<spring:url value="/resources/common/js/jquery.js"/>"></script>

</body>
</html>


