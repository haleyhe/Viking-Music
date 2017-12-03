window.onload = function () {
   if (! localStorage.justOnce) {
       localStorage.setItem("justOnce", "true");
       window.location.reload();
   }
};

song = new Audio();

function changeSong(element){
   //alert(element.id);
   song.load()
   song = new Audio(home + 'mp3/' + element.id + '.mp3');
   song.addEventListener('loadedmetadata', function() {
       $("#seek").prop("max", song.duration);
       song.play();
       $("#seek").bind("change", function() {
           song.currentTime = $(this).val();
       });
       song.addEventListener('timeupdate',function (){
           $("#seek").prop("value", song.currentTime);
       });
   $(document).on('click','#volumebtn', function(e) {
                   e.preventDefault();
                   song.muted = true;
                   $('#volumebtn').replaceWith('<img class="playerimg" id="mutebtn" src=' + home + 'css/speaker-8.png></img>');
   });
   $(document).on('click','#mutebtn', function(e) {
                   e.preventDefault();
                   song.muted = false;
                   $('#mutebtn').replaceWith('<img class="playerimg" id="volumebtn" src=' + home + 'css/speaker-5.png></img>');
   });
   });
   //song.play();
   
    $('#play').replaceWith('<img class="playerimg" id="pause" src=' + home + 'css/pause.png></img>');    
    
}

$( document ).ready(function() {
   $(document).on('click', '#play', function(e) {
                   e.preventDefault();
                   if(song.src == ""){

                   }
                   else{
                   song.play();
                   $('#play').replaceWith('<img class="playerimg" id="pause" src=' + home + 'css/pause.png></img>');
                   $("#seek").prop("max", song.duration);
                   }
   });
   $(document).on('click','#pause', function(e) {
                   e.preventDefault();
                   song.pause();
                   $('#pause').replaceWith('<img class="playerimg" id="play" src=' + home + 'css/play-button.png></img>');
   });
});