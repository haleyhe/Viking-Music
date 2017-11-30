$(document).ready(function() {

  $('ul.musictabs li').click(function() {
    var tab_id = $(this).attr('data-tab');

    $('ul.musictabs li').removeClass('current');
    $('.musictab-content').removeClass('current');

    $(this).addClass('current');
    $("#" + tab_id).addClass('current');
  });

  $('ul.tabs li').click(function() {
    var tab_id = $(this).attr('data-tab');

    $('ul.tabs li').removeClass('current');
    $('.tab-content').removeClass('current');

    $(this).addClass('current');
    $("#" + tab_id).addClass('current');
  });

  $('div.menutabs li').click(function() {
    var tab_id = $(this).attr('data-tab');
    $('div.menutabs li').removeClass('current');
    $('.menutab-content').removeClass('current');

    $(this).addClass('current');
    $("#" + tab_id).addClass('current');

    if ($(this).attr('data-tab') === 'menutab-1') {
      $('.pages').css("display", "none");
      $('#historyPage').show();
    }
    if ($(this).attr('data-tab') === 'menutab-2') {
      $('.pages').css("display", "none");
      $('#librarySongsPage').show();
    }
    if ($(this).attr('data-tab') === 'menutab-3') {
      $('.pages').css("display", "none");
      $('#albumpage').show();
    }
    if ($(this).attr('data-tab') === 'menutab-4') {
      $('.pages').css("display", "none");
      $('#artistpage').show();
    }
    if ($(this).attr('data-tab') === 'menutab-5') {
      $('.pages').css("display", "none");
      $('#playlistPage').show();
    }
  });

  $(".close").click(function() {
    $(".modal").css("display", "none");
  });

  $('.newPlaylist').click(function() {
    $('.pages').css("display", "none");
    $('#playListPage').show();
  });

  $('.artistitems').click(function() {
    $('.pages').css("display", "none");
    $('#libindivArtistPage').show();
  });

  $('.concerttable').click(function() {
    $('.pages').css("display", "none");
    $('#indivConcertPage').show();
  });

  $('.user-dropdown-menu').click(
    function() {
      $(this).removeClass('open');
      $('.user-dropdown-menu').css("display", "none");
    });

  $('.registerbtn').click(function() {
    $('.signup.modal').css("display", "block");
  });

  $('#to-admin-portal-form').click(function() {
    window.location.replace("/308Vikings/adminportal");
  });

});

app.controller("mainAppController", function ($scope, $http) {
});
app.filter("convertMilSec", function(){
   return function(input){
       var minutes = Math.floor(input / 60000);
       var seconds = ((input % 60000) / 1000).toFixed(0);
       return minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
   }
 });
