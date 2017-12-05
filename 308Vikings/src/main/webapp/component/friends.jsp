<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
    </head>
    <body>
        <div ng-controller="friendsController">
        <div id="friendspage">
        <h1 style = "padding-left: 2%; margin-top:40px;"> Friends </h1>
          <div class="container">
              <form name = "friendForm" align="center">
                  <p style="font-size: 20px; font-weight: bold;">Add Friend: <input type ="text" name="friendName"><input type="submit" value="Submit" ng-click="addFriend()"></p>
              </form>
              <br/>
              <br/>
                <div class='container' ng-show="friends.length !== 0" align="center">
                    <table>
                        <tr ng-repeat="friend in friends">
                            <td style="padding-right: 400px; padding-bottom: 5px;">
                                <a href="#!friends/{{friend.id}}?username={{friend.name}}"><input type="hidden" value="{{friend.name}}" name="user_id" />{{friend.name}}</a>
                            </td>
                            <td>
                                <a ng-click="deleteFriend(friend.id)"><img src="${home}css/remove.png" width="15px"></a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class='container' style="padding: 20px;" ng-show="friends.length === 0">
                    <h2 align = "center"> You have no friends. You can add friends above.</h2>
                </div>
          </div>
        </div>
        <div id='startup-error' class="error modal">
               <div class="modal-content">
                   <div id = "friendMessage"></div>
                   <button class="close">CLOSE</button>
               </div>
        </div>
    </body>
</html>
