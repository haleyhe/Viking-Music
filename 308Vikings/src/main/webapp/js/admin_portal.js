 jQuery(document).ready(function ($) {
    $('.pages').css("display","none");
    $('#admin-overview').show();

    $('.dropdownbtn').click(function() {
        if ($(this).hasClass('open')) {
            $('.user-dropdown-menu').css("display", "none");
            $(this).removeClass('open');
        } else {
            $('.user-dropdown-menu').css("display", "block");
            $(this).addClass('open');
        }
    });

    $('.user-dropdown-menu').click(function() {
        $(this).removeClass('open');
        $('.user-dropdown-menu').css("display", "none");
    });
    
    // hide result forms
    $('#artist-edit-form').css("display", "none");
    $('#admin-monthly-summary-result').css("display", "none");
    $('#admin-monthly-summary-no-result').css("display", "none");
    
    $("#error-message-close").click(function (event) {
        $("#admin-error").css("display", "none");
    });
    
    $("#admin-return-to-site-form").submit(function (event) {
        event.preventDefault();
        window.location.replace("/308Vikings/");
    });
    
    $("#admin-signout-form").submit(function (event) {
        event.preventDefault();
        logout();
    });
    
    $("#success-message-close").click(function (event) {
        window.location.replace("/308Vikings/adminportal");
    });
    
    $('div.menutabs li').click(function() {
        var tab_id = $(this).attr('data-tab');
        $('div.menutabs li').removeClass('current');
        $('.menutab-content').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');

        if($(this).attr('data-tab') === 'menutab-1'){
            $('.pages').css("display","none");
            $('#admin-overview').show();
        }
        if($(this).attr('data-tab') === 'menutab-2'){
            $('.pages').css("display","none");
            $('#admin-create-user').show();
        }	
        if($(this).attr('data-tab') === 'menutab-3'){
            $('.pages').css("display","none");
            $('#admin-delete-user').show();
        }
        if($(this).attr('data-tab') === 'menutab-4'){
            $('.pages').css("display","none");
            $('#admin-edit-user').show();
        }
        if($(this).attr('data-tab') === 'menutab-5'){
            $('.pages').css("display","none");
            $('#admin-create-artist').show();
        }
        if($(this).attr('data-tab') === 'menutab-6'){
            $('.pages').css("display","none");
            $('#admin-delete-artist').show();
        }
        if($(this).attr('data-tab') === 'menutab-7'){
            $('.pages').css("display","none");
            loadArtists();
            $('#admin-edit-artist').show();
        }
        if($(this).attr('data-tab') === 'menutab-8'){
            $('.pages').css("display","none");
            $('#admin-summary').show();
        }
        if($(this).attr('data-tab') === 'menutab-9'){
            $('.pages').css("display","none");
            $('#admin-song-requests').show();
        }
    });
    
    // Create User Page
    $("#create-user-form").submit(function (event) {
        event.preventDefault();
        createUser();
    });
    
    // Edit Artist Page
    $("#admin-edit-artist-artist-list").on("click", ".edit-artist-bubble", function() {
        $("#admin-edit-artist-artist-list").css("display","none");
        loadArtistEditForm($(this).attr("data-artist-id"));
    });
    
    // Monthly Summary Page
    $("#admin-summary-month-picker").submit(function (event) {
        event.preventDefault();
        getMonthlySummary();
    });
});

function displayErrorMessage(message) {
    document.getElementById("error-message").innerHTML = message;
    $(".error.modal").css("display", "block");
}

function displaySuccessMessage(message) {
    document.getElementById("success-message").innerHTML = message;
    $(".success.modal").css("display", "block");
}

function logout() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/logout",
        async: true,
        timeout: 100000
    }).done(function(data) {
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            window.location.replace("/308Vikings/");
        }     
    });
}

// Create User Page
function createUser() {
    var newUser = {};
    newUser["id"] = null;
    newUser["username"] = $("#create-user-username").val();
    newUser["password"] = $("#create-user-password").val();
    newUser["email"] = $("#create-user-email").val();
    newUser["dateOfBirth"] = $("#create-user-dob").val();
    newUser["zip"] = $("#create-user-zipcode").val();
    newUser["premium"] = ($("#create-user-premium").is(":checked"));
    newUser["admin"] = ($("#create-user-admin").is(":checked"));
    newUser["facebookId"] = null;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/registerUser",
        data: JSON.stringify(newUser),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            displaySuccessMessage("Account created.");
        }
    });
}

// Edit Artist Page
function loadArtists() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/Artist/getAllArtists",
        async: true,
        timeout: 100000
    }).done(function(data) {
        displayArtists(data.artists);
    });
}

