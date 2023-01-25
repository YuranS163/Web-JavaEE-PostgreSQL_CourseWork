<%@ page import="com.example.WebGaneevRM.data.RenterData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Ошибка</title>
        <link href="css/main.css" rel="stylesheet"/>
    </head>
    <body>
    <header>
        <div class="header_content name_bar">Ошибка</div>
    </header>
    <main class="main_block">
        <h3>Что-то пошло не так, но мы уже решаем эту проблему</h3>
        <form method="post" action="RenterServlet"
              style="margin-top: 10px;
              margin-left: auto;
              margin-right: auto;">
            <button id="actionError" name="action" value="errorPage" type="submit">Перейти на главную страницу</button>
        </form>
    </main>
    <footer class="main_block">
        Разработчик: Ганеев Рустам
        <br>
        ganeevrm@yahoo.com
    </footer>
    </body>
</html>
