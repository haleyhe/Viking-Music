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
    newUser["username"] = $("#signup_username").val();
    newUser["password"] = $("#signup_password").val();
    newUser["email"] = $("#email").val();
    newUser["dateOfBirth"] = $("#dob").val();
    newUser["zip"] = $("#zipcode").val();
    newUser["premium"] = false;
    newUser["admin"] = false;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/registerUser",
        data: JSON.stringify(newUser),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        $(".error.modal").css("display", "block");
        if(!data.sucess){
            $('#message').html(data['error']);
        }
        else{
            $('#message').html("You have successfully signed up.");    
        }   
    });
}

function login() {
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
        timeout: 100000
    }).done(function(data) {
        if(!data.success) {
            $(".error.modal").css("display", "block");
             $('#message').html(data['error']);
        } else {
            window.location.replace("/308Vikings/");
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
        timeout: 100000
    }).done(function(data) {
        window.location.replace("/308Vikings/");
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
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/getSessionUser",
        async: true,
        timeout: 100000
    }).done(function(data) {
        window.location.replace("/308Vikings/");
    });
}