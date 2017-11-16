<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Artists</title>
    </head>
    <body>
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
    </body>
</html>