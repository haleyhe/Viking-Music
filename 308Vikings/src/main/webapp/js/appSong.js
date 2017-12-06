window.onload = function () {   
    if (! localStorage.justOnce) {
       localStorage.setItem("justOnce", "true");
       window.location.reload();
       
   }
   queueList = JSON.parse(localStorage.getItem("queueList"));

  
};

var song = new Audio();
var queueList = [];
localStorage.setItem("queueList", JSON.stringify(queueList));
var currentSong = 0;
var queueLoop = false;
var queueShuffle = false;



function addToQueue(element){
    var queue = {};
    var queuesong = new Audio(home + 'mp3/' + element.id + '.mp3');
    queue[element.id] = queuesong;
    queueList.push(queue);
    //console.log(Object.keys(queueList[currentSong])[0]);
    //console.log(Object.values(queueList[currentSong])[0]);
}
function addToQueueId(id){
    var queue = {};
    var queuesong = new Audio(home + 'mp3/' + id + '.mp3');
    queue[id] = queuesong;
    queueList.push(queue);
}

function playQueue(){
    if(currentSong < queueList.length){
        song.load();
        song = new Audio(home + 'mp3/'  +Object.keys(queueList[currentSong])[0]  + '.mp3');
        songhandler();
        var scope = angular.element(document.getElementById('globalcontroller')).scope();
        scope.getSongDetail(Object.keys(queueList[currentSong])[0]);
        scope.markSongPlayed(Object.keys(queueList[currentSong])[0]);
    }
}
function changeSong(element){
   song.load();
   song = new Audio(home + 'mp3/' + element.id + '.mp3');
   songhandler();
   var scope = angular.element(document.getElementById('globalcontroller')).scope();
   scope.markSongPlayed(element.id);
}

function songhandler(){
    $('#play').replaceWith('<img class="playerimg" id="pause" src=' + home + 'css/pause.png></img>');    
   
    song.addEventListener('loadedmetadata', function() {
        $("#seek").prop("max", song.duration);
        song.play();
        $("#seek").bind("change", function() {
            song.currentTime = $(this).val();
        });
        $("#volume").bind("change", function() {
            song.volume = $(this).val()/100;            
        });
        song.addEventListener('timeupdate',function (){
            $("#seek").prop("value", song.currentTime);
        });
        song.addEventListener('ended', function(){
            //add code for non queue song ending
            if(queueList.length-1 !== currentSong){
                currentSong++;
                playQueue();
            }
            else if(queueLoop){
                currentSong = 0;
                playQueue();
            }
        });
        $(document).on('click','#repeat', function(e) {
        if(song.loop === false){
            song.loop = true;
            $('#repeat').css('background','#347765');
        }
        else{
            song.loop = false;
            $('#repeat').css('background','');
        }
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
        $(document).on('click','#mutebtn', function(e) {
                        e.preventDefault();
                        song.muted = false;
                        $('#mutebtn').replaceWith('<img class="playerimg" id="volumebtn" src=' + home + 'css/speaker-5.png></img>');
        });
   });   
}

$( document ).ready(function() {
    $(document).on('click','#queueLoopbtn', function(e) {
        if(queueLoop === false){
            queueLoop = true;
            $('#queueLoopbtn').html("Loop On");
        }
        else{
            queueLoop = false;
            $('#queueLoopbtn').html("Loop");
        }
    });
    $(document).on('click','#shuffle', function(e) {
        if(queueShuffle === false){
            queueShuffle = true;
            $('#shuffle').css('background','#f24e35');
        }
        else{
            queueShuffle = false;
            $('#shuffle').css('background','');
        }
    });

    $(document).on('click','#next', function(e) {
                   e.preventDefault();
                   if(song.loop == true){
                       playQueue();
                   }
                   else if(queueShuffle == true){
                       currentSong = Math.floor((Math.random() * queueList.length) + 1);
                       playQueue();
                   }
                   else if(queueList.length-1 !== currentSong){
                       currentSong++;
                       playQueue();
                   }
                   else if(queueLoop){
                       currentSong = 0;
                       playQueue();
                   }
   });
   $(document).on('click','#back', function(e) {
                   e.preventDefault();
                   if(currentSong !== 0){
                       currentSong--;
                       playQueue();
                   }
   });
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