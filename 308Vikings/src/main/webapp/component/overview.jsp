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
              <li class="tab-link current" data-tab="musictab-1">Discover</li>
              <li class="tab-link" data-tab="musictab-2" ng-click="getRecentReleases()"> Newly Released</li>
              <li class="tab-link" data-tab="musictab-3" ng-click="getAllGenres()">Genres & Moods</li>
              <li class="tab-link" data-tab="musictab-4" ng-click="getConcertRecommendations()">Concert Recommendations</li>
              <li class="tab-link" data-tab="musictab-5">Chart</li>
            </ul>

            <div id="musictab-1" class="musictab-content current">
              <div class=albumitems>
                <div>
                <img class=albumimg src="${home}/css/album/MiGente.jpg"></img>
                <li class=albumname>Mi Gente Feat. Beyonce</li>
                <li class=albumartist>J Balvin, Willy William</li>
                </div>
                <div>
                <img class=albumimg src="${home}/css/album/TellMeYouLoveMe.png"></img>
                <li class=albumname>Tell Me You Love Me</li>
                <li class=albumartist>Demi Lovato</li>
                </div>
                <div>
                <img class=albumimg src="${home}/css/album/TheBiggerArtist.jpg"></img>
                <li class=albumname>The Bigger Artist</li>
                <li class=albumartist>A Boogie Wit da Hoodie</li>
                </div>
                <div>
                <img class=albumimg src="${home}/css/album/NowDeluxe.png"></img>
                <li class=albumname>Now (Deluxe)</li>
                <li class=albumartist>Shania Twain</li>
                </div>
                <div>
                <img class=albumimg src="${home}/css/album/YoungerNow.png"></img>
                <li class=albumname>Younger Now</li>
                <li class=albumartist>Miley Cyrus</li>
                </div>
                <div>
                <img class=albumimg src="${home}/css/album/savagethank.jpg"></img>
                <li class=albumname>Savage</li>
                <li class=albumartist>Tank</li>
                </div>
                <div>
                <img class=albumimg src="${home}/css/music-player-2.png"></img>
                <li class=albumname>Seven Days</li>
                <li class=albumartist>PARTYNEXTDOOR</li>
                </div>
              </div>
            </div>

            <div id="musictab-2" class="musictab-content">
              <div class="container">
                  <h2>{{pageName}}</h2>
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
                  <h2>{{pageName}}</h2>
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
              <ul class=musictabs>
                  <h1 style = "padding-left: 30px; padding-top:10px;"> Concert Recommendations </h1>
                  <div ng-show="concerts.concerts.length === 0" align = "center">
                      <br/>
                      <br/>
                      
                      <h2> You do not have any concert recommendations </h2>
                  </div>
                  <div ng-show="concerts.concerts.length !== 0">
                      <br/>
                      <br/>
                      <table style = "padding-left: 30px;" align = "center">
                         <tr class>
                             <td class = "title">Date</td>
                             <td class = "title">Name of Venue</td>
                             <td class = "title">Artists Performing </td>
                         </tr>
                         <tr ng-repeat="concert in concerts.concerts" >
                            <td class = "listItems">{{concert.date |   date:'d MMMM yyyy' }}</a></td>
                            <td class = "listItems"><a href="#!concert/{{concert.id}}">{{concert.venue.name}}</a></td>
                            <td class = "listItems" style = "text-align: center">
                                <li ng-repeat ="multipleArtists in concert.artists" class="noBullet">
                                    <a id="{{multipleArtists.id}}" href= "#!/artists/{{multipleArtists.id}}">{{multipleArtists.name}}</a>
                                </li>
                            </td>
                         </tr>
                      </table>
                  </div>
              </ul>
            </div>

            <div id="musictab-5" class="musictab-content">
              <ul class=musictabs>
                <li>Chart</li>
              </ul>
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
