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

  $scope.newDate = new Date().getTime();
  
  $scope.getConcertRecommendations = function(){
    $("#loading").css("display", "block");

    $http({
      method: 'GET',
      url: "/308Vikings/Browse/getRecommendedConcerts",
      headers: {'Content-Type': 'application/json'},
    }).then(function successCallback(response) {
        $scope.concerts = response.data;
    }, function errorCallback(response) {}); 
    
    $("#loading").css("display", "none");
   };
});
