<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Browse</title>
    </head>
    <body>
        <div class=pages id=musicpage>
          <div class="container">
            <ul class="musictabs">
              <li class="tab-link current" data-tab="musictab-1" ng-click="discoverMusic()">Discover</li>
              <li class="tab-link" data-tab="musictab-2" ng-click="getRecentReleases()"> Newly Released</li>
              <li class="tab-link" data-tab="musictab-3" ng-click="getAllGenres()">Genres & Moods</li>
              <li class="tab-link" data-tab="musictab-4" ng-click="getConcertRecommendations()">Concerts</li>
              <li class="tab-link" data-tab="musictab-5" ng-click="getCharts()">Chart</li>
            </ul>

            <div id="musictab-1" class="musictab-content current">
              <div class="container">
              <h2>Discover Recommendations</h2>
              <div id="discover-container">
                  <div ng-show="isEmpty(discover.recommendations)">
                    <br>
                    <br>
                    <h3> Play songs and add to your library to get recommendations. </h3>
                  </div>
                  <div ng-repeat="(key, value) in discover.recommendations">
                    <hr class="style14">
                    <div style="margin-left:60px;">
                    <h4> Because you listened to {{key}}</h4>
                    <div>
                      <div class="albumitems">
                        <div ng-repeat="album in value">
                          <a href="#!album/{{album.id}}">
                            <img class=albumimg ng-src="${home}/css/album/{{album.id}}.jpg" id="{{album.id}}"></img>
                          </a>
                          <li class=albumname>
                            <a href="#!album/{{album.id}}">{{album.name}}</a>
                          </li>
                          <li class=albumartist>
                            <a href="#!artists/{{album.artists[0].id}}">{{album.artists[0].name}}</a>
                          </li>
                        </div>
                      </div>
                  </div>
              </div>
              </div>
            </div>
          </div>
        </div>

            <div id="musictab-2" class="musictab-content">
              <div class="container">
                  <h2>Newly Released</h2>
                  <hr class="style14">
                    <div>
                    <div class=albumitems>
                      <div ng-repeat="album in recent">
                        <a href="#!album/{{album.id}}">
                          <img class=albumimg ng-src="${home}/css/album/{{album.id}}.jpg" id="{{album.id}}"></img>
                        </a>
                        <li class=albumname>
                          <a href="#!album/{{album.id}}">{{album.name}}</a>
                        </li>
                        <li class=albumartist>
                          <a href="#!artists/{{album.artists[0].id}}">{{album.artists[0].name}}</a>
                        </li>
                      </div>
                    </div>
                  </div>
              </div>
            </div>

            <div id="musictab-3" class="musictab-content">
              <div class="container">
                  <h2>Genres & Moods</h2>
                  <hr class="style14">
                    <div class="playlistitems">
                      <div ng-repeat="playlist in genres.playlists">
                        <a href="#!playlist/{{playlist.id}}">
                          <!-- need to replace the alt -->
                          <img class="playlistimg" ng-src="${home}/css/playlist/{{playlist.id}}.jpg?_={{newDate}}" id="{{playlist.id}}"></img>
                        </a>
                        <li class="playlistname">
                          <a id="{{playlist.id}}" href="#!playlist/{{playlist.id}}">{{playlist.name}}</a>
                        </li>
                        <li class="playlistfollowers">{{playlist.numFollowers}}
                          Followers</li>
                      </div>
                    </div>
                </div>
            </div>

            <div id="musictab-4" class="musictab-content">
              <div class="container">
                  <h2>Concert Recommendations </h2>
                  <div ng-show="concerts.concerts.length === 0" align = "center">
                      <br/>
                      <br/>
                      <h3> You do not have any concert recommendations </h3>
                  </div>
                  <div ng-show="concerts.concerts.length !== 0">
                      <br/>
                      <br/>
                      <table class="songtable">
                         <tr class>
                             <td class = "title">Date</td>
                             <td class = "title">Name of Venue</td>
                             <td class = "title">Artists Performing </td>
                         </tr>
                         <tr ng-repeat="concert in concerts.concerts" >
                            <td>{{concert.date |   date:'d MMMM yyyy' }}</a></td>
                            <td><a href="#!concert/{{concert.id}}">{{concert.venue.name}}</a></td>
                            <td>
                                <label ng-repeat ="multipleArtists in concert.artists" class="noBullet">
                                    <a id="{{multipleArtists.id}}" href= "#!/artists/{{multipleArtists.id}}">{{multipleArtists.name}}<span ng-hide="$last">, </span></a>
                                </label>
                            </td>
                         </tr>
                      </table>
                  </div>
              </div>
            </div>

            <div id="musictab-5" class="musictab-content">
              <div style="margin-top: 20px; margin-bottom: 10px;">
                      <h2>Top Charts</h2>
                      <br>
                      <hr class="style14" style="width:70%">
                        <table class="songtable">
                        <tr>
                          <td></td>
                          <td></td>
                          <td>Title</td>
                          <td>Artist</td>
                          <td>Album</td>
                          <td>Duration</td>
                          <td>
                        </tr>

                        <tr ng-repeat="song in charts.songs">
                          <td>
                            <img class='play-btn' src=${home}css/play-button-1.png  id="{{song.id}}" onclick="changeSong(this)"></img>
                          </td>
                          <div>
                            <td>
                                <img class='play-btn' src="${home}css/plus.png" ng-hide="charts.savedSongs[song.id]" ng-click="saveSong(song.id,charts.savedSongs)">
                                <img class="play-btn" src="${home}css/success.png" ng-show="charts.savedSongs[song.id]" ng-click="unsaveSong(song.id,charts.savedSongs)"></td>
                              </div>
                              <td>{{song.name}}</td>
                              <td>
                                <a href="#!artists/{{songartists.id}}" ng-repeat="songartists in song.artists">
                                  {{songartists.name}}<span ng-hide="$last">, </span>
                                </a>
                              </td>
                              <td><a href="#!album/{{song.album.id}}">{{song.album.name}}</a></td>
                              <td>{{song.duration  | convertMilSec}}</td>
                              <td id="nohover">
                                  <img class='play-btn' src="${home}css/more.png" id="{{song.id}}" onclick="openMoreMenu(this)" ng-click="getAllPlaylistforMenu()">
                                  <div class="moredropdown {{song.id}}" style="display: none">
                                      <ul>
                                          <a class="moremenulist" id="{{song.id}}" onclick="addToQueue(this)" ng-click="populateQueue(song.artists, song.name, song.duration)">Add to Queue</a>
                                          <div ng-repeat="myplaylist in playlistmenudata.playlists">
                                          <a class="moremenulist" ng-click="addToPlaylist(myplaylist, song)">Add to {{myplaylist.name}}</a>
                                          </div>
                                      </ul>
                                  </div>
                              </td>
                            </table>
                          </div>
            </div>
          </div>
        </div>
    </body>
    <script>
       window.onload = function () {
            if (! localStorage.justOnce) {
                localStorage.setItem("justOnce", "true");
                window.location.reload();
            }
        }
    </script>
</html>
