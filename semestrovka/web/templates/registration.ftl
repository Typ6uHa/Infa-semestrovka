<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body class="container">
<div class="register" style="margin:auto;width:30%;">
    <h3><strong>Регистрация</strong></h3>
    <form method="post" onsubmit="check()">
        <div class="form-group">
            <input type="text" class="form-control" name="nickname" id="nickname" placeholder="Имя на сайте">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="name" id="name" placeholder="Имя">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="surname" id="surname" placeholder="Фамилия">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="city" id="city" placeholder="Город">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="login" id="login" placeholder="Логин">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" id="password" placeholder="Пароль">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password2" id="password2" placeholder="Подтверждение пароля">
        </div>
        <div id="err" style="color: red">
        <#if fail>
            <#if empty>Все поля должны быть заполнены</#if>
            <#if login_letters>В логине должны быть только латинские буквы</#if>
            <#if login_length>В логине должно быть от 4 до 16 символов</#if>
            <#if nick_letters>В никнейме должны быть только латинские буквы</#if>
            <#if nick_length>В никнейме должно быть от 4 до 16 символов</#if>
            <#if rep_password>Повторный пароль введен неверно</#if>
            <#if password>Пароль должен содержать одну строчную, одну заглавную буквы, цифру и быть длиной не менее 4 символов и не более 16</#if>
            <#if login_nick>Данный логин или никнейм уже заняты</#if>
        </#if>
        </div>
        <br>
        <button type="submit" id="submit" class="btn btn-default">Зарегистрироваться</button>
    </form>
    <script src="../js/registration.js"></script>
</div>
</body>
</html>