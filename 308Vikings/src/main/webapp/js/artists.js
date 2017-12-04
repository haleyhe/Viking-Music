jQuery(document).ready(function ($) {
});

app.controller("getDetailArtist", function ($scope, $http, $location, $routeParams) {
    $http({
      method: 'GET',
      url: '/308Vikings/Artist/getArtistPageDetails',
      headers: {'Content-Type': 'application/json'},
      params: {id: $routeParams.id}
    }).then(function successCallback(response) {
      if (response.data.id != null) {
        $scope.artistdata = response.data;
      } else {
        $location.path('/').replace();
      }
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

    if($(this).attr('data-tab') === 'artisttab-1'){
       $('.pages').css("display","none");
       $('#artistOverview').show();
    }if($(this).attr('data-tab') === 'artisttab-2'){
       $('.pages').css("display","none");
       $('#artistBio').show();
    }if($(this).attr('data-tab') === 'artisttab-3'){
       $('.pages').css("display","none");
       $('#artistConcerts').show();
    }if($(this).attr('data-tab') === 'artisttab-4'){
       $('.pages').css("display","none");
       $('#relatedArtists').show();
    }
   });

    $scope.reloadArtist = function() {
      $http({
        method: 'GET',
        url: '/308Vikings/Artist/getArtistPageDetails',
        headers: {'Content-Type': 'application/json'},
        params: {id: $routeParams.id}

      }).then(function successCallback(response) {
        if (response.data.id != null) {
          $scope.artistdata = response.data;
        } else {
          $location.path('/').replace();
        }
      }, function errorCallback(response) {});
    };

    $scope.followArtist = function() {
        $('.pages').css("display", "none");
        $("#loading").css("display", "block");
        $http.post('/308Vikings/UserMusic/followArtist', {id:$scope.artistdata.id}, {
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }
          })
          .then(function successCallback(response) {
            if (response.data.success) {
              $scope.reloadArtist($scope.artistdata.id);
            }
          }, function errorCallback(response) {});
        $('.pages').css("display","none");
        $('#artistOverview').show();
        $("#loading").css("display", "none");
    };

    $scope.unfollowArtist = function() {
        $('.pages').css("display", "none");
        $("#loading").css("display", "block");
        $http.post('/308Vikings/UserMusic/unfollowArtist', {id:$scope.artistdata.id}, {
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }
          })
          .then(function successCallback(response) {
            if (response.data.success) {
              $scope.reloadArtist($scope.artistdata.id);
            }
          }, function errorCallback(response) {});
        $('.pages').css("display","none");
        $('#artistOverview').show();
        $("#loading").css("display", "none");
    };

});

app.controller("getAllArtist", function ($scope, $http) {
    $http({
      method: 'GET',
      url: '/308Vikings/Artist/getAllArtists',
    }).then(function successCallback(response) {
      $scope.data = response.data;
    }, function errorCallback(response) {});
    $scope.pageName  = "All Artist";
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

 app.filter('lineBreaks', function(){
     return function(input){
         var line = input;
         if(!(typeof line === "undefined") && line.includes("\n") )
             return input.replace(/\n/g, "<br />");
         return input;
     }
 });

 app.filter("sanitize", ['$sce', function($sce) {
        return function(htmlCode){
            return $sce.trustAsHtml(htmlCode);
        }
}]);
