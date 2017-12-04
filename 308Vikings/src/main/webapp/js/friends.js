app.controller('getSession', function($scope, $http) {
    $http({
        method : "GET",
        url : "/308Vikings//UserAccount/getSessionUser"
    }).then(function mySuccess(response) {
        $scope.name = response.data;
    }, function myError(response) {
        $scope.name = response.statusText;
    });
});

app.controller("friendsController", function($scope, $http) {
    $('.pages').css("display","none");
    $("#loading").css("display", "block");

    $http({
        method: 'GET',
        url: '/308Vikings/UserMusic/library/friends',
        headers: {'Content-Type': 'application/json'}

    }).then(function successCallback(response) {
        $('#friendspage').show();
        $scope.friends = response.data;
        $("#loading").css("display", "none");
    }, function errorCallback(response) {});
    
    
     $scope.reloadFriends = function() {
      $http({
        method: 'GET',
        url: '/308Vikings/UserMusic/library/friends',
        headers: {'Content-Type': 'application/json'},

      }).then(function successCallback(response) {
          $scope.friends = response.data;
      }, function errorCallback(response) {});
    };
    
    $scope.addFriend = function() {
        $("#loading").css("display", "none");
        var username =  document.getElementsByName("friendName")[0].value;
        $http.post('/308Vikings/UserMusic/addFriend', {id:username}, {
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }
          })
          .then(function successCallback(response) {
            if (response.data.success) {
              $scope.reloadFriends($scope.friends.id);
            } else{
                $("#message-modal").css("display", "block");
                $('#message').html(response.data.error);
                console.log(message);
            }
          }, function errorCallback(response) {});
        $('#friendspage').show();

        document.getElementsByName("friendName")[0].value = "";
    };
    
    $scope.deleteFriend = function(friendId) {
        $("#loading").css("display", "none");
        $http.post('/308Vikings/UserMusic/removeFriend', {id: friendId}, {
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }
          })
          .then(function successCallback(response) {
            if (response.data.success) {
              $scope.reloadFriends($scope.friends.id);
            }
          }, function errorCallback(response) {});
        $('#friendspage').show();
        
    };
});