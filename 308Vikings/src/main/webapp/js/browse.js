app.controller("browseController", function($scope, $http) {
  $scope.getAllGenres = function() {
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Browse/getGenrePlaylists',
    }).then(function successCallback(response) {
      $scope.pageName = "Genres & Moods";
      $scope.genres = response.data;
          $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  };

  $scope.getRecentReleases = function() {
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Album/getRecentAlbums',
    }).then(function successCallback(response) {
      $scope.pageName = "Newly Released";
      $scope.recent = response.data;
          $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  };

  $scope.getCharts = function() {
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Browse/getTopSongs',
    }).then(function successCallback(response) {
      $scope.pageName = "Top Charts";
      $scope.charts = response.data;
          $("#loading").css("display", "none");
    }, function errorCallback(response) {});
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
          $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  };

  $scope.discoverMusic();
  $scope.newDate = new Date().getTime();

  $scope.getConcertRecommendations = function(){
    $("#loading").css("display", "block");

    $http({
      method: 'GET',
      url: "/308Vikings/Browse/getRecommendedConcerts",
      headers: {'Content-Type': 'application/json'},
    }).then(function successCallback(response) {
        $scope.concerts = response.data;
            $("#loading").css("display", "none");
    }, function errorCallback(response) {});

   };
});
