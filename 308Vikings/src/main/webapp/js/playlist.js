//jQuery(document).ready(function($) {});

app.controller("playlistController", function($scope, $http) {
  $scope.getAllPlaylists = function () {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Playlist/getAllPlaylists',
    }).then(function successCallback(response) {
      $scope.data = response.data;
      $('#playlistPage').show();
      $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  };

  $scope.getPlaylistJson = function(event) {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Playlist/getPlaylistPageDetails',
      headers: {
        'Content-Type': 'application/json'
      },
      params: {
        id: event.target.id
      }
    }).then(function successCallback(response) {
      $scope.playlistdata = response.data;
      $('#indivPlaylistPage').show();
      $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  };

  $scope.getAllPlaylists();
});
