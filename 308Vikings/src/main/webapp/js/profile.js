app.controller("profileController", function($scope, $location, $http) {
  $scope.resetForm = function() {
    $scope.editUser.id = $scope.user.id;
    $scope.editUser.username = $scope.user.username;
    $scope.editUser.email = $scope.user.email;
    $scope.editUser.dateOfBirth = $scope.user.dateOfBirth;
    $scope.editUser.zip = $scope.user.zip;
  };

  $scope.updateUser = function() {
        $("#loading").css("display", "block");
    $http.post('/308Vikings/UserAccount/updateUserProfile', JSON.stringify($scope.editUser), {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        },
      })
      .then(
        function successCallback(response) {
          if (response.status == 200) {
            $("#message-modal").css("display", "block");
            if(!response.data.success){
                $('#message').html(response.data.error);
            }
            else{
                $scope.getUser();
                $('#message').html("You have successfully updated your profile.");
            }
            $scope.resetForm();
          }
              $("#loading").css("display", "none");
        },
        function errorCallback(response) {});
  };

  $scope.changePassword = function() {

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

  $scope.showDeleteConfirmation = function() {
    $('.delete.modal').css("display", "block");
  };

  $scope.closeDeleteConfirmation = function() {
    $(".delete.modal").css("display", "none");
  };

  $scope.deleteAccount = function() {
    $http.post('/308Vikings/UserAccount/deleteAccount',{})
      .then(
        function successCallback(response) {
          if (response.status == 200) {
            window.location.replace("/308Vikings/");
          }
        },
        function errorCallback(response) {});
  };

  $("#username-input").attr("disabled", "disabled");
  $scope.showUpdateAccount();
  $scope.editUser = {};
  $scope.getUser();
});
