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
                   <div ng-repeat="detailAlbumartist in albumdata.album.artists">
                       <div><a href="#!artist/{{detailAlbumartist.id}}">{{detailAlbumartist.name}}</a></div>
                   </div>
                   <div><br>Release Date: </div>
                   <div>{{albumdata.album.releaseDate |  date:'d MMMM yyyy'}}</div>

                   <p style="margin-bottom: 120px;">
                       <button class="pageButton">Play</button>
                       <button class="pageButton">Queue</button>
                       <button class="pageButton unsave-button" ng-if="albumdata.saved" ng-click="unsaveAlbum()">
                         <span>Saved<span></button>
                           <button class="pageButton" ng-if="!albumdata.saved" ng-click="saveAlbum()">Save</button>
                   </p>
                   <hr class="style14" style="width:70%">
               </div>

            <h3 ng-show="albumdata.album.songs.length == 0"> This album has no songs. </h3>
            <table class="songtable" ng-hide="albumdata.album.songs.length == 0">
             <tr>
               <td></td>
               <td></td>
               <td>Title</td>
               <td>Artist</td>
               <td>Duration</td>
             </tr>
             <tr ng-repeat="song in albumdata.album.songs">
                 <td><img class='play-btn' src=${home}css/play-button-1.png id="{{song.id}}" onclick="changeSong(this)"></img></td>
               <div>
                   <td>
                       <img class='play-btn' src="${home}css/plus.png" ng-hide="albumdata.savedSongs[song.id]" >
                       <img class="play-btn" src="${home}css/success.png" ng-show="albumdata.savedSongs[song.id]">
                   </td>
               </div>

               <td>{{song.name}}</td>
               <td><label ng-repeat="songartists in song.artists"><a href="#!artists/{{songartists.id}}">{{songartists.name}}</a></label></td>
               <td>{{song.duration | convertMilSec}}</td>
             </tr>
           </table>
           <h3>More by {{albumdata.album.artists[0].name}}</h3>
           <hr class="style14" style="width:70%">
               <div class="albumitems">
                 <div ng-repeat="relatedAlbums in albumdata.relatedAlbums">
                 <a href="#!album/{{relatedAlbums.id}}"><img class=albumimg ng-src="${home}/css/album/{{relatedAlbums.id}}.jpg" id="{{relatedAlbums.id}}"></img></a>
                 <li class="albumname"><a id="{{relatedAlbums.id}}" href="#!album/{{relatedAlbums.id}}>{{relatedAlbums.name}}"</li>
                 <li class="albumartist">{{relatedAlbums.artists[0].name}}</li>
                 </div>
             </div>
         </div>
    </body>
</html>
