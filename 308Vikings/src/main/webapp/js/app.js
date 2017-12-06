$(document).ready(function() {
  
  $('ul.musictabs li').click(function() {
    var tab_id = $(this).attr('data-tab');

    $('ul.musictabs li').removeClass('current');
    $('.musictab-content').removeClass('current');

    $(this).addClass('current');
    $("#" + tab_id).addClass('current');
  });

  $('ul.tabs li').click(function() {
    var tab_id = $(this).attr('data-tab');

    $('ul.tabs li').removeClass('current');
    $('.tab-content').removeClass('current');

    $(this).addClass('current');
    $("#" + tab_id).addClass('current');
  });

  $('div.menutabs li').click(function() {
    var tab_id = $(this).attr('data-tab');
    $('div.menutabs li').removeClass('current');
    $('.menutab-content').removeClass('current');

    $(this).addClass('current');
    $("#" + tab_id).addClass('current');

    if ($(this).attr('data-tab') === 'menutab-1') {
      $('.pages').css("display", "none");
      $('#historyPage').show();
    }
    if ($(this).attr('data-tab') === 'menutab-2') {
      $('.pages').css("display", "none");
      $('#librarySongsPage').show();
    }
    if ($(this).attr('data-tab') === 'menutab-3') {
      $('.pages').css("display", "none");
      $('#libraryAlbumsPage').show();
    }
    if ($(this).attr('data-tab') === 'menutab-4') {
      $('.pages').css("display", "none");
      $('#libraryArtistsPage').show();
    }
    if ($(this).attr('data-tab') === 'menutab-5') {
      $('.pages').css("display", "none");
      $('#libraryPlaylistsPage').show();
    }
  });

  $(".close").click(function() {
    $(".modal").css("display", "none");
  });
  
  $('#adclose').click(function(){
     $('.ad').css('height', '50px');
     $('#adclose').css('display', 'none');
  });

  $('.newPlaylist').click(function() {
    $('.pages').css("display", "none");
    $('#playListPage').show();
  });

  $('.artistitems').click(function() {
    $('.pages').css("display", "none");
    $('#libindivArtistPage').show();
  });

  $('.concerttable').click(function() {
    $('.pages').css("display", "none");
    $('#indivConcertPage').show();
  });

  $('.user-dropdown-menu').click(
    function() {
      $(this).removeClass('open');
      $('.user-dropdown-menu').css("display", "none");
    });

  $('.registerbtn').click(function() {
    $('.signup.modal').css("display", "block");
  });

  $('#to-admin-portal-form').click(function() {
    window.location.replace("/308Vikings/adminportal");
  });
  


  function readEditURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (e) {
        $('#edit-thumbnail-preview').attr('src', e.target.result);
      };
      reader.readAsDataURL(input.files[0]);
    }
  }

  $("#edit-playlist-thumbnail").change(function () {
    readEditURL(this);
  });

  function readCreateURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (e) {
        $('#create-thumbnail-preview').attr('src', e.target.result);
      };
      reader.readAsDataURL(input.files[0]);
    }
  }

  $("#create-playlist-thumbnail").change(function () {
    readCreateURL(this);
  });
});

function openMoreMenu(element){
    var more = "moredropdown " + element.id;
    var menuelement = document.getElementsByClassName(more);
    if(menuelement[0].style.display === "none"){
        $('.moredropdown').css('display', 'none');
        menuelement[0].style.display = "block";
    }
    else{
        menuelement[0].style.display = "none";
    }
}

