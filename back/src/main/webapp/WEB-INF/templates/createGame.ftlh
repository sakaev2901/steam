<#import "body.ftlh" as c>
<#if user??>
    <#assign username = user.getUsername()>
<#else>
    <#assign username = 'user.username'>
</#if>

<@c.page username>
    <h2>Add game</h2>
    <div class="row">
        <form enctype="multipart/form-data" class="col s8 offset-s2" method="post" action="/game">
            <div class="row">
                <div class="input-field col s12">
                    <input id="name" type="text" name="name" class="validate">
                    <label for="name">Game Name</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="description" type="text" name="description" class="validate">
                    <label for="description">Description</label>
                </div>
            </div>

            <div class="row">
                <div class="file-field input-field col s12">
                    <div class="btn">
                        <span>Picture</span>
                        <input type="file" name="img">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text">
                    </div>
                </div>
            </div>

            <button class="btn waves-effect waves-light" type="submit" >Create</button>
            <input type="submit" value="Create">
        </form>
    </div>
</@c.page>
