<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Recently Played</title>
    </head>
    <body>
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
    </body>
</html>
