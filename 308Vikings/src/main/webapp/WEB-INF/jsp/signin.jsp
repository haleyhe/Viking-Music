<%-- 
    Document   : ajaxtest
    Created on : Oct 24, 2017, 5:00:46 PM
    Author     : bryankoelbel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AJAX Login Test</title>
    </head>
    <body>
        <div id="feedback"></div>
        <form id="login-form">
                <label>Username</label>
                <input type=text id="username">
                <p></p>
                <label>Password</label>
		<input type="password" class="form-control" id="password">
                <p></p>
                <button type="submit" id="login">Login</button>
	</form>
        <p></p>
        <p></p>
        <div id="userInfo"></div>
        
        <script>
	jQuery(document).ready(function($) {

		$("#login-form").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();

			login();

		});

	});

	function login() {
                console.log("Calling login Ajax function...");
		var username = $("#username").val();
		var password = $("#password").val();

		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "processLogin",
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

	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#feedback').html(json);
	}
        
        function displayLoginMessage(data) {
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
			url : "getSessionUser",
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
                profile += "<p><b>ZIP:</b> " + data["zip"] + "</p>";
                profile += "<p><b>Premium:</b> " + data["premium"] + "</p>";
                profile += "<p><b>Admin:</b> " + data["admin"] + "</p>";
		$('#userInfo').html(profile);
	}
        </script>
    </body>
</html>
