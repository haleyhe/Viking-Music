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
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/user.js" />"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viking</title>
    </head>
    <body>
        
             <div id='feedback'>
              <div id=appPage> 
                <div class=menu>

                  <!--Top Menu Logo and Browse-->
                  <div class=menutabs id=browserTabs>
                    <ul>
                      <li>
                        <img id=logo src=${home}/css/viking.png></img>
                      </li> 
                      <b>
                      <li><a>Browse</a></li>
                      </b>       
                    </ul>
                  </div>

                  <!--Menu tabs-->
                  <div class=menutabs id=personalTabs>
                    <ul>
                      <h4>YOUR LIBRARY</h4>
                      <b>
                      <li class="tab-link" data-tab="menutab-1">Recently Played</li>
                      <li class="tab-link" data-tab="menutab-2">Songs</li>
                      <li class="tab-link" data-tab="menutab-3">Albums</li>
                      <li class="tab-link" data-tab="menutab-4">Artists</li>
                      <li class="tab-link" data-tab="menutab-5">Concert</li>
                      </b>
                    </ul>
                  </div>

                  <!--Playlist Tab-->
                  <div id=playlistTabs>
                    <ul>
                      <li>PLAYLISTS</li>
                      <li class=newPlaylist><img style="width: 20px" src="${home}/css/add-3.png"> New Playlist</li> 
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
                    <input type="range" class="seek" value="0" max=""/>
                  </div>
                </div>


                <!--Browser Panel: Panel that will have anything that we want to show-->
                <div class=browse>
                  <!--Top Navigation Bar -->
                  <div class=topnav-bar>
                    <div id=search-bar>
                      <input placeholder="Search"></input >
                    </div>
                    <div id=user-buttons>
                      <ul>
                        <li style="margin-right: 2%;"><button><b>UPGRADE</b></button></li>
                        <li id=user-display-img><img src=${home}/css/user-4.png></img></li>
                        <li id=user-display-name style="margin-right: 2%;"><a>viking123</a></li>
                        <li>
                        <button class = dropdownbtn><b>></b></button>

                        <div class=user-dropdown-menu>
                          <a><button>Account Information</button></a>
                          <a><button>Billing</button></a>
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

                  <!--All the pages-->

                  <div class="pages" id=playListPage>
                    <div>
                      <img style="margin-top: 0"class=playlistimg src="${home}/css/music-player-3.png">
                      <h1 style="padding-top: 50px;">New Playlist</h1>
                      <button style="margin-bottom: 100px;">Play</button>
                      <hr class="style14">
                      <div style="text-align: center;"><h4>The Playlist is empty. Please add songs.</h4></div>

                    </div>
                  </div>

                  <!--Recently Played Page-->
                  <div class=pages id=recentlyplayedpage>
                    <div class="container">
                      <div id="menutab-1" class="menutab-content current">
                      <h2>Recently Played</h2>
                      <hr class="style14">
                        <h3>Songs</h3>
                        <table class=songtable>
                          <tr>
                            <td></td>
                            <td>Title</td>
                            <td>Artist</td>
                            <td>Duration</td>
                          </tr>
                          <tr>
                            <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                            <td>Tell Me You Love Me</td>
                            <td>Demi Lovato</td>
                            <td>3:08</td>
                          </tr>
                          <tr>
                            <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                            <td>Mi Gente</td>
                            <td>J Balvin, Willy William</td>
                            <td>3:23</td>
                          </tr>
                        </table>
                        <h3>Albums</h3>            
                          <div class=albumitems style="margin-bottom: 7%;">
                            <div>
                            <img class=albumimg src="${home}/css/album/MiGente.jpg"></img>
                            <li class=albumname>Mi Gente Feat. Beyonce</li>
                            <li class=albumartist>J Balvin, Willy William</li>     
                            </div>
                            <div>
                            <img class=albumimg src="${home}/css/album/TellMeYouLoveMe.png"></img>
                            <li class=albumname>Tell Me You Love Me</li>
                            <li class=albumartist>Demi Lovato</li>     
                            </div>
                            <div>
                            <img class=albumimg src="${home}/css/album/TheBiggerArtist.jpg"></img>
                            <li class=albumname>The Bigger Artist</li>
                            <li class=albumartist>A Boogie Wit da Hoodie</li>     
                            </div>
                          </div>     
                        <h3>Playlist</h3>
                          <div class=playlistitems style="margin-bottom:7%">
                            <div>
                              <img class=albumimg src="${home}/css/music-player-2.png"></img>
                              <li class=albumname>Someone's Playlist</li>
                              <li class=albumartist>2 Songs</li>     
                            </div>
                          </div>
                      </div>
                    </div>
                  </div>

                  <!--Songs Page-->
                  <div class=pages id=songpage>
                    <div class="container">
                      <div id="menutab-2" class="menutab-content">
                      <h2>Songs</h2>
                      <hr class="style14">
                      <table class=songtable>
                        <tr>
                          <td></td>
                          <td>Title</td>
                          <td>Artist</td>
                          <td>Duration</td>
                        </tr>
                        <tr>
                          <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                          <td>Sorry Not Sorry</td>
                          <td>Demi Lovato</td>
                          <td>3:04</td>
                        </tr>
                        <tr>
                          <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                          <td>Tell Me You Love Me</td>
                          <td>Demi Lovato</td>
                          <td>3:24</td>
                        </tr>
                        <tr>
                          <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                          <td>Mi Gente</td>
                          <td>J Balvin, Willy William</td>
                          <td>3:09</td>
                        </tr>
                        <tr>
                          <td><img  class='play-btn' src=${home}/css/play-button-1.png></img></td>
                          <td>No Promises</td>
                          <td>A Boogie Wit da Hoodie</td>
                          <td>4:01</td>
                        </tr>
                      </table>
                      </div>
                    </div>
                  </div>

                  <!--Album Pages-->
                  <div class=pages id=albumpage>
                    <div class="container">
                      <div id="menutab-3" class="menutab-content">
                      <h2>Albums</h2>
                      <hr class="style14">
                        <div class=albumitems>
                          <div>
                          <img class=albumimg src="${home}/css/album/MiGente.jpg"></img>
                          <li class=albumname>Mi Gente Feat. Beyonce</li>
                          <li class=albumartist>J Balvin, Willy William</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/TellMeYouLoveMe.png"></img>
                          <li class=albumname>Tell Me You Love Me</li>
                          <li class=albumartist>Demi Lovato</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/TheBiggerArtist.jpg"></img>
                          <li class=albumname>The Bigger Artist</li>
                          <li class=albumartist>A Boogie Wit da Hoodie</li>     
                          </div>
                          <div>  
                          </div>          
                        </div>    
                      </div>

                    </div>
                  </div>

                  <!--Artist Pages-->
                  <div class=pages id=artistpage>
                    <div class="container">
                      <div id="menutab-4" class="menutab-content">
                      <h2>Artist</h2>
                      <hr class="style14">
                        <div class=artistitems>
                          <div>
                            <img class=artistimg src="${home}/css/artist/jbalvin.jpg"></img>
                            <li class=artistname>J Balvin</li>
                            <li class=songnumber>1 Songs</li>     
                          </div>
                          <div>
                            <img class=artistimg src="${home}/css/artist/demilovato.jpg"></img>
                            <li class=artistname>Demi Lovato</li>
                            <li class=songnumber>2 Songs</li>     
                          </div>
                          <div>
                            <img class=artistimg src="${home}/css/artist/aboogiewitdahoodie.jpg"></img>
                            <li class=artistname>A Boogie Wit da Hoodie</li>
                            <li class=songnumber>1 Songs</li>     
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <!--Concert Pages-->
                  <div class=pages id=concertpage>
                    <div class="container">
                      <div id="menutab-5" class="menutab-content">
                      <h2>Concerts</h2>
                      <hr class="style14">
                      </div>
                      <table class=concerttable>
                        <tr>
                          <td><img class=concert-btn src=${home}/css/user-7.png></img></td>
                          <td>Nas</td>
                          <td>Mon Oct 18</td>
                          <td>Rialto Theatre</td>
                        </tr>
                      </table>
                    </div>
                  </div>


                  <div class=pages id=musicpage>
                    <div class="container">
                      <ul class="musictabs">
                        <li class="tab-link current" data-tab="musictab-1">Overview</li>
                        <li class="tab-link" data-tab="musictab-2">Newly Released</li>
                        <li class="tab-link" data-tab="musictab-3">Genres & Moods</li>
                        <li class="tab-link" data-tab="musictab-4">Concerts</li>
                        <li class="tab-link" data-tab="musictab-5">Chart</li>
                      </ul>

                      <div id="musictab-1" class="musictab-content current">
                        <div class=albumitems>
                          <div>
                          <img class=albumimg src="${home}/css/album/MiGente.jpg"></img>
                          <li class=albumname>Mi Gente Feat. Beyonce</li>
                          <li class=albumartist>J Balvin, Willy William</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/TellMeYouLoveMe.png"></img>
                          <li class=albumname>Tell Me You Love Me</li>
                          <li class=albumartist>Demi Lovato</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/TheBiggerArtist.jpg"></img>
                          <li class=albumname>The Bigger Artist</li>
                          <li class=albumartist>A Boogie Wit da Hoodie</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/NowDeluxe.png"></img>
                          <li class=albumname>Now (Deluxe)</li>
                          <li class=albumartist>Shania Twain</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/YoungerNow.png"></img>
                          <li class=albumname>Younger Now</li>
                          <li class=albumartist>Miley Cyrus</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/savagethank.jpg"></img>
                          <li class=albumname>Savage</li>
                          <li class=albumartist>Tank</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/music-player-2.png"></img>
                          <li class=albumname>Seven Days</li>
                          <li class=albumartist>PARTYNEXTDOOR</li>     
                          </div>          
                        </div>  
                      </div>

                      <div id="musictab-2" class="musictab-content">
                        <ul class=musictabs>
                          <li>Newly Released</li>
                        </ul>
                      </div>

                      <div id="musictab-3" class="musictab-content">
                        <ul class=musictabs>
                          <li>Genres & Moods</li>
                        </ul>
                      </div>

                      <div id="musictab-4" class="musictab-content">
                        <ul class=musictabs>
                          <li>Concert Recommendations</li>
                        </ul>
                      </div>

                      <div id="musictab-5" class="musictab-content">
                        <ul class=musictabs>
                          <li>Chart</li>
                        </ul>
                      </div>
                    </div>
                  </div>

                  <div class=pages id=profilepage>
                    <div class="container">

                    <ul class="tabs">
                      <li class="tab-link current" data-tab="tab-1">Overview</li>
                      <li class="tab-link" data-tab="tab-2">View Monthly Summary</li>
                    </ul>

                    <div id="tab-1" class="tab-content current">
                      <ul class=userinfo>
                        <li style="font-size: 2em; font-weight: bold">Profile</li>
                        <li id="field">Username</li>
                        <li >viking123</li>
                        <li id="field">Email</li>
                        <li>viking123@gmail.com</li>
                        <li id="field">Date of Birth</li>
                        <li>09/30/17</li>
                        <li id="field">Country</li>
                        <li>US</li>
                      </ul>        
                    </div>
                    <div id="tab-2" class="tab-content">
                      <ul class=recentlyplayedartist>
                        <li style="font-size: 2em; font-weight: bold">View Monthly Summary</li>
                      </ul>
                    </div>
                    </div>
                  </div>

                  <div class=pages id=libindivAlbumPage>
                    <div>
                      <div style="margin-left: 50px;"><h1>Album From Your Library</h1></div>
                      <img style="margin-top: 10px;" class=albumimg src="${home}/css/album/MiGente.jpg"></img>   
                    </div>
                    <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
                      <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">Mi Gente</h1>
                      <h3>By J Balvin</h3>
                      <p style="margin-bottom: 120px;">
                      <a class=viewFullAlbum>View Full Album</a>
                      <button>Save</button>
                      </p>
                      <hr class="style14" style="width:70%">
                    </div>

                    <table class=songtable>
                      <tr>
                        <td></td>
                        <td>Title</td>
                        <td>Artist</td>
                        <td>Date</td>
                      </tr>
                      <tr>
                        <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                        <td>Mi Gente</td>
                        <td>J Balvin, Willy William</td>
                        <td>09/29/2017</td>
                      </tr>
                    </table>
                  </div>

                  <div class=pages id=indivAlbumPage>
                    <div>
                      <img style="margin: 10px;-top: 10px;" class=albumimg src="${home}/css/album/MiGente.jpg"></img>   
                    </div>
                    <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
                      <h1 style="margin-top: 30px; margin-bottom: 0px; font-size: 3em">Mi Gente</h1>
                      <h3>By J Balvin</h3>
                      <p style="margin-bottom: 120px;">
                      <button>Play</button>
                      <button>Save</button>
                      </p>
                      <hr class="style14" style="width:70%">
                    </div>

                    <table class=songtable>
                      <tr>
                        <td></td>
                        <td>Title</td>
                        <td>Artist</td>
                        <td>Duration</td>
                      </tr>
                      <tr>
                        <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                        <td>Mi Gente</td>
                        <td>J Balvin, Willy William</td>
                        <td>3:08</td>
                      </tr>
                      <tr>
                        <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                        <td>Sorry Not Sorry</td>
                        <td>J Balvin</td>
                        <td>4:03</td>
                      </tr>
                      <tr>
                        <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                        <td>Tell Me You Love Me</td>
                        <td>J Balvin</td>
                        <td>3:31</td>
                      </tr>
                        <td><img  class='play-btn' src=${home}/css/play-button-1.png></img></td>
                        <td>No Promises</td>
                        <td>J Balvin</td>
                        <td>3:59</td>
                      </tr>

                    </table>
                    <h3>More by J Balvin</h3>
                    <hr class="style14" style="width:70%">
                      <div class=albumitems>
                          <div>
                          <img class=albumimg src="${home}/css/album/energia.jpg"></img>
                          <li class=albumname>Energia Lado B</li>
                          <li class=albumartist>J Balvin</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/energia.jpg"></img>
                          <li class=albumname>Energia</li>
                          <li class=albumartist>J Balvin</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/lafamilia.jpg"></img>
                          <li class=albumname>La Familia B Sides</li>
                          <li class=albumartist>J Balvin</li>     
                          </div>
                          <div>
                          <img class=albumimg src="${home}/css/album/lafamilia.jpg"></img>
                          <li class=albumname>La Familia</li>
                          <li class=albumartist>J Balvin</li>     
                          </div>
                      </div>
                  </div>        

                  <div class=pages id=libindivArtistPage>
                    <div>
                      <div style="margin-left: 50px;"><h1>Artist From Your Library</h1></div>
                      <img style="margin-top: 10px;" class=artistimg src="${home}/css/artist/jbalvin.jpg" style="margin-bottom: 30px"></img>    
                    </div>
                    <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">
                      <a class=viewFullArtist>View Full Artist</a>
                    <button>Follow</button>
                    </div>
                    <hr class="style14" style="width:70%">

                    <table class=songtable>
                      <tr>
                        <td></td>
                        <td>Title</td>
                        <td>Artist</td>
                        <td>Duration</td>
                      </tr>
                      <tr>
                        <td><img class='play-btn' src=${home}/css/play-button-1.png></img></td>
                        <td>Mi Gente</td>
                        <td>J Balvin, Willy William</td>
                        <td>09/29/2017</td>
                      </tr>
                    </table>
                  </div>

                  <div class=pages id=indivArtistPage>
                    <div>
                      <h1 style="margin-left: 50px;">J Balvin</h1>
                    </div>
                  </div>

                  <div class=pages id=indivConcertPage >
                    <div style="float: left;"">
                      <img style="float: left; width: 150px" class=concertdateimg src=${home}/css/calendar-4.png>
                      <div style="float: right; margin-top: 100px;">Concert</br><b style="font-size: 50px">Nas</b></br></div>
                      <div><button>Find Ticket</button></div>         
                      <div><hr class="style14" style="width:70%"></div>
                      <div style="margin-left: 50px">Detail<br>Oct 18 2017<br>Rialto Theatre</div>
                    </div>
                  </div>
                </div>
              </div>

           </div> 
    </body>
    <script>
        song = new Audio('${home}/download/AlanWalker-Fade.mp3');
        duration = song.duration;

        $(document).on('click', '#play', function(e) {
                        e.preventDefault();
                        song.play();
                        $('#play').replaceWith('<img class="playerimg" id="pause" src="${home}/css/pause.png"></img>');
        }); 
        $(document).on('click','#pause', function(e) {
                        e.preventDefault();
                        song.pause();
                        $('#pause').replaceWith('<img class="playerimg" id="play" src="${home}/css/play-button.png"></img>');
        });
        
    </script>
</html>
