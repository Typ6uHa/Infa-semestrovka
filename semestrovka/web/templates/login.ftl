<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body class="container">
<div class="login" style="margin:auto;width:30%;">
    <h3><strong>Войти</strong></h3>
    <form method="post">
        <div class="form-group">
            <input type="text" class="form-control" id="login" name="login" placeholder="Логин">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="password" name="password" placeholder="Пароль">
        </div>
        <div class="checkbox">
            <label><input type="checkbox" name="checkbox"> Запомнить меня</label>
        </div>
        <button type="submit" class="btn btn-default" id="submit" onclick="checkLogin()">Войти</button>
    </form>
    <br><br>
    <div id="err" style="color: red">
        <#if fail>
            Неверные логин или пароль
        </#if>
    </div>
    <script src="../js/login.js"></script>
    <br>
    <p>Нет аккаунта? <a href="/registration">Регистрация</a></p>
</div>
</body>
</html>