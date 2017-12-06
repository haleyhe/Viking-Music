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
                <button class="pageButton unfollow-button" ng-if="playlistdata.following  && userId != playlistdata.playlist.creator.id" ng-click="unfollowPlaylist()">
                  <span>Following<span>
                </button>
                <button class="pageButton" ng-if="!playlistdata.following" ng-click="followPlaylist()">Follow</button>
                <button class="pageButton" ng-click="showEditPlaylistForm()" ng-if="userId == playlistdata.playlist.creator.id">Edit</button>
                <button class="pageButton" ng-click="showDeletePlaylistForm()" ng-if="userId == playlistdata.playlist.creator.id">Delete</button>
              </p>
              <hr class="style14" style="width:70%"></div>

              <h3 ng-show="playlistdata.playlist.songs.length == 0"> This playlist has no songs. </h3>
              <table class="songtable" ng-hide="playlistdata.playlist.songs.length == 0">
                <tr>
                  <td>#</td>
                  <td></td>
                  <td></td>
                  <td>Title</td>
                  <td>Artist</td>
                  <td>Album</td>
                  <td>Date Added</td>
                  <td>Duration</td>
                </tr>
                <tr ng-repeat="song in playlistdata.playlist.songs">
                <td id="nohover">{{song.trackNumber}}</td>
                <td id="nohover">
                  <img class='play-btn' src=${home}css/play-button-1.png></img>
                 </td>
                <td id="nohover">
                    <a ng-click="saveSong(song.id,playlistdata.savedSongs)"><img class='play-btn' src="${home}css/plus.png" ng-hide="playlistdata.savedSongs[song.id]"></a>
                    <a ng-click="unsaveSong(song.id,playlistdata.savedSongs, null)"><img class="play-btn" src="${home}css/success.png" ng-show="playlistdata.savedSongs[song.id]"></td></a>
                  <td>{{song.name}}</td>
                  <td>
                    <a href="#!artists/{{songartists.id}}" ng-repeat="songartists in song.artists">
                      {{songartists.name}}<span ng-hide="$last">, </span>
                    </a>
                  </td>
                  <td><a href="#!album/{{song.album.id}}">{{song.album.name}}</a></td>
                  <td>{{song.dateAdded | date:'yyyy-MM-dd'}}</td>
                  <td>{{song.duration  | convertMilSec}}</td>
                  <td id="nohover">
                    <img class='play-btn' src="${home}css/more.png" id="{{song.id}}" onclick="openMoreMenu(this)" ng-click="getAllPlaylistforMenu()">
                    <div class="moredropdown {{song.id}}" style="display: none">
                        <ul>
                            <a class="moremenulist" id="{{song.id}}" onclick="addToQueue(this)" ng-click="populateQueue(song.artists, song.name, song.duration)">Add to Queue</a>
                            <a class="moremenulist" ng-click="removeFromPlaylist(playlistdata.playlist.id, song.trackNumber)">Remove from Playlist</a>                           
                        </ul>
                    </div>
                   </td>
                    </tr>
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

                  <!-- Delete Playlist Modal -->
                  <div id="deletePlaylistModal">
                    <div class="delete modal">
                      <div class=modal-content>
                        <div>Delete Playlist</div>
                        <hr class="style15" style="width:70%">
                          <form id="delete-playlist-form">
                                <p> Are you sure you want to delete this playlist? </p>
                                <button type="submit" class="pageButton" ng-click="deletePlaylist()">Yes</button>
                                <button class="pageButton" ng-click="closeDeletePlaylistForm()">No</button>
                              </div>
                          </form>
                      </div>
                      </div>
                  </div>

</body>
</html>
