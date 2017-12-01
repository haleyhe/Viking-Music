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
        <td>Date Added</td>
        <td>Duration</td>
      </tr>
      <tr ng-repeat="libSong in librarySongs.songList">
        <td>
          <img class='play-btn' src=${home}css/play-button-1.png  id="{{libSong.song.id}}" onclick="changeSong(this)"></img>
        </td>
        <div>
          <td>
              <img class='play-btn' src="${home}css/plus.png" ng-hide="librarySongs.savedSongs[libSong.song.id]">
              <img class="play-btn" src="${home}css/success.png" ng-show="librarySongs.savedSongs[libSong.song.id]"></td>
            </div>
            <td>{{libSong.song.name}}</td>
            <td>
              <a ng-repeat="songartists in libSong.song.artists">{{libSong.songartists.name}}
              </a>
            </td>
            <td>{{libSong.song.album.name}}</td>
            <td>{{libSong.dateAdded | date:'yyyy-MM-dd'}}</td>
            <td>{{libSong.song.duration | convertMilSec}}</td>
          </table>
        </div>
      </div>
  </body>
</html>
