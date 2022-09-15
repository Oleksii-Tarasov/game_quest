<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<audio autoplay>
    <source src="${pageContext.request.contextPath}/sound/laugh.mp3">
</audio>
<p>
    You stand before the Lord of the Underworld. <br>
    He looks at you with contempt, but does not take any action.
</p>
<p>
    Perhaps you should do something, ${nickname}, look into your inventory.
</p>
<div class="message">
    <p>
        ${effect}
    </p>

<c:if test="${inventory.size()==0}">
    but you come empty-handed and your fate is sealed... <br>
    ...await...
    <script>
        setTimeout(function(){
            window.location.href = '${pageContext.request.contextPath}/location/?id=hellend';
        }, 10000);
    </script>
</c:if>
</div>
<p>
    You have ${tries} tries
</p>
