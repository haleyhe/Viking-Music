<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>

        <c:url var="home" value="/" scope="request" />
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=0.5, user-scalable=no">
        <link href='http://fonts.googleapis.com/css?family=Julius Sans One:400;300' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css?family=Raleway:300,400" rel="stylesheet" type='text/css'>
        <link href="<c:url value="/css/style.css" />" rel="stylesheet">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
        <script>var home = '${home}';</script>
        <script>
            var app = angular.module("myApp", ["ngRoute"]);
              app.config(function($routeProvider) {
                  $routeProvider
                  .when("/", {
                      templateUrl : "${home}component/overview.jsp",
                      controller: "browseController"
                  })
                  .when("/history", {
                      templateUrl : "${home}component/history.jsp",
                      controller: "historyController"
                  })
                  .when("/library/songs", {
                      templateUrl : "${home}component/librarySongs.jsp",
                      controller: "librarySongsController"
                  })
                  .when("/library/playlists", {
                      templateUrl : "${home}component/libraryPlaylists.jsp",
                      controller: "libraryPlaylistsController"
                  })
                  .when("/library/albums", {
                      templateUrl : "${home}component/libraryAlbums.jsp",
                      controller: "libraryAlbumsController"
                  })
                  .when("/library/artists", {
                      templateUrl : "${home}component/libraryArtists.jsp",
                      controller: "libraryArtistsController"
                  })
                  .when("/album", {
                      templateUrl : "${home}component/album.jsp",
                      controller: "getAllAlbum"
                  })
                  .when("/album/:id", {
                      templateUrl : "${home}component/indivAlbum.jsp",
                      controller: "getDetailAlbum"
                  })
                  .when("/artists", {
                      templateUrl : "${home}component/artists.jsp",
                      controller: "getAllArtist"
                  })
                  .when("/artists/:id", {
                      templateUrl : "${home}component/indivArtist.jsp",
                      controller: "getDetailArtist"
                  })
                  .when("/concert/:id", {
                      templateUrl : "${home}component/indivConcert.jsp",
                      controller: "getDetailConcert"
                  })
                  .when("/profile", {
                      templateUrl : "${home}component/profile.jsp",
                      controller: "profileController"
                  })
                  .when("/playlist", {
                      templateUrl : "${home}component/playlist.jsp"
                  })
                  .when("/playlist/:id", {
                      templateUrl : "${home}component/indivPlaylist.jsp",
                      controller: "indivPlaylistController"
                  })
                  .when("/billing", {
                      templateUrl : "${home}component/upgrade.jsp",
                      controller: "premiumController"
                  })
                  .when("/search", {
                      templateUrl : "${home}component/search.jsp",
                      controller: "searchController"
                  })
                  .when("/friends", {
                      templateUrl : "${home}component/friends.jsp",
                      controller: "friendsController"
                  })
                  .when("/friends/:id", {
                      templateUrl : "${home}component/indivFriend.jsp",
                      controller: "friendsPlaylistController"
                  })
                  .otherwise({
                      redirectTo: "/"
                  });
              });
        </script>
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/browse.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/user.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/appSong.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/album.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/playlist.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/artists.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/library.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/upgrade.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/search.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/profile.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/friends.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/concert.js" />"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viking</title>
    </head>
    <body ng-app="myApp">

             <div id='feedback'>
                <div id=appPage >
                <div class=menu>

                  <!--Top Menu Logo and Browse-->
                  <div class=menutabs id=browserTabs>
                    <ul>
                      <li>
                        <img id=logo src=${home}/css/viking.png></img>
                      </li>
                      <b>
                      <li><a href="#/!">Browse</a></li>
                      </b>
                    </ul>
                  </div>

                  <!--Menu tabs-->
                  <div class=menutabs id=personalTabs>
                    <ul>
                      <h4>YOUR LIBRARY</h4>
                      <b>
                        <a href="#!history"> <li class="tab-link" data-tab="menutab-1">History</li></a>
                        <a href="#!library/songs"> <li class="tab-link" data-tab="menutab-2">Songs</li></a>
                        <a href="#!library/albums"><li class="tab-link" data-tab="menutab-3">Albums</li></a>
                        <a href="#!library/artists"><li class="tab-link" data-tab="menutab-4">Artists</li></a>
                        <a href="#!library/playlists"><li class="tab-link" data-tab="menutab-5">Playlist</li></a>
                      </b>
                    </ul>
                  </div>

                  <!--Playlist Tab-->
                  <div id=playlistTabs ng-controller="createPlaylistController">
                    <ul>
                      <a ng-click="showCreatePlaylistForm()"><li class=newPlaylist><img style="width: 20px" src="${home}/css/add-3.png">Create Playlist</li></a>
                    </ul>
                    <!-- create Playlist Modal -->
                    <div id="createPlaylistModal">
                      <div class="create modal">
                        <div class=modal-content>
                          <div>Create Playlist</div>
                          <hr class="style15" style="width:70%">
                            <form id="create-playlist-form">
                                Playlist Name:<input ng-model="newPlaylist.name" type="text"><br><br>
                                Playlist Description:<br><textarea ng-model="newPlaylist.description" rows=3 cols=40></textarea><br><br>
                                Upload thumbnail Image:<br><input id="create-playlist-thumbnail" type="file" file-model="newPlaylist.thumbnail" accept=".jpg"/><br>
                                <img id="create-thumbnail-preview" height="150px"/>  <br>
                                <div>
                                  <button type="submit" class="pageButton" ng-click="createPlaylist()">Create</button>
                                  <button class="pageButton" ng-click="closeCreatePlaylistForm()">Close</button>
                                </div>
                            </form>
                        </div>
                        </div>
                    </div>
                  </div>

                </div>

                <!--Bottom Player Navigation Bar  -->
                <div class=player-bar style="display: flex">
                  <div style="width: 25%">
                    <ul>
                    <img src="${home}/css/album/05GcLcffb84BOLzo7BMz9W.jpg" style="width:55px; float:left">
                    <div style="margin-left: 60px">Artist Name
                        <br/>Song Name
                    </p>
                    </div>
                  </div>
                  <div class=player style="width: 50%">
                    <div>
                      <img class="playerimg" id = "back" src="${home}/css/back.png"></img>
                      <img class="playerimg" id = "play" src="${home}/css/play-button.png"></img>
                      <img class="playerimg" id = "next" src="${home}/css/next.png"></img>
                      <img class="playerimg" id = "repeat" src="${home}/css/repeat.png"></img>
                      <img class="playerimg" id = "shuffle" src="${home}/css/shuffle.png"></img>
                      <img class="playerimg" id = "volumebtn" src="${home}/css/speaker-5.png" style="margin-right: 0px"></img>
                      <input type="range" style="vertical-align: top; margin-top: 25px" id="volume">
                    </div>
                    <div ><input type="range" id="seek" value="0" max=""/></div>
                  </div>
                  <div style="width: 25%">
                  </div>
                </div>


                <!--Browser Panel: Panel that will have anything that we want to show-->
                <div class=browse>
                  <!--Top Navigation Bar -->
                  <div class=topnav-bar>
                    <div id=search-bar style="float:left">
                      <form ng-submit="search()" ng-controller="mainController">
                          <input type="text" placeholder="Search" ng-model="query"></input>
                      </form>
                    </div>
                    <div id=user-buttons style="float:right">
                      <ul >
                        <li style="margin-right: 2%;"><a href="#!billing"><b>UPGRADE</b></a></li>
                        <li id=user-display-img><img src=${home}/css/user-4.png></img></li>
                        <li id=user-display-name style="margin-right: 2%;"><a href="#!profile">${user.username}</a></li>
                        <li><button id = dropdownbtn><b>></b></button>

                        <div class=user-dropdown-menu>
                          <a href="#!profile"><button>Account</button></a>
                          <a href="#!billing"><button>Billing</button></a>
                          <a href="#!friends"><button>Friends</button></a>

                          <c:choose>
                            <c:when test="${user.admin}">
                                <form id="to-admin-portal-form">
                                    <a><button>To Admin Portal</button></a>
                                </form>
                            </c:when>
                          </c:choose>
                          <form id="signout-form">
                              <a><button>Logout</button></a>
                          </form>

                        </div>
                       </li>
                      </ul>
                    </div>
                  </div>


                  <!--Advertisment Section-->
                  <div class="ad">

                    <div style="text-align: center; font-size: 50px; color: white; line-height: 200px;">ADVERTISEMENT HERE</div>
                  </div>
                  <div ng-controller="globalController">
                  <div ng-view></div>
                  </div>
                </div>
                </div>
                <div id='loading' class="error modal">
                    <div class="loading-modal-content">
                        <img src=${home}/css/loading.gif></img>
                    </div>
                </div>
                <div id="message-modal"  class="error modal">
                    <div class="modal-content">
                        <div id="message"></div>
                        <button class="close pageButton">Close</button>
                    </div>
                </div>
    </body>
    <script>
        $('#dropdownbtn').click(function(e){
            e.preventDefault();
            if($(this).hasClass('open')){
                $('.user-dropdown-menu').css("display", "none");
                $(this).removeClass('open');
            } else {
                $('.user-dropdown-menu').css("display", "block");
                $(this).addClass('open');
            }
        });
    </script>
</html>
