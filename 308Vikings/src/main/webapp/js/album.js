
app.controller('getSession', function($scope, $http) {
    $http({
        method : "GET",
        url : "/308Vikings//UserAccount/getSessionUser"
    }).then(function mySuccess(response) {
        $scope.name = response.data;
    }, function myError(response) {
        $scope.name = response.statusText;
    });
});

app.controller("getDetailAlbum", function ($scope, $routeParams, $http) {
        $('.pages').css("display","none");
        $("#loading").css("display", "block");
        
        $http({
          method: 'GET',
          url: '/308Vikings/Album/getAlbumPageDetails',
          headers: {'Content-Type': 'application/json'},
          params: {id: $routeParams.id}

        }).then(function successCallback(response) {
            $('#indivAlbumPage').show();
            $scope.albumdata = response.data;
            $("#loading").css("display", "none");  
    }, function errorCallback(response) {});
});

app.controller("getAllAlbum", function ($scope, $http) {
  $('.pages').css("display","none");
  $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Album/getAllAlbums',
    }).then(function successCallback(response) {
      $('#albumpage').show();
      $("#loading").css("display", "none");
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