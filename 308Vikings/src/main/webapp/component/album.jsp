<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Album</title>
    </head>
    <body>
        <!--Album Pages-->
         <div ng-controller="getAllAlbum" >
         <div ng-controller="getDetailAlbum">
           <div id='loading' class="error modal">
               <div class="loading-modal-content">
                   <img src=${home}/css/loading.gif></img>
               </div>
           </div>
             <div class=pages id=albumpage>

        
           <div class="container">
             <div id="menutab-3" class="menutab-content">
             <h2>Albums</h2>
             <hr class="style14">
               <div class=albumitems>
                 <div ng-repeat="album in data.albums">
                 <a ng-click="getAlbumJson($event)"><img class=albumimg ng-src="${home}/css/album/{{album.id}}.jpg" id="{{album.id}}"></img></a>
                 <li class=albumname><a id="{{album.id}}" ng-click="getAlbumJson($event)">{{album.name}}</a></li>
                 <li class=albumartist>{{album.artists[0].name}}</li>
                 </div>
               </div>    

           </div>
         </div>
       </div>
           <div class="pages" id=indivAlbumPage>                    

               <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">  
                   <img style="margin: 10px;-top: 10px;" class=albumimg ng-src="${home}/css/album/{{albumdata.album.id}}.jpg"></img>                                                     
                   <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{albumdata.album.name}}</h1>                      
                   <div ng-repeat="detailAlbumartist in albumdata.album.artists">
                       <div>{{detailAlbumartist.name}}</div>
                   </div>
                   <div><br>Release Date: </div>
                   <div>{{albumdata.album.releaseDate |  date:'d MMMM yyyy'}}</div>

                   <p style="margin-bottom: 120px;">
                       <button>Play</button>
                       <button>Save</button>
                   </p>
                   <hr class="style14" style="width:70%">
               </div>

           <table class=songtable>
             <tr>
               <td></td>
               <td></td>
               <td>Title</td>
               <td>Artist</td>
               <td>Duration</td>
             </tr>
             <tr ng-repeat="song in albumdata.album.songs">
               <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
               <td><img class=''></td>
               <td>{{song.name}}</td>
               <td><a ng-repeat="songartists in song.artists">{{songartists.name}} </a></td>
               <td>3:08</td>
             </tr>
           </table>
           <h3>More by {{albumdata.album.artists[0].name}}</h3>
           <hr class="style14" style="width:70%">
               <div class=albumitems>
                 <div ng-repeat="relatedAlbums in albumdata.relatedAlbums">
                 <a ng-click="getAlbumJson($event)"><img class=albumimg ng-src="${home}/css/album/{{relatedAlbums.id}}.jpg" id="{{relatedAlbums.id}}"></img></a>
                 <li class=albumname><a id="{{relatedAlbums.id}}" ng-click="getAlbumJson($event)">{{relatedAlbums.name}}</li>
                 <li class=albumartist>{{relatedAlbums.artists[0].name}}</li>     
                 </div>
             </div>
         </div> 

    </body>
</html>
