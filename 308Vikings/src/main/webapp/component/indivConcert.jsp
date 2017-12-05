<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <title>Viking - Concert</title>
    </head>
    <body>
        <div class="pages" id=indivConcertPage>
            <div style = "padding-left: 50px;">
                <h1 style = "margin-bottom: 0px; padding-top:30px;">{{concert.concert.venue.name}} </h1>
                <h1 style = "margin-top: 0px;">{{concert.concert.date | date:'d MMMM yyyy'}}</h1>
                
                <br/>

                <hr class = "style14">
                <br/>
                <h2> Tickets : </h2>
                <h3 style = "padding-left: 100px;"><a href="{{concert.concert.ticketingUrl}}" target="_blank">Get Tickets Here </a></h3>
                
                <br/>
                <h2> Venue Address: </h2>
                <h3 style = "margin-bottom: 0px;padding-left:100px;"> {{concert.concert.venue.address.street}}</h3>
                <h3 style = "margin-top:0px;padding-left:100px;"> {{concert.concert.venue.address.city}}, {{concert.concert.venue.address.state}} {{concert.concert.venue.address.zip}} </h3>
                
                <br/>
                <hr class = "style14">
                <br/>
                <br/>
                <h2 align = "center"> Artists Performing</h2>

                <div class=artistitems>
                    <div ng-repeat="artist in concert.concert.artists">
                    <a href = "#!/artists/{{artist.id}}"><img class=artistimg id ="{{artist.id}}" ng-src="${home}/css/artist/{{artist.id}}.jpg"></img></a>
                    <li class = "artistname"><a id="{{artist.id}}" href= "#!/artists/{{artist.id}}">{{artist.name}}</a></li>
                </div>
              </div>
            </div>
        </div>
    </body>
</html>
