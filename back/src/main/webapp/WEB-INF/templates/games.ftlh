<#import "body.ftlh" as c>
<#if user??>
    <#assign username = user.getUsername()>
<#else>
    <#assign username = 'user.username'>
</#if>

<@c.page username>
<h2>Games</h2>
<#list games as game>
<div class="row">
    <div class="card horizontal grey darken-4 white-text">
        <div class="card-image">
            <img src="/image/${game.imageName}">
        </div>
        <div class="card-stacked">
            <div class="card-content">
                <h4>${game.name}</h4>
                <p>${game.description}</p>
            </div>
            <div class="card-action">
                <a href="/game/${game.id}" class="open-btn">Open</a>
            </div>
        </div>
</div>

    </#list>
    </@c.page>
