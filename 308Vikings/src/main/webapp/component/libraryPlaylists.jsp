<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
    <title>Viking</title>
  </head>
  <body>
      <!-- Show all playlists -->
      <div class="pages" id="libraryPlaylistsPage">
        <div class="container">
          <div id="menutab-5" class="menutab-content">
            <h2>{{pageName}}</h2>
            <hr class="style14">
              <h3 ng-show="libraryPlaylists == 0"> You have no followed playlists in your library. Follow some now! </h3>
              <div class="playlistitems"  style="margin-bottom:50px">
                <div ng-repeat="playlist in libraryPlaylists">
                  <a href="#!playlist/{{playlist.playlistIdentifier.id}}">
                    <!-- need to replace the alt -->
                    <img class="playlistimg" ng-src="${home}/css/playlist/{{playlist.playlistIdentifier.id}}.jpg"  onerror="this.src='${home}css/music-player.png';" id="{{playlist.playlistIdentifier.id}}"></img>
                  </a>
                  <li class="playlistname">
                    <a id="{{playlist.playlistIdentifier.id}}" href="#!playlist/{{playlist.playlistIdentifier.id}}">{{playlist.playlistIdentifier.name}}</a>
                  </li>
                  <li class="playlistfollowers">{{playlist.playlistIdentifier.numFollowers}}
                    Followers</li>
                </div>
              </div>
            </div>
          </div>
        </div>

    </body>
  </html>
