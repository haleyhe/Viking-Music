 jQuery(document).ready(function ($) {
    $('.pages').css("display","none");
    $('#artist-overview').show();
    
    $("#artist-signout-form").submit(function (event) {
        event.preventDefault();
        logout();
    });
    
    $("#error-message-close").click(function (event) {
        $("#artist-signin-error").css("display", "none");
    });
    
    $('div.menutabs li').click(function() {
        var tab_id = $(this).attr('data-tab');
        $('div.menutabs li').removeClass('current');
        $('.menutab-content').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
        
        console.log("GOT");
        console.log($(this).attr('data-tab'));

        if($(this).attr('data-tab') == 'menutab-1'){
            $('.pages').css("display","none");
            $('#artist-overview').show();
        }
        if($(this).attr('data-tab') == 'menutab-2'){
            $('.pages').css("display","none");
            $('#artist-edit-info').show();
        }	
        if($(this).attr('data-tab') == 'menutab-3'){
            $('.pages').css("display","none");
            $('#artist-change-image').show();
        }	
        if($(this).attr('data-tab') == 'menutab-4'){
            $('.pages').css("display","none");
            $('#artist-summary').show();
        }
        if($(this).attr('data-tab') == 'menutab-5'){
            $('.pages').css("display","none");
            $('#artist-request-song').show();
        }
        if($(this).attr('data-tab') == 'menutab-6'){
            $('.pages').css("display","none");
            $('#artist-remove-song').show();
        }
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
