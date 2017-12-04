<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>

    <c:url var="home" value="/" scope="request"/>
    <title>Viking - Album</title>
  </head>
  <body>
    <!--Album Pages-->
    <div class=pages id=albumpage>
      <div class="container">
        <div id="menutab-3" class="menutab-content">
          <h2>{{pageName}}</h2>
          <hr class="style14">
            <div>
            <div class=albumitems>
              <div ng-repeat="album in data.albums">
                <a href="#!album/{{album.id}}">
                  <img class=albumimg ng-src="${home}/css/album/{{album.id}}.jpg" id="{{album.id}}"></img>
                </a>
                <li class=albumname>
                  <a href="#!album/{{album.id}}">{{album.name}}</a>
                </li>
                <li class=albumartist>
                  <a href="#!artists/{{album.artists[0].id}}">{{album.artists[0].name}}</a>
                </li>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>
  </html>
