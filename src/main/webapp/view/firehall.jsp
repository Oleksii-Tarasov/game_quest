<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<img src="${pageContext.request.contextPath}/img/firehall.jpg" alt="">
<audio autoplay>
    <source src="${pageContext.request.contextPath}/sound/">
</audio>
<p>
    This path led you to the fire gates. <br>
    The passage on which you came is blocked by a wall of fire.
</p>
<p>
    Also, various objects are scattered around you. You can take something:
</p>
<form action="${pageContext.request.contextPath}/grabitem" method="post">
    <c:forEach var="entry" items="${gameItems}">
        <div class="checkBox">
            <input type="checkbox" name="item" value="${entry.key}"> ${entry.value}
        </div>
    </c:forEach>
    <input type="submit" value="Take Selected">
</form>
