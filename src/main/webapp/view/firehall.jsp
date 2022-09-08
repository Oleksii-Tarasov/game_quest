<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<img src="${pageContext.request.contextPath}/img/firehall.jpg" alt="">
<audio autoplay>
    <source src="${pageContext.request.contextPath}/sound/">
</audio>
<p>
    The bridge behind you collapses and you find yourself in front of the
    <a href="${pageContext.request.contextPath}/location/?loc=bossarena">gate into the unknown.</a> <br>
    You don`t know what`s waiting for you, but something tells you tha everything will not be easy.
</p>
<p>
    Various objects are scattered around you. You can take something.
</p>
<form action="${pageContext.request.contextPath}/grabitem" method="post">
    <c:forEach var="entry" items="${gameItems}">
        <div class="checkBox">
            <input type="checkbox" name="item" value="${entry.key}"> ${entry.value}
        </div>
    </c:forEach>
    <c:if test="${gameItems.size()!=0}">
        <input type="submit" value="Take Selected">
    </c:if>
</form>
