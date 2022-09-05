<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<img src="${pageContext.request.contextPath}/img/mainhall.jpg" alt="">
<audio autoplay>
    <source src="${pageContext.request.contextPath}/sound/mainhall.mp3">
</audio>
<p>
    You are in the corridor of the cell block. There are three ways out of it.
</p>
<p>
    It remains for you, ${nickname}, to listen to your intuition or the voice of reason and choose direction.
</p>
<p>
    <a href="${pageContext.request.contextPath}/location/?loc=dungeon">Descend</a> into the gloomy dungeon of an old
    prison? From which comes sounds like clattering jaws.
</p>
<p>
    <a href="${pageContext.request.contextPath}/location/?loc=firehall">Go down</a> a corridor with flames from an
    unknown source playing on the walls?
</p>
<a href="${pageContext.request.contextPath}/location/?loc=garden">Climb up</a> the stairs
    to the doorway from which bright daylight oozes into the prison?
