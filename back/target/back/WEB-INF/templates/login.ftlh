<#import "body.ftlh" as c>
<#if user??>
    <#assign username = user.getUsername()>
    <#else>
        <#assign username = 'user.username'>
</#if>

<@c.page username>
    <h2>Login</h2>
    <div class="row">
        <form class="col s8 offset-s2" method="post">
            <div class="row">
                <div class="input-field col s12">
                    <input id="username" type="text" name="username" class="validate">
                    <label for="username">Username</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" name="password" class="validate">
                    <label for="password">Password</label>
                </div>
            </div>
            <button class="btn waves-effect waves-light" type="submit" name="action">Login</button>
        </form>
    </div>
</@c.page>
