 jQuery(document).ready(function ($) {
    $("#signup-form").submit(function (event) {
        event.preventDefault();
        signup();
    });
    $("#signin-form").submit(function (event) {
        event.preventDefault();
        login();
    });
    
    $("#signout-form").click(function (event) {
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
        async: true
    }).done(function(data) {
        $("#startup-error").css("display", "block");
        if(!data.success){
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
    
    showLoading();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/processLogin",
        data: JSON.stringify(loginRequest),
        dataType: 'json',
        async: true
    }).done(function(data) {
        hideLoading();
        if(!data.success) {
            $("#startup-error").css("display", "block");
            $('#message').html(data['error']);
        } else {
            
            window.location.replace("/308Vikings/");
        } 
    });

}

function logout() {
    $.ajax({
        type: "GET",
        url: "/308Vikings/UserAccount/logout",
        async: true
    }).done(function(data) {
        window.location.replace("/308Vikings/");
    });
}
function displayLoginMessage() {
    $(".error.modal").css("display", "block");
}

function showLoading() {
    $("#loading").css("display", "block");
}

function hideLoading() {
    $("#loading").css("display", "none");
    $("#startup-error").css("display", "none");
}
