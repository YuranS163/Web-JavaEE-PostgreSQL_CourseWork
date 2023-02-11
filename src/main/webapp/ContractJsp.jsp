<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.WebSedyolkinYA.data.ConsiderationData" %>
<%@ page import="com.example.WebSedyolkinYA.dto.ConsiderationDTO" %>
<%@ page import="com.example.WebSedyolkinYA.data.GameData" %>
<%@ page import="com.example.WebSedyolkinYA.data.LandlordData" %>
<%@ page import="com.example.WebSedyolkinYA.data.RenterData" %>

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
    <form method="post" action="ContractServlet">
        <p style="margin-top: 15px; margin-bottom: 15px">Договоры</p>
        <div class="table_block lightyellow">
            <table border="2">
                <thead>
                <tr>
                    <th><input class="checkbox" type="checkbox" onclick="onClickMainCheckBox(this)"></th>
                    <th>№</th>
                    <th>Арендатор</th>
                    <th>Арендодатель</th>
                    <th>Игра</th>
                    <th>Дата заключения договора</th>
                    <th>Срок действия</th>
                    <th>Стоимость</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${contracts}" varStatus="counter">
                    <tr>
                        <td><input type="checkbox" name="listCounter" onclick="onClickSimpleCheckBox(this)" value="${counter.index}"></td>
                        <input type="hidden" name="listId" value="${item.getId_сontract()}">
                        <td class="id_col">${item.getId_сontract()}</td>
                        <td>
                            <select name="listRenter" required>
                                <c:forEach var="itemRenter" items="${RenterData.selectRenter()}">
                                    <option value="${itemRenter.getId_renter()}" ${item.getName_renter() eq itemRenter.getName_renter() ? 'selected' : ''}>${itemRenter.getName_renter()}</option>
                                </c:forEach>
                            </select></td>
                        <td>
                            <select name="listLandlord" required>
                                <c:forEach var="itemLandlord" items="${LandlordData.selectLandlord()}">
                                    <option value="${itemLandlord.getId_landlord()}" ${item.getName_landlord() eq itemLandlord.getName_landlord() ? 'selected' : ''}>${itemLandlord.getName_landlord()}</option>
                                </c:forEach>
                            </select></td>
                        <td>
                            <select name="listGame" required>
                                <c:forEach var="itemGame" items="${GameData.selectGame()}">
                                    <option value="${itemGame.getId_game()}" ${item.getName_game() eq itemGame.getName_game() ? 'selected' : ''}>${itemGame.getName_game()}</option>
                                </c:forEach>
                            </select></td>
                        <td><input type="date" name="listDate" class="input_table" required value="${item.getConclusion_date()}"></td>
                        <td><input type="date" name="listTimeLine" class="input_table" required value="${item.getTime_line()}"></td>
                        <td><input type="text" name="listAmount" class="input_table" required value="${item.getAmount()}"></td>
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
            content: '<form method="post" action="ContractServlet">' +
                '<p><b>Арендатор:</b><br>' +
                '<select name="renterOk" required>' +
                '<c:forEach var="itemRenter" items="${RenterData.selectRenter()}">' +
                '<option value="${itemRenter.getId_renter()}">${itemRenter.getName_renter()}</option>' +
                '</c:forEach> </select></p>' +
                '<p><b>Арендодатель:</b><br>' +
                '<select name="landlordOk" required>' +
                '<c:forEach var="itemLandlord" items="${LandlordData.selectLandlord()}">' +
                '<option value="${itemLandlord.getId_landlord()}">${itemLandlord.getName_landlord()}</option>' +
                '</c:forEach> </select></p>' +
                '<p><b>Игра:</b><br>' +
                '<select name="gameOk" required>' +
                '<c:forEach var="itemGame" items="${GameData.selectGame()}">' +
                '<option value="${itemGame.getId_game()}">${itemGame.getName_game()}</option>' +
                '</c:forEach></select></p>' +
                '<p><b>Дата заключения договора:</b><br>' +
                '<input required name="dateOk" type="date" class="input_modal"></p>' +
                '<p><b>Срок действия:</b><br>' +
                '<input required name="timeLineOk" type="date" class="input_modal"></p>' +
                '<p><b>Стоимость:</b><br>' +
                '<input required name="amountOk" type="text" class="input_modal"></p>' +
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
    Разработчик: Седёлкин Юрий
    <br>
    yurasedyolkin@gmail.com
</footer>
<script src="js/main.js"></script>
</body>
</html>
