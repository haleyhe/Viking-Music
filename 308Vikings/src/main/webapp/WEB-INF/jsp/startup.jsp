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
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/user.js" />"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Viking</title>
    </head>
    <body>
        <div class=startUpPage>
           <form id="signin-form">
           <div class= signin>
           <div><img src="../${home}/css/viking.png"></div>
           <div class=startUptitle><b>VIKING</b></div>
           <div><input id='login-username' placeholder="Username"></div>
           <div><input id= 'login-password' type="password" placeholder="Password"></div>
           <div><button class=login type="submit"><a>Login</a></button></div>
           <div><button class=loginfacebook>Login with Facebook</button></div>
           <div class=registerbtn>Register now</div>
           </div>
           </form>

        <div id=signup>
            <div class=modal-content>
            <div class=startUptitle>VIKING</div>
            <hr class="style15" style="width:70%">
            <form id="signup-form">
                <div><input id="email" placeholder="Email"></div>
                <div><input id="signup_username" placeholder="Username"></div>
                <div><input id="signup_password" type="password" placeholder="Password"></div>
                <div><input type ="date" id="dob" placeholder="Date of Birth"></div>
                <div><input id="zipcode" placeholder="Zipcode"></div>
                <div><button class=signupbtn type="submit">Sign Up</button></div>
            </form>
        </div>
      </div>
    </div>
           <div id="feedback"></div>
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
