        window.onload = function () {
            if (! localStorage.justOnce) {
                localStorage.setItem("justOnce", "true");
                window.location.reload();
            }
        };
 
        song = new Audio(home + '/download/AlanWalker-Fade.mp3');
        duration = song.duration;

        $(document).on('click', '#play', function(e) {
                        e.preventDefault();
                        $("#seek").prop("max", song.duration);
                        song.play();
                        $('#play').replaceWith('<img class="playerimg" id="pause" src=' + home + 'css/pause.png></img>');
        });
        $(document).on('click','#pause', function(e) {
                        e.preventDefault();
                        song.pause();
                        $('#pause').replaceWith('<img class="playerimg" id="play" src=' + home + 'css/play-button.png></img>');
        });
        $("#seek").bind("change", function() {
		song.currentTime = $(this).val();
	});
        song.addEventListener('timeupdate',function (){
		$("#seek").prop("value", song.currentTime);
	});