app.controller("globalController", function ($scope, $rootScope, $location, $http) {
  $scope.getUser = function() {
    $http.get('/308Vikings/UserAccount/getSessionUser')
    .then(function mySuccess(response) {
        $rootScope.userId = response.data.id;
        $rootScope.username = response.data.username;
        $rootScope.premium = response.data.premium;
    }, function myError(response) {
        $scope.name = response.statusText;
    });
  };
  $scope.getUser();
 
  $scope.changePlayer = function(albumid, artistname, songname, lyrics, songId){
      $('#cpImg').replaceWith('<img src="' + home + '/css/album/' + albumid + '.jpg" style="width:55px; float:left" id="cpImg">');
      $('#cpName').replaceWith('<div id="cpName">' + artistname[0].name + '<br/>' + songname + '</div>');
      $('#lyrics').replaceWith('<div id="lyrics" style="display: none"><pre style="padding: 10px">' + lyrics + '<pre></div>');   
  };
  $scope.populateQueue = function(artistname, songname){      
      $('#queuetable').append("<tr><td width='300px'>"+ songname + "</td><td>"+artistname[0].name+"</td></tr>");
  };
  
  $scope.albumToQueue = function(albumList){
      for(var i = 0; i < albumList.album.songs.length; i++){
         addToQueueId(albumList.album.songs[i].id);
         $('#queuetable').append("<tr><td width='300px'>"+ albumList.album.songs[i].name + "</td><td>"+albumList.album.songs[i].artists[0].name+"</td></tr>");
      }
  };
  $scope.getSongDetail = function(songId){
        $http({
          method: 'GET',
          url: '/308Vikings/Song/getSong',
          headers: {'Content-Type': 'application/json'},
          params: {id: songId}
        })
        .then(function successCallback(response) {
            $('#cpImg').replaceWith('<img src="' + home + '/css/album/' + response.data.song.album.id + '.jpg" style="width:55px; float:left" id="cpImg">');
            $('#cpName').replaceWith('<div id="cpName">' + response.data.song.artists[0].name + '<br/>' + response.data.song.name + '</div>');
            $('#lyrics').replaceWith('<div id="lyrics" style="display: none"><pre style="padding: 10px">' + response.data.song.lyrics + '<pre></div>');   
        }, function errorCallback(response) {});
  };
  $scope.albumToPlay = function(albumList){
      queueList.splice(0,queueList.length);
      $('#queuetable').empty();
      for(var i = 0; i < albumList.album.songs.length; i++){
         addToQueueId(albumList.album.songs[i].id);
         $('#queuetable').append("<tr><td width='300px'>"+ albumList.album.songs[i].name + "</td><td>"+albumList.album.songs[i].artists[0].name+"</td></tr>");
      }
      playQueue();
  };
  $scope.markSongPlayed = function(songId){
        $http.post('/308Vikings/UserMusic/markSongAsPlayedForUser', {songId:songId, clicked:true}, {
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }
          })
          .then(function successCallback(response) {
          }, function errorCallback(response) {});
  };
  
  $scope.saveSong = function(songId, savedSongs) {
      $("#loading").css("display", "block");
      $http.post('/308Vikings/UserMusic/saveSong', {id:songId}, {
          headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          }
        })
        .then(function successCallback(response) {
            savedSongs[songId] = true;
            if(!response.data.success){
                $("#message-modal").css("display", "block");
                $('#message').html(response.data.error);
            }
        }, function errorCallback(response) {});
      $("#loading").css("display", "none");
  };

  $scope.unsaveSong = function(songId, savedSongs, songList) {
      $("#loading").css("display", "block");
      $http.post('/308Vikings/UserMusic/unsaveSong', {id:songId}, {
          headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          }
        })
        .then(function successCallback(response) {
            savedSongs[songId] = false;
            if(!response.data.success){
                ("#message-modal").css("display", "block");
                $('#message').html(response.data.error);
            } else if ($location.path() == "/library/songs"){
                delete savedSongs[songId];
                var songLen = songList.length;
                for (var i = 0; i < songLen; i++) {
                    if (songList[i].song.id == songId) {
                      songList.splice(i,1);
                      break;
                    }
                }
            }
        }, function errorCallback(response) {});
      $("#loading").css("display", "none");
  };

});

app.filter("convertMilSec", function(){
   return function(input){
       var minutes = Math.floor(input / 60000);
       var seconds = ((input % 60000) / 1000).toFixed(0);
       return minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
   }
 });
