app.controller("browseController", function($scope, $http) {
  $scope.getAllGenres = function() {
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Browse/getGenrePlaylists',
    }).then(function successCallback(response) {
      $scope.pageName = "Genres & Moods";
      $scope.genres = response.data;
    }, function errorCallback(response) {});
    $("#loading").css("display", "none");
  };

  $scope.getRecentReleases = function() {
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Album/getRecentAlbums',
    }).then(function successCallback(response) {
      $scope.pageName = "Newly Released";
      $scope.recent = response.data;
    }, function errorCallback(response) {});
    $("#loading").css("display", "none");
  };

  $scope.getCharts = function() {
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Browse/getTopSongs',
    }).then(function successCallback(response) {
      $scope.pageName = "Top Charts";
      $scope.charts = response.data;
    }, function errorCallback(response) {});
    $("#loading").css("display", "none");
  };

  $scope.discoverMusic = function() {
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Browse/getRecommendations',
      params: {numSets: 6}
    }).then(function successCallback(response) {
      $scope.pageName = "Discover Music";
      $scope.discover = response.data;
    }, function errorCallback(response) {});
    $("#loading").css("display", "none");
  };

  $scope.discoverMusic();
  $scope.newDate = new Date().getTime();
});
