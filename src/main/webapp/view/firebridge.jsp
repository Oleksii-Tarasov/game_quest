<p>
    After passing through the corridor you find yourself on a bridge. <br>
    Behind you a wall of fire is formed not allowing you to return. <br>
    And the bridge itself begins to pour hot lava.
</p>
<p>
    <div id="clockdiv">
        <div><span class="seconds"></span></div>
    </div>
    seconds left to decide: <a href="${pageContext.request.contextPath}/location/?title=firehall">run ahead</a> or stay put?
</p>
<script>
    function getTimeRemaining(endtime) {
        var t = Date.parse(endtime) - Date.parse(new Date());
        var seconds = Math.floor((t / 1000) % 60);

        return {
            'total': t,
            'seconds': seconds
        };
    }

    function initializeClock(id, endtime) {
        var clock = document.getElementById(id);
        var secondsSpan = clock.querySelector('.seconds');

        function updateClock() {
            var t = getTimeRemaining(endtime);

            secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);

            if (t.total <= 0) {
                location.href="${pageContext.request.contextPath}/location/?title=firecage"
            }
        }

        updateClock();
        setInterval(updateClock, 1000);
    }

    var deadline = new Date(Date.parse(new Date()) + 25 * 1000);
    initializeClock('clockdiv', deadline);
</script>

