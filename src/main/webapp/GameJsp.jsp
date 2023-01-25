<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.WebGaneevRM.data.GameData" %>
<%@ page import="com.example.WebGaneevRM.dto.GameDTO" %>
<%@ page import="java.util.LinkedList" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Добро пожаловать!</title>
    <link href="css/main.css" rel="stylesheet"/>
    <link href="css/modal.css" rel="stylesheet">
    <script src="js/modal.js"></script>
</head>
<body>
<header>
    <div class="header_content name_bar">Аренда настольных игр</div>
    <div class="header_content bar">
        <form method="post" action="RenterServlet">
            <button name="action" value="renterPage">Арендаторы</button>
        </form>
        <form method="post" action="LandlordServlet">
            <button name="action" value="landlordPage">Арендодатели</button>
        </form>
        <form method="post" action="ContractServlet">
            <button name="action" value="contractPage">Договоры</button>
        </form>
        <form method="post" action="GameServlet">
            <button name="action" value="gamePage">Игры</button>
        </form>
        <form method="post" action="ConsiderationServlet">
            <button name="action" value="considerationPage">Учёт</button>
        </form>
    </div>
</header>
<main class="main_block">
    <form method="post" action="GameServlet">
        <p style="margin-top: 15px; margin-bottom: 15px">Игры</p>
        <div class="table_block lightyellow">
            <table border="2">
                <thead>
                <tr>
                    <th><input class="checkbox" type="checkbox" onclick="onClickMainCheckBox(this)"></th>
                    <th>№</th>
                    <th>Название игры</th>
                    <th>Дата выпуска</th>
                    <th>Количество игроков</th>
                    <th>Сложность</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${games}" varStatus="counter">
                    <tr>
                        <td><input type="checkbox" name="listCounter" onclick="onClickSimpleCheckBox(this)" value="${counter.index}"></td>
                        <input type="hidden" name="listId" value="${item.getId_game()}">
                        <td class="id_col">${item.getId_game()}</td>
                        <td><input type="text" name="listName" class="input_table" required value="${item.getName_game()}"></td>
                        <td><input type="date" name="listDate" class="input_table" required value="${item.getRelease_date()}"></td>
                        <td><input type="number" name="listNumber" class="input_table" required value="${item.getNumber_players()}"></td>
                        <td><input type="text" name="listDifficulty" class="input_table" required value="${item.getDifficulty()}"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="main_block lightyellow">
            <button id="actionAdd" name="action" value="add" type="button">Добавить</button>
            <button disabled id="actionDelete" name="action" value="delete" type="submit">Удалить</button>
            <button disabled id="actionEdit" name="action" value="edit" type="submit">Сохранить</button>
        </div>
    </form>

    <script>
        var modal = $modal({
            title: 'Добавление новой записи',
            content: '<form method="post" action="GameServlet">' +
                '<p><b>Назване игры:</b><br>' +
                '<input required autofocus name="nameOk" type="text" pattern="^[А-Яа-яЁё\\s]+$" class="input_modal"></p>' +
                '<p><b>Дата выпуска:</b><br>' +
                '<input required name="dateOk" type="date" class="input_modal"></p>' +
                '<p><b>Количество игроков:</b><br>' +
                '<input required name="numberOk" type="number" class="input_modal"></p>' +
                '<p><b>Сложность:</b><br>' +
                '<input required name="difficultyOk" type="text" class="input_modal"></p>' +
                '<button id="okAdd" name="action" value="ok" type="submit">OK</button>' +
                '</form>'
        });
        document.querySelector('#actionAdd').addEventListener('click', function(e) {
            modal.show();
        });
    </script>

    <%
        String errorM = (String) request.getAttribute("error");
    %>
    <c:if test="${error!=null}">
        <script>
            var error="<%= errorM%>";
            alert(error);
        </script>
    </c:if>
</main>
<footer class="main_block">
    Разработчик: Ганеев Рустам
    <br>
    ganeevrm@yahoo.com
</footer>
<script src="js/main.js"></script>
</body>
</html>

