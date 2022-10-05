<%@ page language="java" contentType="text/html" pageEncoding="iso-8859-1" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
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
                <c:if test="${showItems==true}">
                    <p>Various objects are scattered around you. You can take something:</p>
                    <c:forEach var="entry" items="${itemsInLocation}">
                        <div class="itemButton">
                            <button type="submit" name="item" value="${entry.key}"> ${entry.value}
                        </div>
                    </c:forEach>
                </c:if>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <c:if test="${showInventory==true}">
                <div class="menu">
                    <button class="inventory"> Inventory</button>
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
    <tr>
        <td>
            <br>
            <div class="statButton">
                <button type="button" class="btn btn-dark btn-sm" data-toggle="modal" data-target="#statisticButton">
                    Game statistic
                </button>
            </div>
        </td>
    </tr>
</table>

<div class="modal fade" id="statisticButton" tabindex="-1" role="dialog" aria-labelledby="statisticButtonTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="statisticButtonTitle"><img
                        src="${pageContext.request.contextPath}/img/characterIcon.jpg" alt="">${nickname}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                completed game sessions : ${gameTries} <br>
                number of victories : ${goodTries} <br>
                number of losses : ${badTries} <br>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
