<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
    <script type="text/javascript" src="<c:url value=" /js/app.js" />"></script>
    <title>Viking - Playlist</title>
  </head>
  <body>
    <!-- Loading Module -->
    <div id='loading' class="error modal">
      <div class="loading-modal-content">
        <img src=${home}/css/loading.gif></img>
      </div>
    </div>

    <!-- Show all playlists -->
    <div class="pages" id="playlistpage">
      <div class="container">
        <div id="menutab-5" class="menutab-content">
          <h2>Playlists</h2>
          <hr class="style14">
            <div class="playlistitems">
              <div ng-repeat="playlist in data.playlists">
                <a ng-click="getPlaylistJson($event)">
                  <!-- need to replace the alt -->
                  <img class="playlistimg" ng-src="${home}/css/playlist/{{playlist.id}}.jpg" alt="Playlist Image" id="{{playlist.id}}"></img>
                </a>
                <li class="playlistname">
                  <a id="{{playlist.id}}" ng-click="getPlaylistJson($event)">{{playlist.name}}</a>
                </li>
                <li class="playlistfollowers">{{playlist.id}}</li>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Indiviual Playlist -->
      <div class="pages" id="indivPlaylistPage">
        <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
          <img style="margin: 10px;-top: 10px;" class=albumimg ng-src="${home}/css/playlist/{{playlistdata.playlist.id}}.jpg"></img>
          <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{playlistdata.playlist.name}}</h1>
          <p>
            {{playlistdata.playlist.description}}
          </p>
          <br>
            <p>
              Created by:
              {{playlistdata.playlist.creator.name}}
              -
              {{playlistdata.numSongs}}
              Songs - Play time:
              {{playlistdata.totalDuration | date:'HH:mm'}}
            </p>
            <p>
             Followers: {{playlistdata.playlist.numFollowers}}
            </p>
            <p style="margin-bottom: 120px;">
              <button class="pageButton">Play</button>
              <button class="pageButton">Queue</button>
              <button class="pageButton">{{playlistdata.following ? 'Following' : 'Follow'}}</button>
            </p>
            <hr class="style14" style="width:70%"></div>

            <table class="songtable">
              <tr>
                <td></td>
                <td></td>
                <td>Title</td>
                <td>Artist</td>
                <td>Album</td>
                <td>Date Added</td>
                <td>Duration</td>
              </tr>
              <tr ng-repeat="song in playlistdata.playlist.songs">
                <td>
                  <img class='play-btn' src=${home}css/play-button-1.png></img>
                </td>
                <div>
                  <td>
                    <img class='play-btn' src="${home}css/plus.png" ng-hide="albumdata.savedSongs[song.id]">
                      <img class="play-btn" src="${home}css/success.png" ng-show="albumdata.savedSongs[song.id]"></td>
                    </div>
                    <td>{{song.name}}</td>
                    <td>
                      <a ng-repeat="songartists in song.artists">{{songartists.name}}
                      </a>
                    </td>
                    <td>{{song.album.name}}</td>
                    <td>{{song.dateAdded | date:'yyyy-MM-dd'}}</td>
                    <td>{{song.duration  | date:'HH:mm'}}</td>
                  </table>
                </div>
              </body>
            </html>
