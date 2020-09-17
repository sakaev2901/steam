<#import "body.ftlh" as c>
<#if user??>
    <#assign username = user.getUsername()>
<#else>
    <#assign username = 'user.username'>
</#if>

<@c.page username>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="/resources/jquery.star-rating-svg.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/star-rating-svg.css">
    <div>
        <h2>${game.getName()}</h2>
    </div>
    <div>
        <p>${game.description}</p>
    </div>
    <div>
        <img class="game-img" src="/image/${game.getImageName()}" alt="${game.getImageName()}">
    </div>
    <div class="grey darken-4 white-text manual-title">
        <h2>Manuals</h2>
    </div>
    <div>
        <a href="/manual/editor/${game.id}" class="waves-effect waves-light btn orange-text grey darken-4">Create Manual</a>
    </div>
    <div>
        <#list game.manuals as manual>
            <div class="row">
                    <div class="card grey darken-4 manual-card">
                        <div class="card-content white-text">

                            <span class="card-title white-text"><h4>Author: <span class="orange-text">${manual.user.username}</span></h4></span>
                            <span class="card-title orange-text"><h2>${manual.title}</h2></span>
                            <span class="my-rating"></span>
                            <script>
                                $(".my-rating").starRating({
                                    starSize: 25,
                                    initialRating: ${manual.getAverageRating()},
                                    readOnly: true
                                });
                            </script>
                            ${manual.htmlText}
                        </div>
                        <div class="card-action btn-container">
                            <a href="/manual/${manual.id}">Open Manual</a>
                        </div>
                    </div>
            </div>
        </#list>
    </div>
</@c.page>

