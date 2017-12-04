<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js"/>"></script>
        <title>Viking - Artist</title>
    </head>
    <body>
        <!--Artist Pages-->
           <div id='loading' class="error modal">
               <div class="loading-modal-content">
                   <img src=${home}/css/loading.gif></img>
               </div>
           </div>
           <div id="artistpage">
            <div class="container">
             <div id="menutab-3" class="menutab-content">
              <h2 style="margin-left:5%;">{{pageName}}</h2>
              <hr class="style14">
              <h3 ng-show="libraryArtists == 0"> You have no followed artists in your library. Follow some now! </h3>
              <div class=artistitems>
                  <div ng-repeat="artist in libraryArtists">
                <a href="#!artists/{{artist.artistIdentifier.id}}"><img class=artistimg id ="{{artist.artistIdentifier.id}}" ng-src="${home}/css/artist/{{artist.artistIdentifier.id}}.jpg"></img></a>
                <li class = "artistname"><a id="{{artist.artistIdentifier.id}}" href="#!artists/{{artist.artistIdentifier.id}}">{{artist.artistIdentifier.name}}</a></li>
               </div>
              </div>
             </div>
            </div>
           </div>


    </body>
</html>
