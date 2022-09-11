<audio autoplay>
    <source src="${pageContext.request.contextPath}/sound/nooo.mp3">
</audio>
<p>
    ${effect}
</p>
<p>
    Nevertheless the bridge has been collapsed <br>
    and there is no way back. <br>
    But now you can take the throne of the Lord.
</p>
<p>
    How is it to be a new Lord of the Underworld, ${nickname}?
</p>
<script>
    var index = "0";
    let images = ["newlord.jpg"];

    function changeImg() {
        if (index === images.length) {
            index = "0";
        }
        change_image.src = '${pageContext.request.contextPath}/img/' + images[index];
        index++;
    }

    setTimeout(changeImg, 6000);
</script>
