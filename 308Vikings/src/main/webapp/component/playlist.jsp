<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Playlist</title>
    </head>
    <body>
        <div class="pages" id=playListPage>
          <div>
            <img style="margin-top: 0"class=playlistimg src="${home}/css/music-player-3.png">
            <h1 style="padding-top: 50px;">New Playlist</h1>
            <button style="margin-bottom: 100px;">Play</button>
            <hr class="style14">
            <div style="text-align: center;"><h4>The Playlist is empty. Please add songs.</h4></div>

          </div>
        </div>
    </body>
</html>