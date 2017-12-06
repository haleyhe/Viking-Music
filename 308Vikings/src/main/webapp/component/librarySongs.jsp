<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
    <title>Viking - Library Songs</title>
  </head>
  <body>
    <div class="pages" id="librarySongsPage">
      <div style="margin-top: 20px; margin-bottom: 10px;">
    <h2> Library Songs </h2>
    <hr class="style14" style="width:70%">

      <h3 ng-show="librarySongs.songList == 0"> You have no saved songs in your library. Add some now! </h3>
      <table class="songtable" ng-hide="librarySongs.songList.length == 0">
      <tr>
        <td></td>
        <td></td>
        <td>Title</td>
        <td>Artist</td>
        <td>Album</td>
        <td>Release Date</td>
        <td>Duration</td>
      </tr>
      <tr ng-repeat="libSong in librarySongs.songList | orderBy : null : true">
            <td id="nohover">
              <img class='play-btn' src=${home}css/play-button-1.png  id="{{libSong.song.id}}" onclick="changeSong(this)"  ng-click="changePlayer(libSong.song.album.id, libSong.song.artists, libSong.song.name, libSong.song.lyrics)"></img>
            </td>
            <td id="nohover">
            <a ng-click="saveSong(libSong.song.id,librarySongs.savedSongs)"><img class='play-btn' src="${home}css/plus.png" ng-hide="librarySongs.savedSongs[libSong.song.id]"></a>
            <a ng-click="unsaveSong(libSong.song.id,librarySongs.savedSongs, librarySongs.songList)"><img class="play-btn" src="${home}css/success.png" ng-show="librarySongs.savedSongs[libSong.song.id]"></a>
            </td>
            <td id="{{libSong.song.id}}" onclick="changeSong(this)"  ng-click="changePlayer(libSong.song.album.id, libSong.song.artists, libSong.song.name, libSong.song.lyrics)">{{libSong.song.name}}</td>
            <td>
              <a href="#!artists/{{songartists.id}}" ng-repeat="songartists in libSong.song.artists">
                {{songartists.name}}<span ng-hide="$last">, </span>
              </a>
            </td>
            <td>{{libSong.song.album.name}}</td>
            <td>{{libSong.dateAdded | date:'yyyy-MM-dd'}}</td>
            <td>{{libSong.song.duration | convertMilSec}}</td>
            <td id="nohover">
                <img class='play-btn' src="${home}css/more.png" id="{{libSong.song.id}}" onclick="openMoreMenu(this)" ng-click="getAllPlaylistforMenu()">
                <div class="moredropdown {{libSong.song.id}}" style="display: none">
                    <ul>
                        <a class="moremenulist" id="{{libSong.song.id}}" onclick="addToQueue(this)" ng-click="populateQueue(libSong.song.artists, libSong.song.name, libSong.song.duration)">Add to Queue</a>
                        <div ng-repeat="myplaylist in playlistmenudata.playlists">
                        <a class="moremenulist" ng-click="addToPlaylist(myplaylist, libSong.song)">Add to {{myplaylist.name}}</a>
                        </div>
                    </ul>
                </div>
            </td>
          </tr>
          </table>
        </div>
      </div>
  </body>
</html>
