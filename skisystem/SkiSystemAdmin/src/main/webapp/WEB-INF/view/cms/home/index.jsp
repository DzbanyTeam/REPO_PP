<%--
  Created by IntelliJ IDEA.
  User: Przemek-PC
  Date: 24.03.2019
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>SkiSystem - panel administracyjny</title>
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/common/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/cms/css/style.css"/>">
</head>

<body>
<nav class="navbar navbar-expand bg-light">
    <a class="navbar-brand" href="<spring:url value="/admin"/>">SkiSystem</a>

    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="<spring:url value="/admin/admins"/>">Administratorzy</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<spring:url value="/admin/customers"/>">Klienci</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<spring:url value="/admin/slopes"/>">Stoki</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<spring:url value="/admin/lifts"/>">Wyciągi</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Cennik</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="<spring:url value="/admin/ticket-types"/>">Rodzaje biletów</a>
                <a class="dropdown-item" href="<spring:url value="/admin/ticket-categories"/>">Kategorie cenowe</a>
                <a class="dropdown-item" href="<spring:url value="/admin/seasons"/>">Sezony</a>
                <a class="dropdown-item" href="<spring:url value="/admin/prices"/>">Ceny</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Konfiguracja</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="<spring:url value="/admin/difficulties"/>">Poziomy trudności stoków</a>
                <a class="dropdown-item" href="<spring:url value="/admin/days-of-the-week"/>">Dni tygodnia</a>
            </div>
        </li>
    </ul>

    <a href="<spring:url value="/admin/logout"/>" class="navbar-brand" id="logout-button">
        <i class="fas fa-sign-out-alt"></i>
    </a>
</nav>


