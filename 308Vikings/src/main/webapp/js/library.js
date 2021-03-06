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
  $scope.pageName = "Library Songs";
});

app.controller("libraryPlaylistsController", function($scope, $http) {
  $scope.getLibraryPlaylists = function () {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http.get('/308Vikings/UserMusic/library/playlists')
      .then(
        function successCallback(response) {
          if (response.status == 200) {
            $scope.libraryPlaylists = response.data;
          } else {
            alert(response.statusText);
          }
          $('#libraryPlaylistsPage').show();
          $("#loading").css("display", "none");
        }, function errorCallback(response) {});
  };
  $scope.getLibraryPlaylists();
  $scope.pageName = "Library Playlists";
});

app.controller("libraryAlbumsController", function($scope, $http) {
  $scope.getLibraryAlbums = function () {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http.get('/308Vikings/UserMusic/library/albums')
      .then(
        function successCallback(response) {
          if (response.status == 200) {
            $scope.libraryAlbums = response.data;
          } else {
            alert(response.statusText);
          }
          $('#libraryAlbumsPage').show();
          $("#loading").css("display", "none");
        }, function errorCallback(response) {});
  };
  $scope.getLibraryAlbums();
  $scope.pageName = "Library Albums";
});

app.controller("libraryArtistsController", function($scope, $http) {
  $scope.getLibraryArtists = function () {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http.get('/308Vikings/UserMusic/library/artists')
      .then(
        function successCallback(response) {
          if (response.status == 200) {
            $scope.libraryArtists= response.data;
          } else {
            alert(response.statusText);
          }
          $('#libraryArtistsPage').show();
          $("#loading").css("display", "none");
        }, function errorCallback(response) {});
  };
  $scope.getLibraryArtists();
  $scope.pageName = "Library Artists";
});
