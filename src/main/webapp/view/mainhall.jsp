<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<img src="${pageContext.request.contextPath}/img/mainhall.jpg" alt="">
So ${nickname}, you are in the corridor of the cell block. There are three ways out of it.
<p>
    It remains for you to listen to your intuition or the voice of reason and choose direction.
</p>
<p>
    <a href="${pageContext.request.contextPath}/location/?id=dungeon">Descend</a> into the gloomy dungeon of an old prison? From which
    comes sounds like clattering
    jaws.
</p>
<p>
    <a href="${pageContext.request.contextPath}/location/?id=bossarena">Go down</a> a corridor with flames from an unknown source playing on the
    walls?
</p>
<a href="${pageContext.request.contextPath}/location/?id=exit">Climb up</a> the stairs to the doorway from which bright daylight
oozes into the prison?

<p>
    Also, various objects are scattered around you. You can take something:
</p>
<form action="${pageContext.request.contextPath}/takeitem" method="post">
    <c:forEach var="entry" items="${gameItems}">
        <div class="checkBox">
        <input type="checkbox" name="item" value="${entry.key}"> ${entry.value}
        </div>
    </c:forEach>
    <input type="submit" value="Take Selected">
</form>
