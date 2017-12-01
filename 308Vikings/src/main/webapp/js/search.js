app.controller("searchController", function($scope, $routeParams, $http) {
  $scope.search = function() {
    $scope.overview = true;
    $scope.query = $routeParams.q;
    $('.pages').css("display","none");
    $('.see-all-button').css("display","block");
    $("#loading").css("display", "block");
    $http.get('/308Vikings/Browse/search', {params: {'query' : $routeParams.q, 'limit' : 5}})
    .then(
      function successCallback(response) {
          $scope.searchSongs = response.data.songs;
          $scope.searchAlbums = response.data.albums;
          $scope.searchArtists = response.data.artists;
          $scope.searchPlaylists = response.data.playlists;
          $('#albumResultsContainer').show();
          $('#artistResultsContainer').show();
          $('#playlistResultsContainer').show();
          $('#songResultsContainer').show();
          $('#searchResultPage').show();
      }, function errorCallback(response) {});
      $("#loading").css("display", "none");
  };


  $scope.searchAllSongs = function() {
    $scope.overview = false;
    $("#loading").css("display", "block");
    $('.see-all-button').css("display","none");
    $('#albumResultsContainer').hide();
    $('#artistResultsContainer').hide();
    $('#playlistResultsContainer').hide();
    $http.get('/308Vikings/Browse/search/songs', {params: {'query' : $routeParams.q, 'limit' : 50}})
    .then(
      function successCallback(response) {
        if (response.status == 200) {
          $scope.searchSongs = response.data.songs;
          $('#songResultsContainer').show();
        }
      }, function errorCallback(response) {});

      $("#loading").css("display", "none");
  };

  $scope.searchAllAlbums = function() {
    $scope.overview = false;
    $("#loading").css("display", "block");
    $('.see-all-button').css("display","none");
    $('#songResultsContainer').hide();
    $('#artistResultsContainer').hide();
    $('#playlistResultsContainer').hide();
    $http.get('/308Vikings/Browse/search/albums', {params: {'query' : $routeParams.q, 'limit' : 50}})
    .then(
      function successCallback(response) {
        if (response.status == 200) {
          $scope.searchAlbums = response.data.albums;
          $('#albumResultsContainer').show();
        }
      }, function errorCallback(response) {});

      $("#loading").css("display", "none");
  };

  $scope.searchAllArtists = function() {
    $scope.overview = false;
    $("#loading").css("display", "block");
    $('.see-all-button').css("display","none");
    $('#songResultsContainer').hide();
    $('#albumResultsContainer').hide();
    $('#playlistResultsContainer').hide();
    $http.get('/308Vikings/Browse/search/artists', {params: {'query' : $routeParams.q, 'limit' : 50}})
    .then(
      function successCallback(response) {
        if (response.status == 200) {
          $scope.searchArtists = response.data.artists;
          $('#artistResultsContainer').show();
        }
      }, function errorCallback(response) {});
      $("#loading").css("display", "none");
  };

  $scope.searchAllPlaylists = function() {
    $scope.overview = false;
    $("#loading").css("display", "block");
    $('.see-all-button').css("display","none");
    $('#songResultsContainer').hide();
    $('#albumResultsContainer').hide();
    $('#artistResultsContainer').hide();
    $http.get('/308Vikings/Browse/search/playlists', {params: {'query' : $routeParams.q, 'limit' : 50}})
    .then(
      function successCallback(response) {
        if (response.status == 200) {
          $scope.searchPlaylists = response.data.playlists;
          $('#playlistResultsContainer').show();
        }
      }, function errorCallback(response) {});
      $("#loading").css("display", "none");
  };

  $scope.search();
});

app.controller("mainController", function($scope, $location, $http) {
    $scope.search = function() {
      $location.path('/search').search('q', $scope.query).replace();
    }
});
