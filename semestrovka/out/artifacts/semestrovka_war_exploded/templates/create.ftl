<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/icon.png">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../js/starHandler.js"></script>

    <title>Upload</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>

<body>

<#include "navbar.ftl" parse=true>

<br><br>
<div class="container-fluid">
    <div class="row justify-content-end">
        <div class="col-lg-8 col-lg-offset-2">

            <form method="post" class="well" enctype="multipart/form-data">
                <h2><strong>
                    <input type="text" placeholder="Название" name="title">
                </strong></h2>
                <br>
                <img src="work.jpg" alt="Изображение" width="830" height="467" id="image-show">
                <br><br>
                <input type="file" id="image" name="image">
                <br>
                <textarea rows="10" style="width:100%" name="description" placeholder="Описание"></textarea>
                <div style="text-align:right">
                    <input type="submit" class="btn btn-primary" value="Опубликовать">
                </div>
            </form>
            <#if fail>
                <div id="err" style="color: red">
                <#if empty>Все поля должны быть заполнены</#if>
                <#if no_photo>Вы не добавили фото</#if>
                </div>
            </#if>
        </div>
    </div>
</div>
<script>
    document.getElementById('image').addEventListener('change', handleFileSelect, false);
    function handleFileSelect(evt) {
        var files = evt.target.files;
        var file = files[0];
        if (file.type.match('image.*')) {
            var reader = new FileReader();
            reader.onload = (function() {
                return function(e) {
                    document.getElementById('image-show').src = e.target.result;
                };
            })(file);
            reader.readAsDataURL(file);
        }
    }
</script>
</body>
</html>