<div class="container py-5">
    <h1 id="header">Witaj w panelu administracyjnym</h1>
    <p>Panel administracyjny umożliwia modyfikację treści zawartych w witrynie, wykonywanie czynności administracyjnych na administratorach i klientach, a także zarządzanie zakupionymi przez klientów biletami.</p>
    <p>Powyżej znajduje się menu wyboru modułu. Każdy moduł umożliwia zarządzanie jednym aspektem systemu (np administracja użytkownikami, edycja widocznych w serwisie stoków itp). Każdy moduł ma schematyczną strukturę składającą się z widoku listy elementów oraz widoku dodawania/edycji elementu. Dodatkowa pomoc dotycząca konkretnego modułu znajduje się po wejściu w niego.</p>
    <p>Dodatkowo po lewej stronie nagłówka znajduje się napis "SkiSystem" przekierowujący na stronę główną panelu administracyjnego, którą właśnie czytasz, a po prawej stronie - ikona <i class="fas fa-sign-out-alt"></i>, której wciśnięcie wylogowuje aktualnie zalogowanego administratora.</p>
    <h3>Obsługa modułów</h3>
    <p>Po wejściu w każdy moduł ukaże się lista wszystkich elementów modułu, przedstawiona w formie tabelarycznej. Z poziomu listy możliwe są podstawowe akcje administracyjne, takie, jak usuwanie elementów, czy przejście dodawania nowego elementu lub edycji istniejącego. Większość modułów umożliwia ponadto aktywowanie, lub deaktywowanie elementu, co w zależności od modułu może nieść nieznacznie różne skutki, jednak ogólnie służy to temu, aby w serwisie nie było śladów istnienia elementu, ale żeby pozostał w panelu administracyjnym do ewentualnej edycji i/lub reaktywacji.</p>
    <p>Oprócz wykonywania operacji na pojedynczym elemencie jest wykonanie pewnych czynności na wielu elementach jednocześnie, np usunięcie ich, co odbywa się poprzez wybranie ich za pomocą przycisków wyboru w pierwszej kolumnie tabeli i wybranie czynności z listy wyboru poniżej tabeli.</p>
    <p>We wszystkich modułach edytując/dodając elementy wymagane jest wypełnienie wszystkich pól.</p>
    <h3>Układ widoku listy</h3>
    <div class="card mb-2">
        <div class="card-body">
            <div class="position-relative">
                <span class="badge badge-danger badge-tooltip-right" style="top: 15px; right: -15px">1</span>
                <a class="btn btn-lg btn-light float-right ml-3"><i class="far fa-question-circle"></i></a>
                <h1>Nazwa modułu</h1>
            </div>
            <table class="table mt-3">
                <thead class="thead-dark">
                <tr>
                    <th></th>
                    <th>Nagłówek kolumny</th>
                    <th>Aktywny</th>
                    <th class="text-right">Akcje</th>
                </tr>
                </thead>
                <tbody>

                <tr>
                    <td><div class="position-relative"><span class="badge badge-danger badge-tooltip-left" style="top: 1px;left: -20px;">2</span><input type="checkbox"></div></td>
                    <td>Wartość elementu</td>
                    <td><div class="position-relative"><span class="badge badge-danger badge-tooltip-left" style="top: 3px;left: -20px;">3</span><a title="Dezaktywuj"><i class="fas fa-check text-success"></i></a></div></td>
                    <td class="text-right py-2">
                        <div class="position-relative">

                            <div class="btn-group">
                                <a class="btn btn-primary fas fa-pen" href="" onclick="return false;"></a>
                                <a class="btn btn-light fas fa-trash-alt"></a>
                            </div>
                            <span class="badge badge-danger badge-tooltip-right" style="top: 6px;right: -20px;">4</span></div>
                    </td>
                </tr>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>Wartość elementu</td>
                    <td><a title="Aktywuj"><i class="fas fa-times text-danger"></i></a></td>
                    <td class="text-right py-2">
                        <div class="btn-group">
                            <a class="btn btn-primary fas fa-pen" href="" onclick="return false;"></a>
                            <a class="btn btn-light fas fa-trash-alt"></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>Wartość elementu</td>
                    <td><a title="Dezaktywuj"><i class="fas fa-check text-success"></i></a></td>
                    <td class="text-right py-2">
                        <div class="btn-group">
                            <a class="btn btn-primary fas fa-pen" href="" onclick="return false;"></a>
                            <a class="btn btn-light fas fa-trash-alt"></a>
                        </div>
                    </td>
                </tr>

                </tbody>
            </table>
            <div class="position-relative">
                <a class="btn btn-primary float-right" href="" onclick="return false;">Dodaj element <i class="fas fa-plus"></i></a>
                <span class="badge badge-danger badge-tooltip-right" style="top: 10px;right: -15px;">6</span>
                <span class="badge badge-danger badge-tooltip-left" style="top: 10px;left: -15px;">5</span>
                <select class="form-control" onchange="$(this).val('');" style="width:auto">
                    <option value="" disabled="" selected="">Masowa edycja</option>
                    <option value="delete">Usuń zaznaczone</option>
                    <option value="activate">Aktywuj zaznaczone</option>
                    <option value="deactivate">Dezaktywuj zaznaczone</option>
                </select>
            </div>
        </div>
    </div>
    <ol>
        <li>Przycisk pomocy; jego wciśnięcie ukazuje dodatkową pomoc dotyczącą danego modułu</li>
        <li>Przycisk wyboru umożliwiający wybór elementów, na których chce się wykonać masową akcję</li>
        <li>Piktogram informujący, czy dany element element jest aktywny, czy nie; dodatkowo jeśli jest to <i class="fas fa-check text-success"></i>, wciśnięcie piktogramu natychmiast deaktywuje dany element, a jeśli <i class="fas fa-times text-danger"></i>, wciśnięcie go aktywuje element</li>
        <li>Przyciski służące odpowiednio do:
            <ul>
                <li><i class="fas fa-pen"></i> - przejścia do szczegółowej edycji danego elementu,</li>
                <li><i class="fas fa-trash-alt"></i> - usunięcia danego elementu</li>
            </ul>
        </li>
        <li>Lista wyboru umożliwiająca wybranie akcji do wykonania na wielu elementach</li>
        <li>Przycisk umożliwiający przejście do dodawania nowego elementu</li>
    </ol>
</div>

<script src="<spring:url value="/resources/common/js/jquery.js"/>"></script>
<script src="<spring:url value="/resources/common/js/bootstrap.bundle.min.js"/>"></script>

</body>
</html>


