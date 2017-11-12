 jQuery(document).ready(function ($) {
    $('.pages').css("display","none");
    $('#artist-overview').show();
    
    // hide result forms
    $('#artist-monthly-summary-result').css("display", "none");
    $('#artist-monthly-summary-no-result').css("display", "none");
    
    $("#error-message-close").click(function (event) {
        $("#artist-error").css("display", "none");
    });
    
    $("#artist-signout-form").submit(function (event) {
        event.preventDefault();
        logout();
    });
    
    $("#artist-edit-form").submit(function (event) {
        event.preventDefault();
        submitNewArtistInfo();
    });
    
    $("#error-message-close").click(function (event) {
        $("#artist-signin-error").css("display", "none");
    });
    
    $("#success-message-close").click(function (event) {
        window.location.replace("/308Vikings/artistportal");
    });
    
    $('div.menutabs li').click(function() {
        var tab_id = $(this).attr('data-tab');
        $('div.menutabs li').removeClass('current');
        $('.menutab-content').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');

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
    
    // Monthly Summary Page
    $("#artist-summary-month-picker").submit(function (event) {
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

// Edit Artist Info Page
function submitNewArtistInfo() {
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
    var artist = {};
    artist["name"] = $("#artist-edit-name").val();
    artist["bio"] = $("#artist-edit-bio").val();
    var newName = {};
    newName["firstName"] = $("#artist-edit-first-name").val();
    newName["lastName"] = $("#artist-edit-last-name").val();
    artist["relatedName"] = newName;
    artist["genre"] = $("#artist-edit-genre").val();
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/ArtistAccount/updateArtist",
        data: JSON.stringify(artist),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        if (!data.success) {
            displayErrorMessage(data.error);
        } else {
            displaySuccessMessage("Changes successfully saved.");
        }
    });
    
}

// Monthly Summary Page
function getMonthlySummary() {
    date = $("#artist-summary-month").val();
    if (!date) {
        displayErrorMessage("Please enter a date.");
        return;
    }
    date = date + '-01';
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/308Vikings/Payment/getArtistMonthlySummary",
        data: JSON.stringify(date),
        dataType: 'json',
        async: true,
        timeout: 100000
    }).done(function(data) {
        displayMonthlySummaryResults(date, data);
    });
}

function displayMonthlySummaryResults(date, data) {
    $("#artist-monthly-summary-result").css("display","none");
    $("#artist-monthly-summary-no-result").css("display","none");
    if (Object.keys(data.payments).length === 0) {
        message = "No results for " + date.split("-")[1] + "/" + date.split("-")[0];
        document.getElementById("artist-monthly-summary-no-result-title").innerHTML = message;
        $("#artist-monthly-summary-no-result").show();
    } else {
        message = "Results for " + date.split("-")[1] + "/" + date.split("-")[0];
        document.getElementById("artist-monthly-summary-result-title").innerHTML = message;
        var header = "<tr><td>Song ID</td> <td>Name</td> <td>Monthly Plays</td> <td>Payment Date</td> <td>Amount Paid</td> </tr>";
        var tableRows = "";
        $.each(data.payments, function() {
            tableRows += createMonthlySummaryResultsTableRow(this);
        });
        console.log($("#artist-monthly-summary-result-table"));
        $("#artist-monthly-summary-result-table tbody").html(tableRows);
        $("#artist-monthly-summary-result-table").show();
        $("#artist-monthly-summary-result").show();
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
