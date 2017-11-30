<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
  </head>
  <body>
    <div class="pages" id="searchResultPage">
      <h2>
        Search Results
      </h2>
      <div ng-if="searchSongs.length == 0 && searchAlbums.length == 0 && searchArtists.length == 0 && searchPlaylists.length == 0">
        <h3>
          No results were found for '{{query}}'
        </h3>
      </div>
      <!-- Song Results -->
      <div id="songResultsContainer" class="resultsContainer" ng-hide="searchSongs.length == 0">
        <div class="search-result-heading">
          <h3>
            Songs
          </h3>
          <a class="search-button" ng-click="searchAllSongs()" ng-if="overview">
            See All
          </a>
          <a class="search-button" ng-click="search()" ng-if="!overview">
            Back
          </a>
        </div>
        <div class="song-container">
          <table class="songtable">
            <tr>
              <td></td>
              <td></td>
              <td>Title</td>
              <td>Artist</td>
              <td>Album</td>
              <td>Duration</td>
            </tr>

            <tr ng-repeat="song in searchSongs">
              <td>
                <img class='play-btn' src=${home}css/play-button-1.png id="{{song.id}}" onclick="changeSong(this)"></img>
              </td>
              <div>
                <td>
                  <img class='play-btn' src="${home}css/plus.png" ng-hide="history.savedSongs[song.id]">
                    <img class="play-btn" src="${home}css/success.png" ng-show="history.savedSongs[song.id]"></td>
                  </div>
                  <td>{{song.name}}</td>
                  <td>
                    <a ng-repeat="songartists in song.artists">{{songartists.name}}
                    </a>
                  </td>
                  <td>{{song.album.name}}</td>
                  <td>{{song.duration  | convertMilSec}}</td>
                </table>
                <hr class="style14" style="width:70%"></div>
              </div>

              <!-- Album Results -->
              <div id="albumResultsContainer" class="resultsContainer" ng-hide="searchAlbums.length == 0">
                <div class="container">
                  <div class="search-result-heading">
                    <h3>Albums</h3>
                    <a class="search-button" ng-click="searchAllAlbums()">
                      See All
                    </a>
                    <a class="search-button" ng-click="search()" ng-if="!overview">
                      Back
                    </a>
                  </div>
                  <div class="albumitems" ng-controller="getDetailAlbum">
                    <div ng-repeat="album in searchAlbums">
                      <a ng-click="getAlbumJson($event)">
                        <img class=albumimg ng-src="${home}/css/album/{{album.id}}.jpg" id="{{album.id}}"></img>
                      </a>
                      <li class=albumname>
                        <a id="{{album.id}}" ng-click="getAlbumJson($event)">{{album.name}}</a>
                      </li>
                      <li class=albumartist>{{album.artists[0].name}}</li>
                    </div>
                  </div>
                  <hr class="style14" style="width:70%"></div>
                </div>

                <!-- Artist Results -->
                <div id="artistResultsContainer" class="resultsContainer" ng-hide="searchArtists.length == 0">
                  <div class="container">
                    <div class="search-result-heading">
                      <h3>Artists</h3>
                      <a class="search-button" ng-click="searchAllArtists()">
                        See All
                      </a>
                      <a class="search-button" ng-click="search()" ng-if="!overview">
                        Back
                      </a>
                    </div>
                    <div class="artistitems" ng-controller="getDetailArtist">
                      <div ng-repeat="artist in searchArtists">
                        <a ng-click="getArtistJson($event)">
                          <img class=artistimg id="{{artist.id}}" ng-src="${home}/css/artist/{{artist.id}}.jpg"></img>
                        </a>
                        <li class="artistname">
                          <a id="{{artist.id}}" ng-click="getArtistJson($event)">{{artist.name}}</a>
                        </li>
                      </div>
                    </div>
                  </div>
                  <hr class="style14" style="width:70%"></div>

                  <!-- Playlist Results -->
                  <div id="playlistResultsContainer" class="resultsContainer" ng-hide="searchPlaylists.length == 0">
                    <div class="container">
                      <div class="search-result-heading">
                        <h3>Playlists</h3>
                        <a class="search-button" ng-click="searchAllPlaylists()">
                          See All
                        </a>
                        <a class="search-button" ng-click="search()" ng-if="!overview">
                          Back
                        </a>
                      </div>
                      <div class="playlistitems" ng-controller="indivPlaylistController">
                        <div ng-repeat="playlist in searchPlaylists">
                          <a ng-click="getPlaylistJson($event)">
                            <!-- need to replace the alt -->
                            <img class="playlistimg" ng-src="${home}/css/playlist/{{playlist.id}}.jpg" alt="Playlist Image" id="{{playlist.id}}"></img>
                          </a>
                          <li class="playlistname">
                            <a id="{{playlist.id}}" ng-click="getPlaylistJson($event)">{{playlist.name}}</a>
                          </li>
                          <li class="playlistfollowers">{{playlist.numFollowers}}
                            Followers</li>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>
              </body>
            </html>
