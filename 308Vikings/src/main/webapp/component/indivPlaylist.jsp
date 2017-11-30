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
            {{playlistdata.totalDuration | date:'HH:mm'}}
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

                  <!-- Edit Playlist Form -->
                  <div id="editPlaylistModal">
                    <div class="signup modal">
                      <div class=modal-content>
                        <div>Edit Playlist Details</div>
                        <hr class="style15" style="width:70%">
                          <form id="edit-playlist-form">
                            <div>
                              Playlist Name:
                              <input id="edit-playlist-name" ng-model="editPlaylist.name" type="text"></div>
                              <br>
                                <div>
                                  Playlist Description:
                                  <br>
                                    <textarea id="edit-playlist-description" ng-model="editPlaylist.description" rows=5 cols=40></textarea>
                                  </div>
                                  <br>
                                    <div>
                                      Upload thumbnail Image:
                                      <br>
                                        <input id="edit-playlist-thumbnail" type="file" file-model="editPlaylist.thumbnail" accept=".jpg"/>
                                        <br>
                                          <img id="edit-thumbnail-preview" src="${home}/css/playlist/{{playlistdata.playlist.id}}.jpg?_={{newDate}}" onerror="this.src='${home}css/music-player.png';" height="150px"/>
                                          <!--<img ng-src="{{editPlaylist.thumbnail}}" height="150px"/> -->

                                        </div>
                                        <br>
                                          <div>
                                            <button type="submit" class="pageButton" ng-click="updatePlaylist()">Edit</button>
                                            <button class="pageButton" ng-click="closeEditPlaylistForm()">Close</button>
                                          </div>
                                        </form>
                                      </div>
                                    </div>
                                  </div>

                                  <script>
                                    function readURL(input) {
                                      if (input.files && input.files[0]) {
                                        var reader = new FileReader();

                                        reader.onload = function (e) {
                                          $('#edit-thumbnail-preview').attr('src', e.target.result);
                                        };

                                        reader.readAsDataURL(input.files[0]);
                                      }
                                    }

                                    $("#edit-playlist-thumbnail").change(function () {
                                      readURL(this);
                                    });
                                  </script>

                                </body>
                              </html>