function displayArtists(artists) {
    artistListHtml = "";
    $.each(artists, function() {
        artistListHtml += createArtistItem(this);
    });
    
    document.getElementById("admin-edit-artist-artist-list").innerHTML = artistListHtml;   
}

function createArtistItem(artist) {
    result = "<div class='edit-artist-bubble' data-artist-id='" + artist.id + "'>";
    result += "<img class=artistimg src=/308Vikings/css/artist/" + artist.id + ".jpg></img>";
    result += "<li class=artistname>" + artist.name + "</li>";
    result += "</div>";
    return result;
}

function loadArtistEditForm(artistId) {
    $.ajax({
        type: "GET",
        url: "/308Vikings/Artist/getArtist",
        data: {id: artistId},
        async: true,
        timeout: 100000
    }).done(function(data) {
        displayArtistEditForm(data);
    });
}

function displayArtistEditForm(artist) {
    console.log(artist);
    $("#artist-edit-thumbnail-preview").attr("src","/308Vikings/css/artist/" + artist.id + ".jpg");
    $("#artist-edit-name").attr("value", artist.name);
    $("#artist-edit-bio").val(artist.bio);
    var nameList = "";
    $.each(artist.relatedNames, function() {
        nameList += "<li>" + this.firstName + " " + this.lastName + "</li>";
    });
    $("#artist-related-names").innerHTML = nameList;
    var genreList = "";
    $.each(artist.genres, function() {
        genreList += "<li>" + this + "</li>";
    });  
    $("#artist-genres").innerHTML = genreList;
    $('#artist-edit-form').show();
}

// Monthly Summary Page
function getMonthlySummary() {
    date = $("#admin-summary-month").val();
    if (!date) {
        displayErrorMessage("Please enter a date.");
        return;
    }
    date = date + '-01';
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/Payment/getAdminMonthlySummary",
        data: JSON.stringify(date),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        displayMonthlySummaryResults(date, data);
    });
}

function displayMonthlySummaryResults(date, data) {
    $("#admin-monthly-summary-result").css("display","none");
    $("#admin-monthly-summary-result-table").css("display","none");
    $("#admin-monthly-summary-result-revenue-table").css("display","none");
    $("#admin-monthly-summary-no-result").css("display","none");
    if (Object.keys(data.payments).length === 0 && Object.keys(data.revenue).length === 0) {
        message = "No results for " + date.split("-")[1] + "/" + date.split("-")[0];
        document.getElementById("admin-monthly-summary-no-result-title").innerHTML = message;
        $("#admin-monthly-summary-no-result").show();
    } else {
        message = "Results for " + date.split("-")[1] + "/" + date.split("-")[0];
        document.getElementById("admin-monthly-summary-result-title").innerHTML = message;
        if (Object.keys(data.payments).length > 0) {
            var tableRows = "";
            $.each(data.payments, function() {
                tableRows += createMonthlySummaryResultsTableRow(this);
            });
            $("#admin-monthly-summary-result-table tbody").html(tableRows);
            $("#admin-monthly-summary-result-table").show();  
        }
        if (Object.keys(data.revenue).length > 0) {
            var tableRows = "";
            $.each(data.revenue, function() {
                tableRows += createMonthlySummaryRevenueResultsTableRow(this);
            });
            $("#admin-monthly-summary-result-revenue-table tbody").html(tableRows);
            $("#admin-monthly-summary-result-revenue-table").show();  
        }
        $("#admin-monthly-summary-result").show();
        
    }
}

function createMonthlySummaryResultsTableRow(payment) {
    var result= "<tr>";
    result += "<td>" + payment.id + "</td>";
    result += "<td>" + payment.name + "</td>";
    result += "<td>" + payment.numPlays + "</td>";
    var date = new Date(payment.datePaid);
    var dateFormatted = date.getFullYear() + '/' + (date.getMonth() + 1) + "/" + date.getDay();
    result += "<td>" + dateFormatted + "</td>";
    result += "<td>" + "$" + parseFloat(payment.amountPaid).toFixed(2) + "</td>";
    result += "</tr>";
    return result;
}

function createMonthlySummaryRevenueResultsTableRow(revenue) {
    var result= "<tr>";
    result += "<td>" + revenue.user.id + "</td>";
    result += "<td>" + revenue.user.name + "</td>";
    result += "<td>" + revenue.payment.cardNumber + "</td>";
    var date = new Date(revenue.datePaid);
    var dateFormatted = date.getFullYear() + '/' + (date.getMonth() + 1) + "/" + date.getDay();
    result += "<td>" + dateFormatted + "</td>";
    result += "<td>" + "$" + parseFloat(revenue.amountPaid).toFixed(2) + "</td>";
    result += "</tr>";
    return result;
}