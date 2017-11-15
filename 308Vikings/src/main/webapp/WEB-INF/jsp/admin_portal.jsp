<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
        <script type="text/javascript" src="<c:url value="/js/admin_portal.js" />"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Vikings Admin</title>
    </head>
    <body>
        <div id='feedback'>
        <div id=appPage>
            <div class=menu>
                  <!--Top Menu Logo and Admin Options-->
                  <div class=menutabs id=browserTabs>
                    <ul>
                      <li>
                        <img id=logo src=${home}/css/viking.png></img>
                      </li> 
                      <b>
                      <h4>VIKINGS ADMIN PORTAL</h4>
                      </b>       
                    </ul>
                  </div>

                  <!--Menu tabs-->
                  <div class=menutabs id=personalTabs>
                    <ul>
                      <b>
                      <li class="tab-link" data-tab="menutab-1">Overview</li>
                      <li class="tab-link" data-tab="menutab-2">Create User</li>
                      <li class="tab-link" data-tab="menutab-3">Delete User</li>
                      <li class="tab-link" data-tab="menutab-4">Edit User</li>
                      <li class="tab-link" data-tab="menutab-5">Create Artist</li>
                      <li class="tab-link" data-tab="menutab-6">Delete Artist</li>
                      <li class="tab-link" data-tab="menutab-7">Edit Artist</li>
                      <li class="tab-link" data-tab="menutab-8">View Monthly Summary</li>
                      <li class="tab-link" data-tab="menutab-9">Manage Song Requests</li>
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
                      <li id=user-display-img><img src=${home}/css/viking.png></img></li>
                      <li id=user-display-name style="margin-right: 2%;"><a>${user.username}</a></li>
                      <li>
                      <button class=dropdownbtn><b>></b></button>
                      <div class=user-dropdown-menu>
                        <form id="admin-return-to-site-form">
                          <a><button>Return to Main Site</button></a>
                        </form>
                        <form id="admin-signout-form">    
                            <a><button>Logout</button></a>
                        </form>
                      </div>
                      </li>
                    </ul>
                  </div>
                </div>
                      
            <div class="pages" id="admin-overview">
              <div class='container' style="padding: 50px;">
              <div>
                  <h1>Welcome, admin!</h1>
                  <br />
                  <p>Select an action from the left menu.</p>
              </div>
              </div>
            </div>
                      
            <div class="pages" id="admin-create-user">
              <div class='container' style="padding: 20px;">
              <div>
                  <h1 style="padding-top: 50px;">Create User</h1>
                  <div id=signup>
                        <form id="create-user-form">
                          <div><input id="create-user-email" placeholder="Email"></div>
                          <div><input id="create-user-username" placeholder="Username"></div>
                          <div><input id="create-user-password" type="password" placeholder="Password"></div>
                          <div><input type ="date" id="create-user-dob" placeholder="Date of Birth"></div>
                          <div><input id="create-user-zipcode" placeholder="Zipcode"></div>
                          <div>Premium<input type="checkbox" id="create-user-premium"></div>
                          <div>Admin<input type="checkbox" id="create-user-admin"></div>
                          <div><button class=signupbtn type="submit">Create User</button></div>
                        </form>
                  </div>
              </div>    
              </div>
            </div>
                      
            <div class="pages" id="admin-edit-artist">
              <div class='container' style="padding: 50px;">
              <div>
                  <h1>Edit Artist</h1>
                  <div id="admin-edit-artist-artist-list" class=artistitems>
                  </div>
                  <form id="artist-edit-form">
                    <div class="signin">
                    <div><img id="artist-edit-thumbnail-preview" style="width: 300px; height: 300px" src=""></div>
                    <div>Image:</div>
                    <div><input id="artist-edit-thumbnail" type="file" name="thumbnail" accept=".jpg"/></div>
                    <div>Name:</div>
                    <div><input id="artist-edit-name" placeholder="Name" value=""></div>
                    <br/>
                    <div>Bio:</div>
                    <div><textarea id="artist-edit-bio"></textarea></div>
                    <div>Current Related Names:</div>
                    <div id="artist-related-names"></div>
                    <div>Add New Name:</div>
                    <div><input id="artist-edit-first-name" placeholder="First Name"> <input id="artist-edit-last-name" placeholder="Last Name"></div>
                    <br/>
                    <div>Current Genres:</div>
                    <div id="artist-genres"></div>
                    <div>Add New Genre:</div>
                    <div><input id="artist-edit-genre" placeholder="Genre"></div>
                    <br/>
                    <div><button id="artist-edit-submit" class=signupbtn type="submit">Submit Changes</button></div>
                    </div>
                </form>
              </div>     
            </div>
            </div>
              
            <div class="pages" id="admin-summary">
              <div class='container' style="padding: 20px;">
              <div>
                  <h1 style="padding-top: 50px;">Monthly Admin Summary</h1>
                  <h4>Select a month to view revenue and royalties for:</h4>
                  <form id='admin-summary-month-picker'>
                      <input type='month' id='admin-summary-month'>
                      <input type='submit' id='admin-summary-submit'>
                  </form>
              </div>
                  <div id="admin-monthly-summary-result">
                    <h1 id="admin-monthly-summary-result-title"></h1>
                    <table id="admin-monthly-summary-result-table" class="songtable">
                        <h1>Artist Payments</h1>
                        <thead>
                            <tr>
                                <td>Artist ID</td>
                                <td>Name</td>
                                <td>Monthly Plays</td>
                                <td>Payment Date</td>
                                <td>Amount Paid</td>
                            </tr>
                        </thead>
                        <tbody>
                            
                        </tbody>
                    </table>
                    <table id="admin-monthly-summary-result-revenue-table" class="songtable">
                        <h1>User Revenue</h1>
                        <thead>
                            <tr>
                                <td>User ID</td>
                                <td>Username</td>
                                <td>Card Number</td>
                                <td>Payment Date</td>
                                <td>Amount Paid</td>
                            </tr>
                        </thead>
                        <tbody>
                            
                        </tbody>
                    </table>
                  </div>
                  <div id="admin-monthly-summary-no-result">
                      <h1 id="admin-monthly-summary-no-result-title"></h1>
                  </div>
              </div>
            </div>
        </div>
        </div>
              
        <div id='admin-error' class="error modal">
               <div class="modal-content">
                   <div>Error</div>
                   <div id='error-message'></div>
                   <button id='error-message-close' class="close">CLOSE</button>
               </div>
        </div>
        <div id='admin-success' class="success modal">
               <div class="modal-content">
                   <div>Success</div>
                   <div id='success-message'></div>
                   <button id='success-message-close' class="close">CLOSE</button>
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
