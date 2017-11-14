 jQuery(document).ready(function ($) {
    $("#signup-form").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        signup();
    });
    $("#signin-form").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        login();
    });
    
    $("#signout-form").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        logout();
    });
});

function signup() {
    var newUser = {};
    newUser["id"] = null;
    newUser["username"] = $("#signup_username").val();
    newUser["password"] = $("#signup_password").val();
    newUser["email"] = $("#email").val();
    newUser["dateOfBirth"] = $("#dob").val();
    newUser["zip"] = $("#zipcode").val();
    newUser["premium"] = false;
    newUser["admin"] = false;
    newUser["facebookId"] = null;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/registerUser",
        data: JSON.stringify(newUser),
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            $(".error.modal").css("display", "block");
            $('#error').html(data['error']);
        },
        error: function (e) {
            display(e);
        },
        done: function (e) {
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
        url: "/308Vikings/UserAccount/processLogin",
        data: JSON.stringify(loginRequest),
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            if(!data.success){
                $(".error.modal").css("display", "block");
                 $('#error').html(data['error']);
            }
            else{
                window.location.replace("/308Vikings/");
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

function logout() {
    console.log("Calling logout Ajax function...");

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/logout",
        data: "",
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            window.location.replace("/308Vikings/");
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
    document.getElementById("signup").style.display = "none";
    document.getElementById("feedback").style.display = "inline";
    if (data.success) {
        $('#feedback').html("<h4>Signup Successful</h4>");
    } else 
    {
        $('#feedback').html("<h4>Signup Failed. Username or Email taken.</h4>");
    }
}

function display(data) {
    var json = "<h4>Ajax Response</h4><pre>"
            + JSON.stringify(data, null, 4) + "</pre>";
    $('#feedback').html(json);
}

function displayLoginMessage() {
    $(".error.modal").css("display", "block");
}

function getUserInfoFromSession() {
    console.log("Calling User from session Ajax function...");

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/getSessionUser",
        data: {},
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {

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