<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/icon.png">

    <title>Search</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../js/starHandler.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>

<body>

<#include "navbar.ftl" parse=true>

<br>
<div class="container-fluid">
    <div class="row justify-content-end">
        <div class="col-lg-8 col-lg-offset-2">
            <div class="row-fluid">
                <form method="get">
                    <div class="input-group">
                        <input type="text" class="form-control" name="q" id="querry">
                        <span class="input-group-btn">
				            <button class="btn btn-default" type="submit">Поиск</button>
				        </span>
                    </div>
                </form>
            </div>
        </div>
        <br><br>
        <hr>

        <#if nicknames?has_content || themes?has_content>
        <div class="col-lg-6 col-lg-offset-3">
            <div class="row" style="margin-left:0px"><font size="+1">
                <p>Искать среди:</p>
                <label><input type="checkbox"> Пользователей </label>
                <label><input type="checkbox"> Работ </label>
            </font></div>
        </div>
            <#if nicknames?has_content>
                <div class="col-lg-6 col-lg-offset-3 nicknames" style="margin-top:30px" id="users_view">
                    <#list 0..nicknames?size-1 as i>
                        <div class="row well">
                            <div class="col-lg-2">
                                <img src="${photos[i]}" width="128" height="128" alt="avatar">
                            </div>
                            <div class="col-lg-4 col-lg-offset-2">
                                <font size="+2"><p>${nicknames[i]}<br>${names[i]} ${surnames[i]}<br>${cities[i]}</p></font>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </#list>
                </div>
                <br><br>
                <hr>
            </#if>

            <#if themes?has_content>
                <div class="col-lg-8 col-lg-offset-2 topics" style="margin-top:30px" id="topics_view">
                    <br>
                    <#list 0..themes?size-1 as i>
                        <div class="well">
                            <div class="pull-right">
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
                </div>
            </#if>
        <#else>
            <p align="center">
                К сожалению, по вашему запросу ничего не найдено
            </p>
        </#if>
    </div>
</body>
</html>
