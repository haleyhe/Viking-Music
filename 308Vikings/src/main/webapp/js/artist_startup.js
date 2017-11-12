 jQuery(document).ready(function ($) {
    $("#artist-signin-form").submit(function (event) {
        event.preventDefault();
        login();
    });
    
    $("#error-message-close").click(function (event) {
        $("#artist-signin-error").css("display", "none");
    });
});

function login() {
    var loginRequest = {};
    loginRequest["username"] = $("#artist-login-id").val();
    loginRequest["password"] = $("#artist-login-password").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/ArtistAccount/processLogin",
        data: JSON.stringify(loginRequest),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            window.location.replace("/308Vikings/artistportal");
        }
        
    }).fail(function(data) {
            
    });
}

function displayErrorMessage(message) {
    document.getElementById("error-message").innerHTML = message;
    $(".error.modal").css("display", "block");
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
            console.log("SUCCESS: ", data);
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
