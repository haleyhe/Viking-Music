
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

app.controller("getDetailAlbum", function ($scope, $routeParams, $location, $http) {
        $('.pages').css("display","none");
        $("#loading").css("display", "block");

        $http({
          method: 'GET',
          url: '/308Vikings/Album/getAlbumPageDetails',
          headers: {'Content-Type': 'application/json'},
          params: {id: $routeParams.id}

        }).then(function successCallback(response) {
            $('#indivAlbumPage').show();
            if (response.data.album != null) {
              $scope.albumdata = response.data;
            } else {
              $location.path('/').replace();
            }
            $("#loading").css("display", "none");
    }, function errorCallback(response) {});

    $scope.reloadAlbum = function() {
      $http({
        method: 'GET',
        url: '/308Vikings/Album/getAlbumPageDetails',
        headers: {'Content-Type': 'application/json'},
        params: {id: $routeParams.id}

      }).then(function successCallback(response) {
        if (response.data.album != null) {
          $scope.albumdata = response.data;
        } else {
          $location.path('/').replace();
        }
      }, function errorCallback(response) {});
    };

    $scope.saveAlbum = function() {
        $('.pages').css("display", "none");
        $("#loading").css("display", "block");
        $http.post('/308Vikings/UserMusic/saveAlbum', {id:$scope.albumdata.album.id}, {
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }
          })
          .then(function successCallback(response) {
            if (response.data.success) {
              $scope.reloadAlbum($scope.albumdata.album.id);
            }
          }, function errorCallback(response) {});
        $('#indivAlbumPage').show();
        $("#loading").css("display", "none");
    };

    $scope.unsaveAlbum = function() {
        $('.pages').css("display", "none");
        $("#loading").css("display", "block");
        $http.post('/308Vikings/UserMusic/unsaveAlbum', {id:$scope.albumdata.album.id}, {
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }
          })
          .then(function successCallback(response) {
            if (response.data.success) {
              $scope.reloadAlbum($scope.albumdata.album.id);
            }
          }, function errorCallback(response) {});
        $('#indivAlbumPage').show();
        $("#loading").css("display", "none");
    };


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
