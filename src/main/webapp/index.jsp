<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        <%@include file="/css/page.css" %>
    </style>
    <title>Escape Quest</title>
</head>
<header>
    ~ Escape Quest ~
</header>
<body>
<table>
    <tr>
        <td>
            <div class="contentBlock">
                <jsp:include page="${contentBlock}"/>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <c:set var="inv" value="${inventory}" scope="request"/>
            <c:if test="${inv!=null && inv.size()!=0}">
                <div class="menu">
                    <button class="inventory">Inventory↓</button>
                    <div class="dropdown-child">
                        <form action="${pageContext.request.contextPath}/grabitem" method="get">
                            <c:forEach var="entry" items="${inventory}">
                                <button type="submit" name="item" value="${entry.key}">${entry.value}</button>
                            </c:forEach>
                        </form>
                    </div>
                </div>
            </c:if>
        </td>
    </tr>
    <tr>
        <td>
            <c:if test="${isGameOver == true}">
                <div class="loserLabel">
                    <label>Game Over</label>
                </div>
                <button onclick="location.href='${pageContext.request.contextPath}/restart'">~ Change
                    your Fate ~
                </button>
            </c:if>
            <c:if test="${isWinner == true}">
                <div class="winnerLabel">
                    <label>You Win!</label>
                </div>
                <button onclick="location.href='${pageContext.request.contextPath}/restart'">~ No, I want
                    to play ~
                </button>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
