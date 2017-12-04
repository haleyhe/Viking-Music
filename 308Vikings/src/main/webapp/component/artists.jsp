<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
    <title>Viking</title>
  </head>
  <body>
    <!--Artist Pages-->
    <div id="artistpage">
      <div class="container">
        <div id="menutab-3" class="menutab-content">
          <h2 style="margin-left:5%;">{{pageName}}</h2>
          <hr class="style14">
            <div class="artistitems">
              <div ng-repeat="artist in l" onclick=void(0)>
                <a href="#!artists/{{artist.id}}">
                  <img class=artistimg id="{{artist.id}}" ng-src="${home}/css/artist/{{artist.id}}.jpg"></img>
                </a>
                <li class="artistname">
                  <a id="{{artist.id}}" href="#!artists/{{artist.id}}">{{artist.name}}</a>
                </li>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>
  </html>
