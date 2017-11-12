<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
        <script type="text/javascript" src="<c:url value="/js/artist_startup.js" />"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Vikings Artists</title>
    </head>
    <body>
        <div class=startUpPage>
           <form id="artist-signin-form">
            <div class= signin>
                <div><img src="../${home}/css/viking.png"></div>
                <div class=startUptitle><b>Vikings Artists</b></div>
                <div><input id='artist-login-id' placeholder="Artist ID"></div>
                <div><input id='artist-login-password' type="password" placeholder="Password"></div>
                <div><button class=login type="submit"><a>Login</a></button></div>
            </div>
           </form>
                
           <div id='artist-signin-error' class="error modal">
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
