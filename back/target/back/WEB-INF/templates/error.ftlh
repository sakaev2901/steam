<#import "body.ftlh" as c>
<#if user??>
    <#assign username = user.getUsername()>
<#else>
    <#assign username = 'user.username'>
</#if>

<@c.page username>
    <h2>Oooops, ERROR</h2>

</@c.page>
