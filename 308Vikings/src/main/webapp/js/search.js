app.controller("searchController", function($scope, $routeParams, $http) {
  $scope.searchAll = function() {
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
      }, function errorCallback(response) {});
      $('#searchResultPage').show();
      $('#albumResultsContainer').show();
      $('#artistResultsContainer').show();
      $('#playlistResultsContainer').show();
      $('#songResultsContainer').show();
      $("#loading").css("display", "none");
  };


  $scope.searchSongs = function() {
    console.log("in search songs");
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
        } else {
          //replace to display data module
          alert(response.data.error);
        }
      }, function errorCallback(response) {});
      $('#songResultsContainer').show();
      $("#loading").css("display", "none");
  };

  $scope.searchAlbums = function() {
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
        } else {
          //replace to display data module
          alert(response.data.error);
        }
      }, function errorCallback(response) {});
      $('#albumResultsContainer').show();
      $("#loading").css("display", "none");
  };

  $scope.searchArtists = function() {
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
        } else {
          //replace to display data module
          alert(response.data.error);
        }
      }, function errorCallback(response) {});
      $('#artistResultsContainer').show();
      $("#loading").css("display", "none");
  };

  $scope.searchPlaylists = function() {
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
        } else {
          //replace to display data module
          alert(response.data.error);
        }
      }, function errorCallback(response) {});
      $('#playlistResultsContainer').show();
      $("#loading").css("display", "none");
  };

  $scope.searchAll();
});

app.controller("mainController", function($scope, $location, $http) {
    $scope.search = function() {
      $location.path('/search').search('q', $scope.query).replace();
    }
});
