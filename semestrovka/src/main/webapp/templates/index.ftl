<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/icon.png">
    <script src="../js/jquery-3.2.1.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../js/starHandler.js"></script>
    <script src="../js/adminDeletePost.js"></script>

<#if current_url == "/">
    <title>Home</title>
<#elseif current_url = "/favorite">
    <title>Favorites</title>
</#if>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet">
</head>

<body>

<#include "navbar.ftl" parse=true>

<br>
<div class="container-fluid">
    <div class="row justify-content-end">
        <div class="col-lg-8 col-lg-offset-2">
        <#if topics?has_content>
            <ul class="nav nav-pills" role="tablist">
                <#if sort = "new">
                    <li role="presentation" class="active"><a href="?sort=new">Свежие</a></li>
                    <li role="presentation"><a href="?sort=popular">Популярные</a></li>
                <#else>
                    <li role="presentation"><a href="?sort=new">Свежие</a></li>
                    <li role="presentation" class="active"><a href="?sort=popular">Популярные</a></li>
                </#if>
            </ul>
            <br>
            <#list 0..topics?size-1 as i>
                <div class="well">
                    <div class="pull-right">
                        <font size="5">
                            <#if !login>
                                <#if favorite[i]>
                                    <span class="glyphicon glyphicon-star" id="id" value="${id[i]}"></span>
                                <#else>
                                    <span class="glyphicon glyphicon-star-empty" id="id" value="${id[i]}"></span>
                                </#if>
                                <#if user_role = 1>
                                    <span class="glyphicon glyphicon-remove" id="id" value="${id[i]}"></span>
                                </#if>
                            </#if>
                        </font>
                    </div>
                    <div class="pull-left">
                        <h2><a style="color: black; text-decoration: none;" href="/post?id=${id[i]}"><strong>${themes[i]}</strong></a></h2>
                    </div>
                    <div class="clearfix"></div>
                    <br>
                    <#if photoUrl[i]??>
                        <img src="${photoUrl[i]}" width="830" height="467">
                    </#if>
                    <br><br>
                    <p>
                        <span class="glyphicon glyphicon-thumbs-up"></span>
                        <span class="badge">${likes[i]}</span>
                        <span class="glyphicon glyphicon-thumbs-down"></span>
                        <span class="badge">${dislikes[i]}</span>
                    </p>
                    <br>
                    <p>${description[i]}</p>
                </div>
            </#list>
        <#else>
        <p align="center">
            Кажется, постов пока что нет :С
        </p>
        </#if>
        </div>
    </div>
</div>
<div class="text-center">
<#if sort = "new">
    <#if page = 1 && 3 <= max_pages >
        <strong><a href="?page=1">1</a></strong>
        <a href="?page=2">2</a>
        <a href="?page=3">3</a>
    <#elseif page = 1 && max_pages = 2>
        <strong><a href="?page=1">1</a></strong>
        <a href="?page=2">2</a>
    <#elseif page = 1 && max_pages = 1>
        <strong><a href="?page=1">1</a></strong>
    <#elseif page + 1 <= max_pages>
        <a href="?page=${page - 1}">${page - 1}</a>
        <strong><a href="?page=${page}">${page}</a></strong>
        <a href="?page=${page + 1}">${page + 1}</a>
    <#elseif page = max_pages>
        <a href="?page=${page - 1}">${page - 1}</a>
        <strong><a href="?page=${page}">${page}</a></strong>
    </#if>
<#else>
    <#if page = 1 && 3 <= max_pages >
        <strong><a href="?sort=popular&page=1">1</a></strong>
        <a href="?sort=popular&page=2">2</a>
        <a href="?sort=popular&page=3">3</a>
    <#elseif page = 1 && max_pages = 2>
        <strong><a href="?sort=popular&page=1">1</a></strong>
        <a href="?sort=popular&page=2">2</a>
    <#elseif page = 1 && max_pages = 1>
        <strong><a href="?sort=popular&page=1">1</a></strong>
    <#elseif page + 1 <= max_pages>
        <a href="?sort=popular&page=${page - 1}">${page - 1}</a>
        <strong><a href="?sort=popular&page=${page}">${page}</a></strong>
        <a href="?sort=popular&page=${page + 1}">${page + 1}</a>
    <#elseif page = max_pages>
        <a href="?sort=popular&page=${page - 1}">${page - 1}</a>
        <strong><a href="?sort=popular&page=${page}">${page}</a></strong>
    </#if>
</#if>

<br>
</div>
</body>
</html>
