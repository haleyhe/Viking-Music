app.controller("profileController", function($scope, $http) {
  $scope.resetForm = function() {
    $scope.editUser.id = $scope.user.id;
    $scope.editUser.username = $scope.user.username;
    $scope.editUser.email = $scope.user.email;
    $scope.editUser.dateOfBirth = $scope.user.dateOfBirth;
    $scope.editUser.zip = $scope.user.zip;
  };

  $scope.updateUser = function() {
    $http.post('/308Vikings/UserAccount/updateUserProfile', JSON.stringify($scope.editUser), {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        },
      })
      .then(
        function successCallback(response) {
          if (response.status == 200 && response.data.success) {
            $scope.getUser();
            $scope.resetForm();
          } else {
            //replace to display data module
            alert(response.data.error);
          }
        },
        function errorCallback(response) {});
  };

  $scope.showChangePassword = function() {
    $("#account-overview-container").css("display", "none");
    $("#change-password-container").css("display", "block");
  };

  $scope.showUpdateAccount = function() {
    $("#change-password-container").css("display", "none");
    $("#account-overview-container").css("display", "block");
  };

  $scope.getUser = function() {
    $http.get("/308Vikings//UserAccount/getSessionUser")
      .then(function successCallback(response) {
        $scope.user = response.data;
        dob = new Date(response.data.dateOfBirth);
        $scope.user.dateOfBirth = dob;
        $scope.resetForm();
      }, function errorCallback(response) {});
  };

  $("#username-input").attr("disabled", "disabled");
  $scope.showUpdateAccount();
  $scope.editUser = {};
  $scope.getUser();
});