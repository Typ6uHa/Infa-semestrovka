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
    <script src="../js/delete.js"></script>

    <title>${theme}</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>

<body>

<#include "navbar.ftl" parse=true>

<div class="container-fluid">
    <div class="row justify-content-end">
        <div class="col-lg-8 col-lg-offset-2">
            <div class="well row">
                <div class="pull-right">
                    <font size="5">
                    <#if !login>
                        <#if favorite>
                            <span class="glyphicon glyphicon-star" id="id" value="${id}"></span>
                        <#else>
                            <span class="glyphicon glyphicon-star-empty" id="id" value="${id}"></span>
                        </#if>
                    </#if>
                    </font>
                </div>
                <div class="pull-left">
                    <h4>${date}</h4>
                    <h2><strong>${theme}</strong></h2>
                </div>

                <div class="clearfix"></div>
                <br>
            <#if photoUrl??>
                <img src="${photoUrl}" width="830" height="467">
            </#if>
                <br><br>
                <p>
                    <font size="5"><span class="glyphicon glyphicon-thumbs-up"></span></font>
                    <span class="badge">${likes}</span>
                    <font size="5"><span class="glyphicon glyphicon-thumbs-down"></span></font>
                    <span class="badge">${dislikes}</span>
                </p><br>
                <div class="pull-right">
                </div>
                <p>${description}</p>
            </div>
        </div>
        <div class="col-lg-6 col-lg-offset-3">
            <div class="well row">
            <#if login>
                Зайдите на сайт, чтобы оставить комментарий
            <#else>
                <form method="post">
                    <div class="input-group commentary-input-field bottom-padding" style="width:100%;">
                        <textarea class="form-control" name="commentary" rows="3" placeholder="Написать коммент"
                                  required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary pull-right" style="margin:10px;">Отправить</button>
                </form>
            </#if>
            </div>

        <#if comments?has_content>
            <#list 0..comments?size-1 as i>
                <div class="well row comment${ids[i]}">
                    <#if deletable[i]>
                        <div class="pull-right">
                            <font size="4">
                                <span class="glyphicon glyphicon-remove" value="${ids[i]}"></span>
                            </font>
                        </div>
                    </#if>
                    <div class="col-lg-2">
                        <#if user_photos[i]??>
                            <img src="${user_photos[i]}" width="64" height="64" alt="avatar">
                        </#if>
                        <font size="+1" style="align:center;">${user_names[i]}</font>
                    </div>
                    <div class="col-lg-6 col-lg-offset-1">
                        <font size="+1"><p>${comments[i]}</p></font>
                    ${comment_date[i]}
                    </div>
                    <div class="clearfix"></div>
                </div>
            </#list>
        </#if>


        </div>
    </div>
</div>
</body>
</html>
