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
                $("#loading").css("display", "none");
      }, function errorCallback(response) {});
  };


  $scope.searchAllSongs = function() {
    $scope.overview = false;
    $("#loading").css("display", "block");
    $('.see-all-button').css("display","none");
    $('#songResultsContainer').hide();
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
              $("#loading").css("display", "none");
      }, function errorCallback(response) {});

  };

  $scope.searchAllAlbums = function() {
    $scope.overview = false;
    $("#loading").css("display", "block");
    $('.see-all-button').css("display","none");
    $('#songResultsContainer').hide();
    $('#albumResultsContainer').hide();
    $('#artistResultsContainer').hide();
    $('#playlistResultsContainer').hide();
    $http.get('/308Vikings/Browse/search/albums', {params: {'query' : $routeParams.q, 'limit' : 50}})
    .then(
      function successCallback(response) {
        if (response.status == 200) {
          $scope.searchAlbums = response.data.albums;
          $('#albumResultsContainer').show();
        }
          $("#loading").css("display", "none");
      }, function errorCallback(response) {});


  };

  $scope.searchAllArtists = function() {
    $scope.overview = false;
    $("#loading").css("display", "block");
    $('.see-all-button').css("display","none");
    $('#songResultsContainer').hide();
    $('#albumResultsContainer').hide();
    $('#artistResultsContainer').hide();
    $('#playlistResultsContainer').hide();
    $http.get('/308Vikings/Browse/search/artists', {params: {'query' : $routeParams.q, 'limit' : 50}})
    .then(
      function successCallback(response) {
        if (response.status == 200) {
          $scope.searchArtists = response.data.artists;
          $('#artistResultsContainer').show();
        }
              $("#loading").css("display", "none");
      }, function errorCallback(response) {});
  };

  $scope.searchAllPlaylists = function() {
    $scope.overview = false;
    $("#loading").css("display", "block");
    $('.see-all-button').css("display","none");
    $('#songResultsContainer').hide();
    $('#albumResultsContainer').hide();
    $('#artistResultsContainer').hide();
    $('#playlistResultsContainer').hide();
    $http.get('/308Vikings/Browse/search/playlists', {params: {'query' : $routeParams.q, 'limit' : 50}})
    .then(
      function successCallback(response) {
        if (response.status == 200) {
          $scope.searchPlaylists = response.data.playlists;
          $('#playlistResultsContainer').show();
                $("#loading").css("display", "none");
        }
      }, function errorCallback(response) {});
  };

  $scope.search();
});

app.controller("mainController", function($scope, $location, $http) {
    $scope.search = function() {
      $location.path('/search').search('q', $scope.query).replace();
    }
});
