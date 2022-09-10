<p>
    You put out the hellfire with water from the bucket and the Lord of the Underworld turns into steam.
</p>
<p>
    Nevertheless the bridge has been collapsed and there is no way back. <br>
    But now you can take the throne of the Lord of the Underworld.
</p>
<p>
    How is it to be a new Lord, ${nickname}?
</p>
<script>
    var index = "0";
    let images = ["newlord.jpg"];

    function changeImg() {
        if (index == images.length) {
            index = "0";
        }
        change_image.src = '${pageContext.request.contextPath}/img/' + images[index];
        index++;
    }

    setTimeout(changeImg, 6000);
</script>
