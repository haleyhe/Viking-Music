app.controller("playlistController", function($scope, $http) {
  $scope.getAllPlaylists = function() {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Playlist/getAllPlaylists',
    }).then(function successCallback(response) {
      $scope.data = response.data;
    }, function errorCallback(response) {});
    $('#playlistPage').show();
    $("#loading").css("display", "none");
  };

  $scope.newDate = new Date().getTime();
  $scope.getAllPlaylists();
  $scope.pageName = "All Playlists";
});

app.controller("createPlaylistController", function($scope, $location, $http) {
      $scope.newPlaylist = {};

      $scope.showCreatePlaylistForm = function() {
        $('.create.modal').css("display", "block");
        $('.pages').css("display", "block");
      };

      $scope.closeCreatePlaylistForm = function() {
        $(".create.modal").css("display", "none");
        $('.pages').css("display", "block");
        $scope.newPlaylist = {};
        $("#create-playlist-thumbnail").val('');
        $('#create-thumbnail-preview').attr('src', '');
      };


      $scope.createPlaylist = function() {
        //$("#loading").css("display", "block");
        var createData = new FormData();
        createData.append('name', $scope.newPlaylist.name);
        createData.append('description', $scope.newPlaylist.description);
        createData.append('publiclyVisible', true);
        if ($scope.newPlaylist.thumbnail != null && $scope.newPlaylist.thumbnail.size > 0) {
          createData.append('thumbnail', $scope.newPlaylist.thumbnail);
        }
        $http.post('/308Vikings/Playlist/createPlaylist', createData, {
            transformRequest: angular.identity,
            headers: {
              'Content-Type': undefined
            }
          })
          .then(
            function successCallback(response) {
              if (response.status == 200 && response.data.success) {
                  $("#loading").css("display", "none");
                  $scope.closeCreatePlaylistForm();
                  newPath = '/playlist/' + response.data.error;
                   $location.path(newPath).replace();
              }
            }, function errorCallback(response) {});
      };
});

app.controller("indivPlaylistController", function($scope, $routeParams, $rootScope, $location, $http) {
  $scope.getPlaylistJson = function() {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http({
      method: 'GET',
      url: '/308Vikings/Playlist/getPlaylistPageDetails',
      headers: {
        'Content-Type': 'application/json'
      },
      params: {
        id: $routeParams.id
      }
    }).then(function successCallback(response) {
       if (response.data.playlist != null) {
         $scope.playlistdata = response.data;
       } else {
         $location.path('/').replace();
       }
    }, function errorCallback(response) {});
    $('#indivPlaylistPage').show();
    $("#loading").css("display", "none");
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
      if (response.data.playlist != null) {
        $scope.playlistdata = response.data;
      } else {
        $location.path('/').replace();
      }
    }, function errorCallback(response) {});
  };

  $scope.showEditPlaylistForm = function() {
    $scope.resetEditForm();
    $('.edit.modal').css("display", "block");
  };

  $scope.closeEditPlaylistForm = function() {
    $(".edit.modal").css("display", "none");
  };

  $scope.showDeletePlaylistForm = function() {
    $('.delete.modal').css("display", "block");
  };

  $scope.closeDeletePlaylistForm = function() {
    $(".delete.modal").css("display", "none");
  };

  $scope.deletePlaylist = function() {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http({
      method: 'POST',
      url: '/308Vikings/Playlist/deletePlaylist',
      data: {
        id: $scope.playlistdata.playlist.id
      }
    }).then(function successCallback(response) {
      $scope.closeDeletePlaylistForm();
      if (response.data.success) {
        $location.path('/').replace();
      } else {
        //replace to display data module
        alert(response.data.error);
      }
    }, function errorCallback(response) {});
    $('#indivPlaylistPage').show();
    $("#loading").css("display", "none");
  }

  $scope.updatePlaylist = function() {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
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
        function successCallback(response) {
          if (response.status == 200 && response.data.success) {
            $scope.reloadPlaylist($scope.editPlaylist.id);
            $scope.resetEditForm();
            $scope.closeEditPlaylistForm();
          } else {
            //replace to display data module
            alert(response.data.error);
          }
        }, function errorCallback(response) {});

      $('#indivPlaylistPage').show();
      $("#loading").css("display", "none");
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
    }, function errorCallback(response) {});
    $('#indivPlaylistPage').show();
    $("#loading").css("display", "none");
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
    }, function errorCallback(response) {});
    $('#indivPlaylistPage').show();
    $("#loading").css("display", "none");
  };

  $scope.resetEditForm = function() {
    $scope.editPlaylist.id = $scope.playlistdata.playlist.id;
    $scope.editPlaylist.name = $scope.playlistdata.playlist.name;
    $scope.editPlaylist.description = $scope.playlistdata.playlist.description;
    $("#edit-playlist-thumbnail").val('');
  };

    $scope.newDate = new Date().getTime();
    $scope.editPlaylist = {};
    $scope.getPlaylistJson();
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
