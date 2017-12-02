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
    $("#edit-user-form-div").css("display", "none");
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
            loadStatistics();
            $('#admin-statistics').show();
        }
        if($(this).attr('data-tab') === 'menutab-2'){
            $('.pages').css("display","none");
            $('#admin-create-user').show();
        }	
        if($(this).attr('data-tab') === 'menutab-3'){
            $('.pages').css("display","none");
            $('#admin-edit-user').show();
        }
        if($(this).attr('data-tab') === 'menutab-4'){
            $('.pages').css("display","none");
            $('#admin-create-artist').show();
        }
        if($(this).attr('data-tab') === 'menutab-5'){
            $('.pages').css("display","none");
            loadArtists();
            $('#admin-edit-artist').show();
        }
        if($(this).attr('data-tab') === 'menutab-6'){
            $('.pages').css("display","none");
            $('#admin-summary').show();
        }
        if($(this).attr('data-tab') === 'menutab-7'){
            $('.pages').css("display","none");
            $('#admin-song-requests').show();
        }
    });
    
    // Create User Page
    $("#create-user-form").submit(function (event) {
        event.preventDefault();
        createUser();
    });
    
    // Edit User Page
    $("#edit-user-prompt").submit(function (event) {
        event.preventDefault();
        getUserForEdit();
    });
    
    $("#edit-user-form").submit(function (event) {
        event.preventDefault();
        submitUserEdit();
    });
    
    $("#delete-user-form").submit(function (event) {
        event.preventDefault();
        deleteUser();
    });
    
    // Edit Artist Page
    $("#admin-edit-artist-artist-list").on("click", ".edit-artist-bubble", function() {
        $("#admin-edit-artist-artist-list").css("display","none");
        loadArtistEditForm($(this).attr("data-artist-id"));
    });
    
    $("#artist-edit-form").submit(function (event) {
        event.preventDefault();
        submitArtistEdit();
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

function showLoading() {
    $("#loading").css("display", "block");
}

function hideLoading() {
    $("#loading").css("display", "none");
    $("#admin-error").css("display", "none");
}

function logout() {
    showLoading();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/logout",
        async: true,
        timeout: 100000
    }).done(function(data) {
        if (!data.success) {
            hideLoading();
            displayErrorMessage(data.error);
        } else {
            window.location.replace("/308Vikings/");
        }     
    });
}

// Create User Page
function createUser() {
    var newUser = {};
    newUser["username"] = $("#create-user-username").val();
    newUser["password"] = $("#create-user-password").val();
    newUser["email"] = $("#create-user-email").val();
    newUser["dateOfBirth"] = $("#create-user-dob").val();
    newUser["zip"] = $("#create-user-zipcode").val();
    newUser["premium"] = ($("#create-user-premium").is(":checked"));
    newUser["admin"] = ($("#create-user-admin").is(":checked"));
    
    showLoading();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/UserAccount/registerUser",
        data: JSON.stringify(newUser),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        hideLoading();
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            displaySuccessMessage("Account created.");
        }
    });
}

// Edit User Page
function getUserForEdit() {
    var username = $("#edit-user-prompt-username").val();
    showLoading();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/Admin/getUserByUsername",
        data: {username: username},
        async: true,
        timeout: 100000
    }).done(function(data) {
        hideLoading();
        if (data) {
            displayEditUserForm(data);
        } else {
            displayErrorMessage("User not found.");
        }
    });
}

function displayEditUserForm(user) {
    document.getElementById("edit-user-form-header").innerHTML = "Edit User " + user.id;
    $("#edit-user-prompt").css("display", "none");
    $("#edit-user-form").attr("userId", user.id);
    $("#edit-user-username").attr("value", user.username);
    $("#edit-user-email").attr("value", user.email);
    var dob = new Date(user.dateOfBirth);
    document.getElementById("edit-user-dob").valueAsDate = dob;
    $("#edit-user-zipcode").attr("value", user.zip);
    document.getElementById("edit-user-premium").checked = user.premium;
    document.getElementById("edit-user-admin").checked = user.admin;
    $("#delete-user-form").attr("userId", user.id);
    $("#edit-user-form-div").show();
}

function submitUserEdit() {
    var updatedUser = {};
    updatedUser["id"] = $("#edit-user-form").attr("userId");
    updatedUser["username"] = $("#edit-user-username").val();
    updatedUser["password"] = $("#edit-user-password").val();
    updatedUser["email"] = $("#edit-user-email").val();
    updatedUser["dateOfBirth"] = $("#edit-user-dob").val();
    updatedUser["zip"] = $("#edit-user-zipcode").val();
    updatedUser["premium"] = ($("#edit-user-premium").is(":checked"));
    updatedUser["admin"] = ($("#edit-user-admin").is(":checked"));
    
    showLoading();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/Admin/updateUser",
        data: JSON.stringify(updatedUser),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        hideLoading();
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            displaySuccessMessage("Account updated.");
        }
    });
}

