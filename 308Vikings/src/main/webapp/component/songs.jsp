<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Songs</title>
    </head>
    <body>
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
    </body>
</html>