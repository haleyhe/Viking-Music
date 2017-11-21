jQuery(document).ready(function ($) {
});
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

app.controller("getDetailAlbum", function ($scope, $http) {
    $scope.getAlbumJson = function (event) {
        $('.pages').css("display","none");
        $("#loading").css("display", "block");

    $http({
      method: 'GET',
      url: '/308Vikings/Album/getAlbumPageDetails',
      headers: {'Content-Type': 'application/json'},
      params: {id: event.target.id}
    }).then(function successCallback(response) {
      $scope.albumdata = response.data;
      $('#indivAlbumPage').show();
      $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  }
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
