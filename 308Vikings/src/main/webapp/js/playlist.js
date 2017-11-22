function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function(e) {
      $('#edit-thumbnail-preview').attr('src', e.target.result);
    };

    reader.readAsDataURL(input.files[0]);
  }
}

$("#edit-playlist-thumbnail").change(function() {
  console.log("changed");
  readURL(this);
});

app.controller("playlistController", function($scope, $http) {
  $scope.getAllPlaylists = function() {
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

  $scope.showEditPlaylistForm = function() {
    $scope.editPlaylist.name = $scope.playlistdata.playlist.name;
    $scope.editPlaylist.description = $scope.playlistdata.playlist.description;
    $('.signup.modal').css("display", "block");
  };

  $scope.closeEditPlaylistForm = function() {
    $(".modal").css("display", "none");
  };

  $scope.updatePlaylist = function() {

  };

  $scope.followPlaylist = function() {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http({
      method: 'POST',
      url: '/308Vikings/UserMusic/followPlaylist',
      data: {id: $scope.playlistdata.playlist.id}
    }).then(function successCallback(response) {
      if (response.data.success) {
        $scope.playlistdata.following = true;
        $scope.playlistdata.playlist.numFollowers += 1;
      } else {
        //replace to display data module
        alert(response.data.error);
      }
      $('#indivPlaylistPage').show();
      $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  };

  $scope.unfollowPlaylist = function() {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http({
      method: 'POST',
      url: '/308Vikings/UserMusic/unfollowPlaylist',
      data: {id: $scope.playlistdata.playlist.id}
    }).then(function successCallback(response) {
      if (response.data.success) {
        $scope.playlistdata.following = false;
        $scope.playlistdata.playlist.numFollowers -= 1;
      } else {
        //replace to display data module
        alert(response.data.error);
      }
      $('#indivPlaylistPage').show();
      $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  };

  $scope.editPlaylist = {};
  $scope.getAllPlaylists();
});
