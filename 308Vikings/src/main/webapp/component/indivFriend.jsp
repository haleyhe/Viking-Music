<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <title>Viking - Friends</title>
    </head>
    <body>
        <div class="pages" id="indivFriendPage">
            <div style="padding-left: 50px; padding-top: 30px;">
            <img src ="/308Vikings//css/user-4.png" width="150px">
            </div>
            <h1 style = "padding-left: 50px;">
               {{currentName}}'s Playlists
            </h1>   
            <br/>
           <hr class="style14">
           <br/>
           <br/>
           <div class = 'container' ng-show="friendsplaylist.playlists.length === 0">
                <h2 align = "center"> You have no friends. You can add friends above.</h2>
           </div>
           <div class = 'container' ng-show="friendsplaylist.playlists.length !== 0">
               <div class="playlistitems">
                <div ng-repeat="playlist in friendsplaylist.playlists">
                  <a href="#!playlist/{{playlist.id}}">
                    <!-- need to replace the alt -->
                    <img class="playlistimg" ng-src="${home}/css/playlist/{{playlist.id}}.jpg"  onerror="this.src='${home}css/music-player.png';" id="{{playlist.id}}"></img>
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
    </body>
</html>
