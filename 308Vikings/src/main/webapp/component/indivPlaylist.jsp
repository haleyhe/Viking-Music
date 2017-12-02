<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
    <title>Viking - Playlist</title>
  </head>
  <body>
    <!-- Indiviual Playlist -->
    <div class="pages" id="indivPlaylistPage">
      <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
        <img style="margin: 10px;-top: 10px;" class=albumimg ng-src="${home}/css/playlist/{{playlistdata.playlist.id}}.jpg?_={{newDate}}" onerror="this.src='${home}css/music-player.png';"></img>
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
            {{playlistdata.totalDuration | convertMilSec}}
          </p>
          <p>
            Followers:
            {{playlistdata.playlist.numFollowers}}
          </p>
          <p style="margin-bottom: 120px;">
            <button class="pageButton">Play</button>
            <button class="pageButton">Queue</button>
            <button class="pageButton unfollow-button" ng-if="playlistdata.following" ng-click="unfollowPlaylist()">
              <span>Following<span></button>
                <button class="pageButton" ng-if="!playlistdata.following" ng-click="followPlaylist()">Follow</button>
                <button class="pageButton" ng-click="showEditPlaylistForm()">Edit</button>
              </p>
              <hr class="style14" style="width:70%"></div>

              <h3 ng-show="playlistdata.playlist.songs.length == 0"> This playlist has no songs. </h3>
              <table class="songtable" ng-hide="playlistdata.playlist.songs.length == 0">
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
                        <a href="#!artists/{{songartists.id}}" ng-repeat="songartists in song.artists">
                          {{songartists.name}}
                        </a>
                      </td>
                      <td><a href="#!album/{{song.album.id}}">{{song.album.name}}</a></td>
                      <td>{{song.dateAdded | date:'yyyy-MM-dd'}}</td>
                      <td>{{song.duration  | convertMilSec}}</td>
                    </table>
                  </div>

                  <!-- Edit Playlist Modal -->
                  <div id="editPlaylistModal">
                    <div class="edit modal">
                      <div class=modal-content>
                        <div>Edit Playlist Details</div>
                        <hr class="style15" style="width:70%">
                          <form id="edit-playlist-form">
                              Playlist Name:<input ng-model="editPlaylist.name" type="text"><br><br>
                              Playlist Description:<br><textarea ng-model="editPlaylist.description" rows=3 cols=40></textarea><br><br>
                              Upload thumbnail Image:<br><input id="edit-playlist-thumbnail" type="file" file-model="editPlaylist.thumbnail" accept=".jpg"/><br>
                              <img id="edit-thumbnail-preview" src="${home}/css/playlist/{{playlistdata.playlist.id}}.jpg?_={{newDate}}" onerror="this.src='${home}css/music-player.png';" height="150px"/>  <br>
                              <div>
                                <button type="submit" class="pageButton" ng-click="updatePlaylist()">Edit</button>
                                <button class="pageButton" ng-click="closeEditPlaylistForm()">Close</button>
                              </div>
                          </form>
                      </div>
                      </div>
                  </div>

</body>
</html>
