<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <title>Viking - Artist</title>
        <link href="<c:url value="/css/indivArtists.css" />" rel="stylesheet">    </head>
    <body>
     <ng-controller = "getDetailArtist">
      <div class="pages" id="artistOverview">

        <div class ="artisttab">
         <ul>
          <li class="tab-link current" data-tab="artisttab-1">Overview</li>
          <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
          <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
          <li class="tab-link" data-tab="artisttab-4">Related Artists</li>
         </ul>
        </div>

           <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
               <img style="margin: 10px;-top: 10px; margin-right: 30px;" class=artistIndivimg ng-src="${home}/css/artist/{{artistdata.id}}.jpg"></img>
               <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{artistdata.name}}</h1>

               <p style="margin-bottom: 150px;">
                   <button class="pageButton unfollow-button" ng-if="artistdata.following" ng-click="unfollowArtist()">
                         <span>Following<span></button>
                           <button class="pageButton" ng-if="!artistdata.following" ng-click="followArtist()">Follow</button>
                   </p>
               <br>
               <hr class="style14" style="width:70%">
           </div>
         <h2> Top Songs </h2>
          <table class=songtable style="padding-left:50px;">
           <tr>
             <td></td>
             <td></td>
             <td>Title</td>
             <td>Duration</td>
           </tr>
           <tr ng-repeat="song in artistdata.topSongs | limitTo: 5">
            <td id="nohover" style="width: 30px;"><img class='play-btn' src=${home}css/play-button-1.png id="{{song.id}}" onclick="changeSong(this)"></img></td>
            <td id="nohover" style="width: 50px;">
                <a ng-click="saveSong(song.id,artistdata.savedSongs)"><img class='play-btn' src="${home}css/plus.png" ng-hide="artistdata.savedSongs[song.id]" ></a>
                <a ng-click="unsaveSong(song.id,artistdata.savedSongs, null)"><img class="play-btn" src="${home}css/success.png" ng-show="artistdata.savedSongs[song.id]"></a>
            </td>
            <td>{{song.name}}</td>
            <td>{{song.duration | convertMilSec}}</td>
            <td id="nohover">
                <img class='play-btn' src="${home}css/more.png" id="{{song.id}}" onclick="openMoreMenu(this)" ng-click="getAllPlaylistforMenu()">
                <div class="moredropdown {{song.id}}" style="display: none">
                    <ul>
                        <a class="moremenulist" id="{{song.id}}" onclick="addToQueue(this)" ng-click="populateQueue(song.artists, song.name, song.duration)">Add to Queue</a>
                        <div ng-repeat="myplaylist in playlistmenudata">
                        <a class="moremenulist" ng-click="addToPlaylist(myplaylist, song)">Add to {{myplaylist.playlistIdentifier.name}}</a>                           
                        </div>
                    </ul>
                </div>
            </td>

           </tr>
         </table>
         <br />
         <br />
          <h2> Albums </h2>
           <hr class="style14">
            <div class=albumitems>
             <div ng-repeat="album in artistdata.albums">
              <a href = "#!/album/{{album.id}}"><img class=albumimg ng-src="${home}/css/album/{{album.id}}.jpg" id="{{album.id}}"></img></a>
              <li class=albumname><a id="{{album.id}}" href = "#!/album/{{album.id}}">{{album.name}}</a></li>
             </div>
            </div>
       </div>

       <div class="pages" id="artistBio">
         <div class ="artisttab">
         <ul>
          <li class="tab-link" data-tab="artisttab-1">Overview</li>
          <li class="tab-link current" data-tab="artisttab-2">Artist Bio</li>
          <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
          <li class="tab-link" data-tab="artisttab-4">Related Artists</li>
         </ul>
        </div>
         <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
             <img style="margin: 10px;-top: 10px; margin-right: 30px;" class=artistIndivimg ng-src="${home}/css/artist/{{artistdata.id}}.jpg"></img>
             <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{artistdata.name}}</h1>

             <p style="margin-bottom: 150px;">
             </p>
             <br>
             <hr class="style14" style="width:70%">
         </div>
        <div class ="bio">
         <p ng-bind-html= "artistdata.bio | lineBreaks | sanitize"></p>
        </div>
       </div>

       <div class="pages" id="artistConcerts">
        <div class ="artisttab">
         <ul>
          <li class="tab-link" data-tab="artisttab-1">Overview</li>
          <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
          <li class="tab-link current" data-tab="artisttab-3">Artist Concerts</li>
          <li class="tab-link" data-tab="artisttab-4">Related Artists</li>
         </ul>
        </div>
         <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
             <img style="margin: 10px;-top: 10px; margin-right: 30px;" class=artistIndivimg ng-src="${home}/css/artist/{{artistdata.id}}.jpg"></img>
             <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{artistdata.name}}</h1>

             <p style="margin-bottom: 150px;">
             </p>
             <br>
             <hr class="style14" style="width:70%">
         </div>
          <div ng-hide="(artistdata.concerts | objectLength) !== 0">
               <h1 style = "text-align: center;"> This artist has no current concerts </h1>
         </div>

         <br />
         <br />
         <br />

         <div ng-hide="(artistdata.concerts | objectLength) === 0">
             <table style = "padding-left: 30px;" align = "center">
                 <tr class>
                     <td class = "title">Date</td>
                     <td class = "title">Name of Venue</td>
                     <td class = "title">Artists Performing </td>
                 </tr>
                 <tr ng-repeat="concert in artistdata.concerts" >
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
      </div>
      <div class="pages" id="relatedArtists">
        <div class ="artisttab">
         <ul>
          <li class="tab-link" data-tab="artisttab-1">Overview</li>
          <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
          <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
          <li class="tab-link current" data-tab="artisttab-4">Related Artists</li>
         </ul>
        </div>
         <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
             <img style="margin: 10px;-top: 10px; margin-right: 30px;" class=artistIndivimg ng-src="${home}/css/artist/{{artistdata.id}}.jpg"></img>
             <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{artistdata.name}}</h1>

             <p style="margin-bottom: 150px;">
             </p>
             <br>
             <hr class="style14" style="width:70%">
         </div>
          <br />
          <br />

          <h2> Related Artists </h2>
          <div class=artistitems>
           <div ng-repeat="artist in artistdata.relatedArtists | limitTo: 8">
            <a href = "#!/artists/{{artist.id}}"><img class=artistimg id ="{{artist.id}}" ng-src="${home}/css/artist/{{artist.id}}.jpg"></img></a>
            <li class = "artistname"><a id="{{artist.id}}" href= "#!/artists/{{artist.id}}">{{artist.name}}</a></li>
           </div>
          </div>
      </div>

  </body>
</html>
