<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>

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
        <script type="text/javascript" src="<c:url value="/js/artist_portal.js" />"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Vikings Artists</title>
    </head>
    <body>
        <div id='feedback'>
        <div id=appPage>
            <div class=menu>
                  <!--Top Menu Logo and Artist Options-->
                  <div class=menutabs id=browserTabs>
                    <ul>
                      <li>
                        <img id=logo src=${home}/css/viking.png></img>
                      </li> 
                      <b>
                      <h4>VIKINGS ARTIST PORTAL</h4>
                      </b>       
                    </ul>
                  </div>

                  <!--Menu tabs-->
                  <div class=menutabs id=personalTabs>
                    <ul>
                      <b>
                      <li class="tab-link" data-tab="menutab-1">Overview</li>
                      <li class="tab-link" data-tab="menutab-2">Edit Info</li>
                      <li class="tab-link" data-tab="menutab-3">Change Image</li>
                      <li class="tab-link" data-tab="menutab-4">View Summary</li>
                      <li class="tab-link" data-tab="menutab-5">Request Song</li>
                      <li class="tab-link" data-tab="menutab-6">Remove Song</li>
                      </b>
                    </ul>
                  </div>
            </div>
                
            <!--Browser Panel: Panel that will have anything that we want to show-->
            <div class=browse>
              <!--Top Navigation Bar -->
                <div class=topnav-bar>
                  <div id=user-buttons>
                    <ul>
                      <li id=user-display-img><img src=${home}/css/artist/${artist.id}.jpg></img></li>
                      <li id=user-display-name style="margin-right: 2%;"><a>${artist.name}</a></li>
                      <li>
                      <button class = dropdownbtn><b>></b></button>
                      <div class=user-dropdown-menu>
                        <form id="artist-signout-form">
                            <a><button>Logout</button></a>
                        </form>
                      </div>
                      </li>
                    </ul>
                  </div>
                </div>
                      
            <div class="pages" id="artist-overview">
              <div class='container'>
              <div>
                <img style="-top: 10px;" class=albumimg src="${home}/css/artist/${artist.id}.jpg">
                <h1 style="padding-top: 50px;">${artist.name}</h1>
                <div style="padding: 30px;" class=artistbio>${fn:replace(artist.bio, newLineChar, "<br/>")}</div>
              </div>
              </div>
            </div>
              
            <div class="pages" id="artist-summary">
              <div class='container'>
              <div>
                  <h1 style="padding-top: 50px;">Monthly Artist Summary</h1>
                  <h4>Select a month to view royalties and stats for:</h4>
                  <form id='artist-summary-month-picker'>
                      <input type='month' id='artist-summary-month'>
                      <input type='submit' id='artist-summary-submit'>
                  </form>
              </div>
                  <div id="artist-monthly-summary-result">
                    <h1 id="artist-monthly-summary-result-title"></h1>
                    <table id="artist-monthly-summary-result-table" class="songtable">
                        <thead>
                            <tr>
                                <td>Song ID</td>
                                <td>Name</td>
                                <td>Monthly Plays</td>
                                <td>Payment Date</td>
                                <td>Amount Paid</td>
                            </tr>
                        </thead>
                        <tbody>
                            
                        </tbody>
                    </table>
                  </div>
                  <div id="artist-monthly-summary-no-result">
                      <h1 id="artist-monthly-summary-no-result-title"></h1>
                  </div>
              </div>
            </div>
        </div>
        </div>
              
        <div id='artist-error' class="error modal">
               <div class="modal-content">
                   <div>Error</div>
                   <div id='error-message'></div>
                   <button id='error-message-close' class="close">CLOSE</button>
               </div>
        </div>
    </body>
    <script>
       window.onload = function () {
            if (! localStorage.justOnce) {
                localStorage.setItem("justOnce", "true");
                window.location.reload();
            }
        };
    </script>
</html>
