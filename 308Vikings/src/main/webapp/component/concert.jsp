<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <title>Viking - Concert</title>
    </head>
    <body>
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

        <div class=pages id=indivConcertPage >
          <div style="float: left;"">
            <img style="float: left; width: 150px" class=concertdateimg src=${home}/css/calendar-4.png>
            <div style="float: right; margin-top: 100px;">Concert</br><b style="font-size: 50px">Nas</b></br></div>
            <div><button>Find Ticket</button></div>         
            <div><hr class="style14" style="width:70%"></div>
            <div style="margin-left: 50px">Detail<br>Oct 18 2017<br>Rialto Theatre</div>
          </div>
        </div>        
    </body>
</html>