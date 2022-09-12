<%@ page language="java" contentType="text/html" pageEncoding="iso-8859-1" import="java.util.*" %>
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
<audio autoplay>
    <source src="${pageContext.request.contextPath}${sound}">
</audio>
<table>
    <tr>
        <td>
            <img id="change_image" src="${pageContext.request.contextPath}${image}" alt="">
        </td>
    </tr>
    <tr>
        <td>
            <div class="contentBlock">
                <jsp:include page="${storyBlock}"/>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <form action="${pageContext.request.contextPath}/grabitem" method="post">
                <c:if test="${itemsInLocation!=null && itemsInLocation.size()!=0}">
                    <p>Various objects are scattered around you. You can take something:</p>
                    <c:forEach var="entry" items="${itemsInLocation}">
                        <div class="checkBox">
                            <input type="checkbox" name="item" value="${entry.key}"> ${entry.value}
                        </div>
                    </c:forEach>
                    <input type="submit" value="Take Selected">
                </c:if>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <c:if test="${inventory!=null && inventory.size()!=0}">
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
