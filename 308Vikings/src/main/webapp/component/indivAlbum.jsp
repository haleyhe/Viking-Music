<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <title>Viking - Album</title>
    </head>
    <body>
           <div class="pages" id=indivAlbumPage>
               <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
                   <img style="margin: 10px;-top: 10px;" class=albumimg ng-src="${home}/css/album/{{albumdata.album.id}}.jpg"></img>
                   <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{albumdata.album.name}}</h1>
                   <div>
                       <a href="#!artists/{{albumArtist.id}}" ng-repeat="albumArtist in albumdata.album.artists">
                         {{albumArtist.name}}<span ng-hide="$last">, </span>
                       </a>
                   </div>
                   <div><br>Release Date: {{albumdata.album.releaseDate |  date:' MMMM d yyyy'}}</div>

                   <p style="margin-bottom: 120px;">
                       <button class="pageButton" ng-click="albumToPlay(albumdata)">Play</button>
                       <button class="pageButton" ng-click="albumToQueue(albumdata)")>Queue</button>
                       <button class="pageButton unsave-button" ng-if="albumdata.saved" ng-click="unsaveAlbum()">
                         <span>Saved<span></button>
                           <button class="pageButton" ng-if="!albumdata.saved" ng-click="saveAlbum()">Save</button>
                   </p>
                   <hr class="style14" style="width:70%">
               </div>

            <h3 ng-show="albumdata.album.songs.length == 0"> This album has no songs. </h3>
            <table class="songtable" ng-hide="albumdata.album.songs.length == 0">
                <tr>
                  <td>#<td>
                  <td></td>
                  <td>Title</td>
                  <td>Artist</td>
                  <td>Duration</td>
                  <td></td>
                </tr>
                <tr ng-repeat="song in albumdata.album.songs">
                 <td id="nohover"> {{song.trackNumber}} </td>
                 <td id="nohover">
                     <img class='play-btn' src=${home}css/play-button-1.png id="{{song.id}}" onclick="changeSong(this)" ng-click="changePlayer(albumdata.album.id, song.artists, song.name, song.lyrics, song.id)"></img>
                 </td>
                <td id="nohover">
                    <a ng-click="saveSong(song.id,albumdata.savedSongs)"><img class='play-btn' src="${home}css/plus.png" ng-hide="albumdata.savedSongs[song.id]" ></a>
                    <a ng-click="unsaveSong(song.id,albumdata.savedSongs, null)"><img class="play-btn" src="${home}css/success.png" ng-show="albumdata.savedSongs[song.id]"></a>
                </td>
               <td id="{{song.id}}" onclick="changeSong(this)" ng-click="changePlayer(albumdata.album.id, song.artists, song.name, song.lyrics)">{{song.name}}</td>
               <td><label ng-repeat="songartists in song.artists"><a href="#!artists/{{songartists.id}}">{{songartists.name}}<span ng-hide="$last">, </span> </a></label></td>
               <td>{{song.duration | convertMilSec}}</td>
               <td id="nohover">
                   <img class='play-btn' src="${home}css/more.png" id="{{song.id}}" onclick="openMoreMenu(this)">
                   <div class="moredropdown {{song.id}}" style="display: none">
                       <ul>
                           <a class="moremenulist" id="{{song.id}}" onclick="addToQueue(this)" ng-click="populateQueue(song.artists, song.name, song.duration)">Add to Queue</a>
                           <a class="moremenulist">Add to Playlist</a>
                       </ul>
                   </div>
               </td>
             </tr>
           </table>
           <h3>More by {{albumdata.album.artists[0].name}}</h3>
           <hr class="style14" style="width:70%">
               <div class="albumitems">
                 <div ng-repeat="relatedAlbums in albumdata.relatedAlbums">
                 <a href="#!album/{{relatedAlbums.id}}"><img class=albumimg ng-src="${home}/css/album/{{relatedAlbums.id}}.jpg" id="{{relatedAlbums.id}}"></img></a>
                 <li class="albumname"><a id="{{relatedAlbums.id}}" href="#!album/{{relatedAlbums.id}}">{{relatedAlbums.name}}</a></li>
                 <li class="albumartist"><a href="#!artists/{{relatedAlbums.artists[0].id}}">{{relatedAlbums.artists[0].name}}</a></li>
                 </div>
             </div>
         </div>
    </body>
    <script>
    </script>
</html>
