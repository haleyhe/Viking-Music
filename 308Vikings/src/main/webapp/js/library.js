app.controller("historyController", function($scope, $http) {
  $scope.getHistory = function () {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http.get('/308Vikings/UserMusic/history')
      .then(
        function  successCallback(response) {
          if (response.status == 200) {
            $scope.history = response.data;
          } else {
            alert(response.statusText);
          }
            $('#historyPage').show();
            $("#loading").css("display", "none");
        }, function errorCallback(response) {});
  };
  $scope.getHistory();
});

app.controller("librarySongsController", function($scope, $http) {
  $scope.getLibrarySongs = function () {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http.get('/308Vikings/UserMusic/library/songs')
      .then(
        function successCallback(response) {
          if (response.status == 200) {
            $scope.librarySongs = response.data;
          } else {
            alert(response.statusText);
          }
            $('#librarySongsPage').show();
            $("#loading").css("display", "none");
        }, function errorCallback(response) {});
  };
  $scope.getLibrarySongs();
});
