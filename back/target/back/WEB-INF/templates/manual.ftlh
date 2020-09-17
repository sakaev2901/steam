<#import "body.ftlh" as c>
<#import "body.ftlh" as c>
<#if user??>
    <#assign username = user.getUsername()>
<#else>
    <#assign username = 'user.username'>
</#if>

<@c.page username>
    <h2>Manual ${manual.title}</h2>
    <h2>Author: <b>${manual.user.getUsername()}</b></h2>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="/resources/jquery.star-rating-svg.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/star-rating-svg.css">
    <h2>Rating: <b>${manual.getAverageRating()}</b></h2>
    <span class="my-rating"></span>
    <div class="manual-text">${manual.htmlText}</div>
    <script>
        $(".my-rating").starRating({
            starSize: 25,
            initialRating: 4.3,
            readOnly: false,
            callback: function(currentRating, $el){
                $.post("/api/rating", {manualId: ${manual.id} , value: currentRating})
            }
        });
    </script>

</@c.page>
