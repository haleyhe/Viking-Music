<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>

        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/appSong.js" />"></script>
        <title>Viking - Album</title>
    </head>
    <body>
        <!--Album Pages-->
             <div class=pages id=albumpage>
                 
           <div class="container">
             <div id="menutab-3" class="menutab-content">
             <h2>Albums</h2>
             <hr class="style14">
               <div class=albumitems>
                 <div ng-repeat="album in data.albums">
                 <a href="#!album/{{album.id}}"><img class=albumimg ng-src="${home}/css/album/{{album.id}}.jpg" id="{{album.id}}"></img></a>
                 <li class=albumname><a id="{{album.id}}" href="#!album/{{album.id}}"</a></li>
                 <li class=albumartist><a href="#!artists/{{album.artists[0].id}}">{{album.artists[0].name}}</a></li>
                 </div>
               </div>

           </div>
         </div>
       </div>
    </body>
</html>
