<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <title>Viking - Artist</title>
    </head>
    <body>
     <ng-controller = "getDetailArtist">
      <div class="pages" id="artistOverview">     

        <div class ="artisttab" id ="personalTabs">
         <ul>
          <li class="tab-link" data-tab="artisttab-1">Overview</li>
          <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
          <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
         </ul>
        </div>
         
           <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
               <img style="margin: 10px;-top: 10px; margin-right: 30px;" class=artistIndivimg ng-src="${home}/css/artist/{{artistdata.id}}.jpg"></img>
               <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">{{artistdata.name}}</h1>

               <p style="margin-bottom: 150px;">
                   <button class="pageButton">Play</button>
                   <button class="pageButton">Save</button>
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

        <h2> Related Artists </h2>
         <hr class="style14">
          <div class=artistitems>
           <div ng-repeat="artist in artistdata.relatedArtists | limitTo: 9">
            <a href = "#!/artists/{{artist.id}}"><img class=artistimg id ="{{artist.id}}" ng-src="${home}/css/artist/{{artist.id}}.jpg"></img></a>
            <li class = "artistname"><a id="{{artist.id}}" href= "#!/artists/{{artist.id}}">{{artist.name}}</a></li>
           </div>
          </div>   

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
         <div class ="artisttab" id ="personalTabs">
         <ul>
          <li class="tab-link" data-tab="artisttab-1">Overview</li>
          <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
          <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
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
         <p> {{artistdata.bio}}</p>
        </div> 

       <div class="pages" id="artistConcerts">     
         <div class ="artisttab" id ="personalTabs">
         <ul>
          <li class="tab-link" data-tab="artisttab-1">Overview</li>
          <li class="tab-link" data-tab="artisttab-2">Artist Bio</li>
          <li class="tab-link" data-tab="artisttab-3">Artist Concerts</li>
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
          <div ng-hide="(artistdata.concerts | objectLength) != 0">
               <h1 style = "text-align: center;"> This artist has not current concerts </h1>
         </div>
          
         <div ng-hide="(artistdata.concerts | objectLength) == 0">
             <table>
                 <tr>
                     <td>Date</td>
                     <td>Name of Venue</td>
                     <td>Address of Venue </td>
                     <td>Ticketing URL </td>
                     <td>Artists Performing </td>
                 </tr>
                 <tr ng-repeat="concert in artistdata.concerts">
                    <td>{{concert.date |   date:'d MMMM yyyy' }}</a></td>
</td>
                    <td>{{concert.venue.name}}</td>
                    <td>{{concert.venue.address.street}} {{concert.venue.address.city}}, {{concert.venue.address.state}} {{concert.venue.address.zip}}</td>
                    <td> <a href = "{{concert.ticketingUrl}}"> Tickets Here </a></td>
                    <td>
                        <li ng-repeat ="multipleArtists in concert.artists">
                            <a id="{{multipleArtists.id}}" href= "#!/artists/{{multipleArtists.id}}">{{multipleArtists.name}}</a>
                        </li>
                    <td>
                 </tr>
             </table>
         </div>
      </div>
  </body>
</html>