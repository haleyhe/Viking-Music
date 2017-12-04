<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
    <title>Viking - Playlist</title>
  </head>
  <body>

      <!-- Show all playlists -->
      <div class="pages" id="playlistPage">
        <div class="container">
          <div id="menutab-5" class="menutab-content">
            <h2>{{pageName}}</h2>
            <hr class="style14">
              <div class="playlistitems">
                <div ng-repeat="playlist in data.playlists">
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
        </div>

    </body>
  </html>
