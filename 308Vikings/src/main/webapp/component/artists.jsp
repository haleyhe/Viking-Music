<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>    
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Artist</title>
    </head>
    <body>
        <!--Artist Pages-->
         <div ng-controller = "getAllArtist">
         <div ng-controller = "getDetailArtist">
         <div ng-controller = "getDetailAlbums">
           <div id='loading' class="error modal">
               <div class="loading-modal-content">
                   <img src=${home}/css/loading.gif></img>
               </div>
           </div>
           <div class="pages" id="artistpage">
            <div class="container">
             <div id="menutab-3" class="menutab-content">
              <h2>Artists</h2>
              <hr class="style14">
              <div class=artistitems>
               <div ng-repeat="artist in data.artists">
                <a ng-click="getArtistJson($event)"><img class=artistimg id ="{{artist.id}}" ng-src="${home}/css/artist/{{artist.id}}.jpg"></img></a>
                <li class = "artistname"><a id="{{artist.id}}" ng-click="getArtistJson($event)">{{artist.name}}</a></li>
               </div>
              </div>    
             </div>
            </div>    
           </div>
           
           <div class=pages id="artistOverview">     
            <div class ="artisttab" id ="personalTabs">
             <ul>
              <li class="tab-link" data-tab="artisttab-1">Overview</li>
              <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
              <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
             </ul>
             
               <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
                   <img style="margin: 10px;-top: 10px; margin-right: 30px;" class=artistIndivimg ng-src="${home}/css/artist/{{artistdata.id}}.jpg"></img>
                   <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{artistdata.name}}</h1>

                   <p style="margin-bottom: 150px;">
                       <!--<button class="pageButton">Play</button>
                       <button class="pageButton">Save</button>-->
                   </p>
                   <br>
                   <hr class="style14" style="width:70%">
               </div>
             <h2> Top Songs </h2>
              <table class=songtable>
               <tr>
                 <td>Title</td>
                 <td>Duration</td>
               </tr>
               <tr ng-repeat="song in artistdata.topSongs | limitTo: 5">
                 <td>{{song.name}}</td>
                 <td>{{song.duration | convertMilSec}}</td>
               </tr>
             </table> 
            </div> 

            <h2> Related Artists </h2>
             <hr class="style14">
              <div class=artistitems>
               <div ng-repeat="artist in artistdata.relatedArtists | limitTo: 9">
                <a ng-click="getArtistJson($event)"><img class=artistimg id ="{{artist.id}}" ng-src="${home}/css/artist/{{artist.id}}.jpg"></img></a>
                <li class = "artistname"><a id="{{artist.id}}" ng-click="getArtistJson($event)">{{artist.name}}</a></li>
               </div>
              </div>   

              <h2> Albums </h2>
               <hr class="style14">
                <div class=albumitems>
                 <div ng-repeat="album in artistdata.albums">
                  <a ng-click="getAlbumJsons($event)"><img class=albumimg ng-src="${home}/css/album/{{album.id}}.jpg" id="{{album.id}}"></img></a>
                  <li class=albumname><a id="{{album.id}}" ng-click="getAlbumJsons($event)">{{album.name}}</a></li>
                 </div>
                </div>   
           </div>

           <div class=pages id="artistBio">     
            <div class ="artisttab" id ="personalTabs">
             <ul>
              <li class="tab-link" data-tab="artisttab-1">Overview</li>
              <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
              <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
             </ul>
             <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
                 <img style="margin: 10px;-top: 10px; margin-right: 30px;" class=artistIndivimg ng-src="${home}/css/artist/{{artistdata.id}}.jpg"></img>
                 <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{artistdata.name}}</h1>

                 <p style="margin-bottom: 150px;">
                     <!--<button class="pageButton">Play</button>
                     <button class="pageButton">Save</button>-->
                 </p>
                 <br>
                 <hr class="style14" style="width:70%">
             </div>
             <p> {{artistdata.bio}}</p>
            </div> 
           </div>

           <div class=pages id="artistConcerts">     
            <div class ="artisttab" id ="personalTabs">
             
             <ul>
              <li class="tab-link" data-tab="artisttab-1">Overview</li>
              <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
              <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
             </ul>
             <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
                 <img style="margin: 10px;-top: 10px; margin-right: 30px;" class=artistIndivimg ng-src="${home}/css/artist/{{artistdata.id}}.jpg"></img>
                 <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{artistdata.name}}</h1>

                 <p style="margin-bottom: 150px;">
                     <!--<button class="pageButton">Play</button>
                     <button class="pageButton">Save</button>-->
                 </p>
                 <br>
                 <hr class="style14" style="width:70%">
             </div>
              <div ng-repeat="concert in artistdata.concerts">
                <p>{{concert | noConcerts}}</p>
            </div> 
           </div>
          </div>
    </body>
</html>

