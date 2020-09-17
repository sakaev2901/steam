<#macro page username>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <link rel="stylesheet" href="/resources/style.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
    <nav class="grey darken-4">
        <div class="nav-wrapper">
            <a href="#" class="brand-logo orange-text">Logo</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">



                <#if user??>
                    <li><a href="/games" class="">Games</a></li>
                    <#if user.role == "ROLE_ADMIN">
                        <li><a href="/game">Add Game</a></li>
                    </#if>
                    <li><a href="/logout">Logout</a></li>
                    <#else>
                        <li><a href="/login">Login</a></li>
                        <li><a href="/signUp">Sign Up</a></li>
                </#if>

            </ul>
        </div>
    </nav>
    <div class="container">
        <#nested/>

    </div>
    <!-- footer section -->
    </body>
    </html>
</#macro>