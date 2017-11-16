<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Browse</title>
    </head>
    <body>
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
    </body>
    <script>
       window.onload = function () {
            if (! localStorage.justOnce) {
                localStorage.setItem("justOnce", "true");
                window.location.reload();
            }
        }
    </script>
</html>
