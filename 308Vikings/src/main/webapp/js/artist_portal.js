 jQuery(document).ready(function ($) {
    $("#artist-signout-form").submit(function (event) {
        event.preventDefault();
        logout();
    });
    
    $("#error-message-close").click(function (event) {
        $("#artist-signin-error").css("display", "none");
    });
});

function displayErrorMessage(message) {
    document.getElementById("error-message").innerHTML = message;
    $(".error.modal").css("display", "block");
}

function logout() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/ArtistAccount/logout",
        async: true,
        timeout: 100000
    }).done(function(data) {
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            window.location.replace("/308Vikings/artistportal");
        }     
    });
}
