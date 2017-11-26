<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/icon.png">

    <title>Profile</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>

<body>
<#include "navbar.ftl" parse=true>
<form class="form-group" method="post" enctype="multipart/form-data">
    <h3>Username</h3>
    <input type="text" class="form-group" name="username" value="${username}">
    <h3>Name</h3>
    <input type="text" class="form-group" name="name" value="${name}">
    <h3>Surname</h3>
    <input type="text" class="form-group" name="surname" value="${surname}">
    <h3>City</h3>
    <input type="text" class="form-group" name="city" value="${city}">
    <h3>Profile image</h3>
    <input type="file" name="image" class="form-control">
    <button type="submit" class="btn btn-primary">Сохранить</button>
</form>
<#if photo??>
    <img src="${photo}"/>
</#if>
<#if fail>
<div id="err" style="color: red">
    <#if empty>Все поля должны быть заполнены</#if>
    <#if nick_letters>В никнейме должны быть только латинские буквы</#if>
    <#if nick_length>В никнейме должно быть от 4 до 16 символов</#if>
    <#if login_nick>Данный никнейм уже занят</#if>
</div>
</#if>
</body>
</html>
