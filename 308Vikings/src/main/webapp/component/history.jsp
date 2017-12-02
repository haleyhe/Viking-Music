<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>

        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - History</title>
    </head>
    <body>
      <div class="pages" id="historyPage">
        <div style="margin-top: 20px; margin-bottom: 10px;">
                <h2> Play History </h2>

                <br>
                <hr class="style14" style="width:70%">

                  <h3 ng-show="history.songList == 0"> You have never played any songs. Play some now! </h3>
                  <table class="songtable" ng-hide="history.songList.length == 0">
                  <tr>
                    <td></td>
                    <td></td>
                    <td>Title</td>
                    <td>Artist</td>
                    <td>Album</td>
                    <td>Duration</td>
                  </tr>

                  <tr ng-repeat="song in history.songList">
                    <td>
                      <img class='play-btn' src=${home}css/play-button-1.png  id="{{song.id}}" onclick="changeSong(this)"></img>
                    </td>
                    <div>
                      <td>
                          <img class='play-btn' src="${home}css/plus.png" ng-hide="history.savedSongs[song.id]">
                          <img class="play-btn" src="${home}css/success.png" ng-show="history.savedSongs[song.id]"></td>
                        </div>
                        <td>{{song.name}}</td>
                        <td>
                          <a href="#!artists/{{songartists.id}}" ng-repeat="songartists in song.artists">
                            <!--<a href="#!artists/{{songartists.id}}">{{songartists.name}} </a>-->
                            {{songartists.name}}
                          </a>
                        </td>
                        <td><a href="#!album/{{song.album.id}}">{{song.album.name}}</a></td>
                        <td>{{song.duration  | convertMilSec}}</td>
                      </table>
                    </div>
    </body>
</html>
