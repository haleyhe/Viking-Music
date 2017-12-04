<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
    <title>Viking</title>
  </head>
  <body>
    <!--Album Pages-->
    <div class="pages" id="libraryAlbumsPage">
      <div class="container">
        <div id="menutab-3" class="menutab-content">
          <h2>{{pageName}}</h2>
          <hr class="style14">
            <h3 ng-show="libraryAlbums == 0"> You have no saved albums in your library. Add some now! </h3>
            <div class="albumitems">
              <div ng-repeat="album in libraryAlbums">
                <a href="#!album/{{album.albumIdentifier.id}}">
                  <img class=albumimg ng-src="${home}/css/album/{{album.albumIdentifier.id}}.jpg" id="{{album.albumIdentifier.id}}"></img>
                </a>
                <li class="albumname">
                  <a href="#!album/{{album.albumIdentifier.id}}">{{album.albumIdentifier.name}}</a>
                </li>
                <li class="albumartist">
                  <a href="#!artists/{{album.albumIdentifier.artists[0].id}}">{{album.albumIdentifier.artists[0].name}}</a>
                </li>
              </div>
            </div>
          </div>
        </div>
      </div>
  </body>
</html>
