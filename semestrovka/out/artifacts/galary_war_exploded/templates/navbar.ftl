<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Gallery</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <#if current_url == "/">
                    <li class="active"><a href="/">Главная</a></li>
                    <#else>
                        <li><a href="/">Главная</a></li>
                </#if>
                <#if current_url == "/profile">
                    <li class="active"><a href="/profile">Профиль</a></li>
                    <#else>
                        <li><a href="/profile">Профиль</a></li>
                </#if>
                <#if current_url == "/create">
                    <li class="active"><a href="/create">Создать пост</a></li>
                    <#else>
                        <li><a href="/create">Создать пост</a></li>
                </#if>
                <#if current_url == "/favorite">
                    <li class="active"><a href="/favorite">Избранное</a></li>
                    <#else>
                        <li><a href="/favorite">Избранное</a></li>
                </#if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            <#if login>
                <li><a href="/registration"><span class="glyphicon glyphicon-user"></span> Регистрация</a></li>
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Вход</a></li>
            <#else>
                <li><a href="/logout"> Выход</a></li>
            </#if>
            </ul>
            <form class="navbar-form navbar-right" action="/search" method="get">
                <div class="form-group">
                    <input type="text" class="form-control" name="q" id="q" placeholder="">
                    <button type="submit" class="btn btn-default">Поиск</button>
                </div>
            </form>
        </div>
    </div>
</nav>
<br><br><br>