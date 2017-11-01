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
           <div><img src=<c:url value="/css/viking.png" />></div>
           <div class=startUptitle><b>VIKING</b></div>
           <div><input id='login-username' placeholder="Username"></div>
           <div><input id= 'login-password' placeholder="Password"></div>
           <div><button class=login type="submit"><a href="#Browser">Login</a></button></div>
           <div><button class=loginfacebook>Login with Facebook</button></div>
           <div class=registerbtn>Register now</div>
           </div>
           </form>

        <div class=signup>
            <div class=modal-content>
            <div class=startUptitle>VIKING</div>
            <hr class="style15" style="width:70%">
            <form action="" method="POST">
                <div><input class="email" placeholder="Email"></div>
                <div><input class="signup_username" placeholder="Username"></div>
                <div><input class="signup_password" placeholder="Password"></div>
                <div><input class="rePassword" placeholder="Re-Password"></div>
                <div><input class="dob" placeholder="Date of Birth"></div>
                <div><input class="zipcode" placeholder="Zipcode"></div>
                <div><button class=signupbtn>Sign Up</button></div>
            </form>
        </div>
      </div>
    </div>
           <div id='feedback'></div> 
    </body>
    
    <script>
        jQuery(document).ready(function ($) {
    $(".signupbtn").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        signup();
    });
    $("#signin-form").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        login();
    });
});

function signup() {
    console.log("Calling signup Ajax function...");
    var newUser = {};
    newUser["id"] = null;
    newUser["username"] = $(".signup_username").val();
    newUser["password"] = $(".signup_password").val();
    newUser["email"] = $(".email").val();
    newUser["dateOfBirth"] = $(".dob").val();
    newUser["zip"] = $(".zipcode").val();
    newUser["premium"] = false;
    newUser["admin"] = false;
    newUser["facebookId"] = null;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "${home}UserAccount/registerUser",
        data: JSON.stringify(newUser),
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            displaySignupMessage(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });

}

function login() {
    console.log("Calling login Ajax function...");
    var loginRequest = {};
    loginRequest["username"] = $("#login-username").val();
    loginRequest["password"] = $("#login-password").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "${home}UserAccount/processLogin",
        data: JSON.stringify(loginRequest),
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            displayLoginMessage(data);
            if (data) {
                getUserInfoFromSession();
            }
        },
        error: function (e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });

}

function displaySignupMessage(data) {
    document.getElementById("feedback").style.display = "inline";
    if (data) {
        $('#feedback').html("<h4>Signup Successful</h4>");
    } else {
        $('#feedback').html("<h4>Signup Failed. Username or Email taken.</h4>");
    }
}

function display(data) {
    var json = "<h4>Ajax Response</h4><pre>"
            + JSON.stringify(data, null, 4) + "</pre>";
    $('#feedback').html(json);
}

function displayLoginMessage(data) {
    document.getElementById("feedback").style.display = "inline";
    if (data) {
        $('#feedback').html("<h4>Login Successful</h4>");
    } else {
        $('#feedback').html("<h4>Login Failed</h4>");
    }
}
function getUserInfoFromSession() {
    console.log("Calling User from session Ajax function...");

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "${home}UserAccount/getSessionUser",
        data: {},
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            displayUserInfo(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

function displayUserInfo(data) {
    // hide login form
    document.getElementById("signin-form").style.display = "none";

    var profile = "<h1>Vikings - Profile</h1>";
    profile += "<p><b>User ID:</b> " + data["id"] + "</p>";
    profile += "<p><b>Username:</b> " + data["username"] + "</p>";
    profile += "<p><b>Email:</b> " + data["email"] + "</p>";    
    profile += "<p><b>Date of Birth:</b> " + data["dateOfBirth"] + "</p>";
    profile += "<p><b>ZIP:</b> " + data["zip"] + "</p>";
    profile += "<p><b>Premium:</b> " + data["premium"] + "</p>";
    profile += "<p><b>Admin:</b> " + data["admin"] + "</p>";
    $('#userInfo').html(profile);
}
    </script>
</html>
