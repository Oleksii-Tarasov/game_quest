<div class="introduceContentBlock">
    <img src="${pageContext.request.contextPath}/img/introduce.jpg" alt="">
    I present to your attention a visual game-quest,<br>
    the history of which develops depending on the decisions made by the player.<br><br>
    Enter your name, hero:
    <form action="${pageContext.request.contextPath}" method="post">
        <input type="text" name="nickname" required minlength="3" maxlength="8" value="<enter your name>"
               onfocus="this.value=''">
        <br> and <br>
        <div class="startButton">
            <button type="submit">Begin your story</button>
        </div>
    </form>
</div>




