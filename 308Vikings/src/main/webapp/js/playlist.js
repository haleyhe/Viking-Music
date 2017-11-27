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

  $scope.reloadPlaylist = function(playlistId) {
    $scope.newDate = new Date().getTime();
    $http({
      method: 'GET',
      url: '/308Vikings/Playlist/getPlaylistPageDetails',
      headers: {
        'Content-Type': 'application/json'
      },
      params: {
        id: playlistId
      }
    }).then(function successCallback(response) {
      $scope.playlistdata = response.data;
    }, function errorCallback(response) {});
  };

  $scope.showEditPlaylistForm = function() {
    $scope.editPlaylist.id = $scope.playlistdata.playlist.id;
    $scope.editPlaylist.name = $scope.playlistdata.playlist.name;
    $scope.editPlaylist.description = $scope.playlistdata.playlist.description;
    $('.signup.modal').css("display", "block");
  };

  $scope.closeEditPlaylistForm = function() {
    $(".modal").css("display", "none");
  };

  $scope.updatePlaylist = function() {
    // $('.pages').css("display", "none");
    // $("#loading").css("display", "block");

    var editData = new FormData();

    editData.append("id", $scope.editPlaylist.id);
    editData.append('name', $scope.editPlaylist.name);
    editData.append('description', $scope.editPlaylist.description);
    if ($scope.editPlaylist.thumbnail != null && $scope.editPlaylist.thumbnail.size > 0) {
      editData.append('thumbnail', $scope.editPlaylist.thumbnail);
    }

    $http.post('/308Vikings/Playlist/updatePlaylist', editData, {
        transformRequest: angular.identity,
        headers: {
          'Content-Type': undefined
        }
      })
      .then(
        function(response) {
          if (response.data.success) {
            $scope.reloadPlaylist($scope.editPlaylist.id);
            $scope.closeEditPlaylistForm();
          } else {
            //replace to display data module
            alert(response.data.error);
          }
          // $('#indivPlaylistPage').show();
          // $("#loading").css("display", "none");
        }
      );
  };

  $scope.followPlaylist = function() {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http({
      method: 'POST',
      url: '/308Vikings/UserMusic/followPlaylist',
      data: {
        id: $scope.playlistdata.playlist.id
      }
    }).then(function successCallback(response) {
      if (response.data.success) {
        $scope.reloadPlaylist($scope.playlistdata.playlist.id);
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
      data: {
        id: $scope.playlistdata.playlist.id
      }
    }).then(function successCallback(response) {
      if (response.data.success) {
        $scope.reloadPlaylist($scope.playlistdata.playlist.id);
      } else {
        //replace to display data module
        alert(response.data.error);
      }
      $('#indivPlaylistPage').show();
      $("#loading").css("display", "none");
    }, function errorCallback(response) {});
  };

  $scope.newDate = new Date().getTime();
  $scope.editPlaylist = {};
  $scope.getAllPlaylists();
});

app.directive('fileModel', ['$parse', function($parse) {
  return {
    restrict: 'A',
    link: function(scope, element, attrs) {
      var model = $parse(attrs.fileModel);
      var modelSetter = model.assign;

      element.bind('change', function() {
        scope.$apply(function() {
          modelSetter(scope, element[0].files[0]);
        });
      });
    }
  };
}]);