jQuery(document).ready(function ($) {
});

app.controller("getDetailArtist", function ($scope, $http, $routeParams) {
    $http({
      method: 'GET',
      url: '/308Vikings/Artist/getArtistPageDetails',
      headers: {'Content-Type': 'application/json'},
      params: {id: $routeParams.id}
    }).then(function successCallback(response) {
      $scope.artistdata = response.data;
      $('#artistOverview').show();
      $('#loading').css("display", "none");
    }, function errorCallback(response) {});
    
    $("#artistOverview").css("display", "none");
    $("#artistBio").css("display", "none");
    $("#artistConcerts").css("display", "none");
    $('.pages').css("display","none");
    $('#loading').css("display", "block");
    $('div.artisttab li').click(function(){
    var tab_id = $(this).attr('data-tab');
    $('div.artisttab li').removeClass('current');
    $('.artisttab-content').removeClass('current');

    $(this).addClass('current');
    $("#"+tab_id).addClass('current');

    if($(this).attr('data-tab') === 'artisttab-1'){
       $('.pages').css("display","none");
       $('#artistOverview').show();
    }if($(this).attr('data-tab') === 'artisttab-2'){
       $('.pages').css("display","none");
       $('#artistBio').show();
    }if($(this).attr('data-tab') === 'artisttab-3'){
       $('.pages').css("display","none");
       $('#artistConcerts').show();
    }  
   });
});

app.controller("getAllArtist", function ($scope, $http) {
    $http({
      method: 'GET',
      url: '/308Vikings/Artist/getAllArtists',
    }).then(function successCallback(response) {
      $scope.data = response.data;
    }, function errorCallback(response) {});
});

app.filter("convertMilSec", function(){
   return function(input){
       var minutes = Math.floor(input / 60000);
       var seconds = ((input % 60000) / 1000).toFixed(0);
       return minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
   }
 });

 app.filter('objectLength', function(){
     return function(object){
         var count = 0;
        
        for (var i in object){
            count++;
        }
        
        return count;
     }
 });