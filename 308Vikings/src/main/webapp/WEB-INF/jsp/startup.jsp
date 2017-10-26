<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AJAX Signup Test</title>
    </head>
    <body>
        <div id="feedback"></div>
        <form id="signup-form">
                <h4>Sign Up</h4>
                <p></p>
                <label>Username</label>
                <input type=text id="username">
                <p></p>
                <label>Password</label>
        <input type="password" class="form-control" id="password">
                <p></p>
                <label>Email Address</label>
        <input type="text" class="form-control" id="email">
                <p></p>
                <label>Birth Date</label>
                <input type="date" class="form-control" id="dateOfBirth">
                <p></p>
                <label>ZIP Code</label>
                <input type="text" class="form-control" id="zip">
                <p></p>
                <button type="submit" id="signup">Sign Up</button>
    </form>
        <p></p>
        <p></p>
        <form id="display-login-form">
            <button type="submit" id="display-login-form-button">Display Login</button>
        </form>
        <form id="login-form">
                <h4>Sign In</h4>
                <p></p>
                <label>Username</label>
                <input type=text id="login-username">
                <p></p>
                <label>Password</label>
        <input type="password" class="form-control" id="login-password">
                <p></p>
                <button type="submit" id="login">Login</button>
    </form>
        <p></p>
        <p></p>
        <div id="userInfo"></div>
        
        <script>
            jQuery(document).ready(function($) {
                document.getElementById("login-form").style.display="none";

                $("#signup-form").submit(function(event) {
                    // Prevent the form from submitting via the browser.
                    event.preventDefault();

                    signup();

                });
                
                $("#display-login-form").submit(function(event) {
                    // Prevent the form from submitting via the browser.
                    event.preventDefault();
                    // hide signup and show the login form
                    document.getElementById("feedback").style.display="none";
                    document.getElementById("signup-form").style.display="none";
                    document.getElementById("display-login-form").style.display="none";
                    document.getElementById("login-form").style.display="inline";

                });
                
                $("#login-form").submit(function(event) {
                    // Prevent the form from submitting via the browser.
                    event.preventDefault();

                    login();

                });

            });

            function signup() {
                console.log("Calling signup Ajax function...");
                var newUser = {};
                newUser["id"] = null;
                newUser["username"] = $("#username").val();
                newUser["password"] = $("#password").val();
                newUser["email"] = $("#email").val();
                newUser["dateOfBirth"] = $("#dateOfBirth").val();
                newUser["zip"] = $("#zip").val();
                newUser["premium"] = false;
                newUser["admin"] = false;
                newUser["facebookId"] = null;

                $.ajax({
                    type : "POST",
                    contentType : "application/json",
                    url : "${home}registerUser",
                    data : JSON.stringify(newUser),
                    dataType : 'json',
                    timeout : 100000,
                    success : function(data) {
                        console.log("SUCCESS: ", data);
                        displaySignupMessage(data);
                    },
                    error : function(e) {
                        console.log("ERROR: ", e);
                        display(e);
                    },
                    done : function(e) {
                        console.log("DONE");
                    }
                });

            }
        
            function login() {
                console.log("Calling login Ajax function...");
                var username = $("#login-username").val();
                var password = $("#login-password").val();

                $.ajax({
                    type : "GET",
                    contentType : "application/json",
                    url : "${home}processLogin",
                    data : { username: username, password: password },
                    dataType : 'json',
                    timeout : 100000,
                    success : function(data) {
                        console.log("SUCCESS: ", data);
                        displayLoginMessage(data);
                        if (data) {
                            getUserInfoFromSession();
                        }
                    },
                    error : function(e) {
                        console.log("ERROR: ", e);
                        display(e);
                    },
                    done : function(e) {
                        console.log("DONE");
                    }
                });

            }
        
            function displaySignupMessage(data) {
                document.getElementById("feedback").style.display="inline";
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
                document.getElementById("feedback").style.display="inline";
                if (data) {
                   $('#feedback').html("<h4>Login Successful</h4>");
                } else {
                    $('#feedback').html("<h4>Login Failed</h4>");
                }
            }
        
            function getUserInfoFromSession() {
                console.log("Calling User from session Ajax function...");
                
                $.ajax({
                    type : "GET",
                    contentType : "application/json",
                    url : "${home}getSessionUser",
                    data : {},
                    dataType : 'json',
                    timeout : 100000,
                    success : function(data) {
                       console.log("SUCCESS: ", data);
                        displayUserInfo(data);
                    },
                    error : function(e) {
                        console.log("ERROR: ", e);
                        display(e);
                    },
                    done : function(e) {
                        console.log("DONE");
                    }
                });
            }
        
            function displayUserInfo(data) {
                // hide login form
                document.getElementById("login-form").style.display="none";
                
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
    </body>
</html>