function deleteUser() {
    var idRequest = {};
    idRequest['id'] = $("#delete-user-form").attr("userId");
    showLoading();
    $.ajax({
        type: "POST",
        url: "/308Vikings/Admin/deleteUser",
        contentType: "application/json",
        data: JSON.stringify(idRequest),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        hideLoading();
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            displaySuccessMessage("User deleted.");
        }
    });
}

// Edit Artist Page
function loadArtists() {
    showLoading();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/Artist/getAllArtists",
        async: true,
        timeout: 100000
    }).done(function(data) {
        hideLoading();
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
    showLoading();
    $.ajax({
        type: "GET",
        url: "/308Vikings/Artist/getArtist",
        data: {id: artistId},
        async: true,
        timeout: 100000
    }).done(function(data) {
        hideLoading();
        displayArtistEditForm(data);
    });
}

function displayArtistEditForm(artist) {
    $("#artist-edit-form").attr("artistId", artist.id);
    $("#artist-edit-thumbnail-preview").attr("src","/308Vikings/css/artist/" + artist.id + ".jpg");
    $("#artist-edit-name").attr("value", artist.name);
    $("#artist-edit-bio").val(artist.bio);
    var nameList = "<ul>";
    $.each(artist.relatedNames, function() {
        nameList += "<li>" + this.firstName + " " + this.lastName + "</li>";
    });
    nameList += "</ul>";
    document.getElementById("artist-related-names").innerHTML = nameList;
    var genreList = "<ul>";
    $.each(artist.genres, function() {
        genreList += "<li>" + this + "</li>";
    });
    genreList += "</ul>";
    document.getElementById("artist-genres").innerHTML = genreList;
    $('#artist-edit-form').show();
}

function submitArtistEdit() {
    if ($("#artist-edit-name").val().length === 0) {
        displayErrorMessage("Name cannot be blank.");
        return;
    }
    if ($("#artist-edit-first-name").val().length === 0) {
        if ($("#artist-edit-last-name").val().length > 0) {
            displayErrorMessage("First name required.");
            return;
        }
    }
    if ($("#artist-edit-last-name").val().length === 0) {
        if ($("#artist-edit-first-name").val().length > 0) {
            displayErrorMessage("Last name required.");
            return;
        }
    }
    
    var formData = new FormData();
    formData.append("id", $("#artist-edit-form").attr("artistId"));
    formData.append("name", $("#artist-edit-name").val());
    formData.append("bio", $("#artist-edit-bio").val());
    formData.append("firstName", $("#artist-edit-first-name").val());
    formData.append("lastName", $("#artist-edit-last-name").val());
    formData.append("genre", $("#artist-edit-genre").val());
    var thumbnailFile = null;
    var filesSelected = document.getElementById("artist-edit-thumbnail").files;
    if (filesSelected.length > 0) {
        formData.append("thumbnail", filesSelected[0]);
    }
    showLoading();
    $.ajax({
        type: "POST",
        url: "/308Vikings/Admin/updateArtist",
        contentType: false,
        processData: false,
        data: formData,
        async: true,
        timeout: 100000
    }).done(function(data) {
        hideLoading();
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            displaySuccessMessage("Changes successfully saved.");
        }
    });
    
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
    var dateFormatted = date.getFullYear() + '/' + (date.getUTCMonth() + 1) + "/" + date.getUTCDate();
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
    var dateFormatted = date.getFullYear() + '/' + (date.getUTCMonth() + 1) + "/" + date.getUTCDate();
    result += "<td>" + dateFormatted + "</td>";
    result += "<td>" + "$" + parseFloat(revenue.amountPaid).toFixed(2) + "</td>";
    result += "</tr>";
    return result;
}

function loadStatistics() {
    showLoading();
    $.ajax({
        type: "GET",
        url: "/308Vikings/Admin/getStatistics",
        async: true,
        timeout: 100000
    }).done(function(data) {
        hideLoading();
        displayStatistics(data);
    });
}

function displayStatistics(data) {
    var result = "";
    result += "<h1>" + data.numSongs + "</h1> Total Songs <br />";
    result += "<h1>" + data.numAlbums + "</h1> Total Albums <br />";
    result += "<h1>" + data.numArtists + "</h1> Total Artists <br />";
    result += "<h1>" + data.numUsers + "</h1> Users <br />";
    result += "<h1>" + data.numPremiumUsers + "</h1> Premium Users <br />";
    result += "<h1>" + data.numSongPlays + "</h1> Total Song Plays <br />";
    result += "<h1>$" + parseFloat(data.totalPayments).toFixed(2) + "</h1> In Payments Made <br />";
    result += "<h1>$" + parseFloat(data.totalRevenue).toFixed(2) + "</h1> In Revenue Received <br />";
    $("#admin-statistics-body").html(result);
}