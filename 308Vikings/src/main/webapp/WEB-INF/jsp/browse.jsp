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
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/user.js" />"></script>        
        <script>
        var app = angular.module("myApp", ["ngRoute"]);
          app.config(function($routeProvider) {
              $routeProvider
              .when("/", {
                  templateUrl : "${home}component/overview.jsp"
              })
              .when("/recentlyplayed", {
                  templateUrl : "${home}component/recentlyPlayed.jsp"
              })
              .when("/songs", {
                  templateUrl : "${home}component/songs.jsp"
              })
              .when("/album", {
                  templateUrl : "${home}component/album.jsp"
              })
              .when("/artists", {
                  templateUrl : "${home}component/artists.jsp"
              })
              .when("/concert", {
                  templateUrl : "${home}component/concert.jsp"
              })
              .when("/profile", {
                  templateUrl : "${home}component/profile.jsp"
              })         
              .otherwise({
                  redirectTo: "/"
              }); 
          });
        </script>
        <script type="text/javascript" src="<c:url value="/js/album.js" />"></script>

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
                        <a href="#!recentlyplayed"> <li class="tab-link" data-tab="menutab">Recently Played</li></a>
                        <a href="#!songs"> <li class="tab-link" data-tab="menutab-2">Songs</li></a>
                        <a href="#!album"><li class="tab-link" data-tab="menutab-3">Albums</li></a>
                        <a href="#!artists"><li class="tab-link" data-tab="menutab-4">Artists</li></a>
                        <a href="#!concert"><li class="tab-link" data-tab="menutab-5">Concert</li></a>
                      </b>
                    </ul>
                  </div>

                  <!--Playlist Tab-->
                  <div id=playlistTabs>
                    <ul>
                      <li>PLAYLISTS</li>
                      <li class=newPlaylist><img style="width: 20px" src="${home}/css/add-3.png">New Playlist</li> 
                    </ul>
                  </div>
                </div>

                <!--Bottom Player Navigation Bar  -->
                <div class=player-bar>
                  <div class=player>
                    <img class="playerimg" id = "shuffle" src="${home}/css/shuffle.png"></img>
                    <img class="playerimg" id = "back" src="${home}/css/back.png"></img>
                    <img class="playerimg" id = "play" src="${home}/css/play-button.png"></img>
                    <img class="playerimg" id = "next" src="${home}/css/next.png"></img>
                    <img class="playerimg" id = "repeat" src="${home}/css/repeat.png"></img>
                    <div style="margin-left: 20%; margin-right: 20%;"><input type="range" id="seek" value="0" max=""/></div>
                  </div>
                </div>


                <!--Browser Panel: Panel that will have anything that we want to show-->
                <div class=browse>
                  <!--Top Navigation Bar -->
                  <div class=topnav-bar>
                    <div id=search-bar style="float:left">
                      <input placeholder="Search"></input >
                    </div>
                    <div id=user-buttons style="float:right">
                      <ul >
                        <li style="margin-right: 2%;"><button><b>UPGRADE</b></button></li>
                        <li id=user-display-img><img src=${home}/css/user-4.png></img></li>
                        <li id=user-display-name style="margin-right: 2%;"><a href="#!profile">${user.username}</a></li>
                        <li><button id = dropdownbtn><b>></b></button>
                        
                        <div class=user-dropdown-menu>
                          <a><button>Account Information</button></a>
                          <a><button>Billing</button></a>
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
                  <div class=ad>
                    <div style="text-align: center; font-size: 50px; color: white; line-height: 200px;">ADVERTISEMENT HERE</div>
                  </div>
                  <div ng-view></div>
                </div>
                </div>
    </body>
    <script>
        window.onload = function () {
            if (! localStorage.justOnce) {
                localStorage.setItem("justOnce", "true");
                window.location.reload();
            }
        }
        song = new Audio('${home}/download/AlanWalker-Fade.mp3');
        duration = song.duration;

        $(document).on('click', '#play', function(e) {
                        e.preventDefault();
                        $("#seek").prop("max", song.duration);
                        song.play();
                        $('#play').replaceWith('<img class="playerimg" id="pause" src="${home}/css/pause.png"></img>');
        }); 
        $(document).on('click','#pause', function(e) {
                        e.preventDefault();
                        song.pause();
                        $('#pause').replaceWith('<img class="playerimg" id="play" src="${home}/css/play-button.png"></img>');
        });
        $("#seek").bind("change", function() {
		song.currentTime = $(this).val();
	});
        song.addEventListener('timeupdate',function (){
		$("#seek").prop("value", song.currentTime);
	});

    </script>

    
</html>
