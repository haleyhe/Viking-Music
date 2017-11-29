app.controller("premiumController", function($scope, $rootScope, $http) {
  $scope.ccCompanies = ['VISA','MASTERCARD','AMEX','DISCOVER'];
  $scope.getUser = function () {
    $http.get("/308Vikings//UserAccount/getSessionUser")
    .then(function successCallback(response) {
        $rootScope.user = response.data;
    }, function errorCallback(response) {
    });
  };
  $scope.getUser();

  $scope.resetForm = function () {
    $scope.payment = {}
    $scope.expiration = {};
    $scope.street1 = null;
    $scope.street2 = null;
  };

  $scope.upgrade = function() {
    $("#loading").css("display", "block");
    if ($scope.street2 != null) {
      $scope.payment.billingAddress.street = $scope.street1 +  " " + $scope.street2;
    } else {
        $scope.payment.billingAddress.street = $scope.street1;
    }
    year = 2000 + parseInt($scope.expiration.year);
    month = parseInt($scope.expiration.month) - 1;
    $scope.payment.expirationDate = new Date (Date.UTC(year, month, 0, 0, 0, 0, 0));
    headers =  {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    };
    $http.post('/308Vikings/UserAccount/upgrade', JSON.stringify($scope.payment), {headers})
      .then(
        function successCallback(response) {
          if (response.status == 200 && response.data.success) {
            $scope.getUser();
            $scope.resetForm();
          } else {
            //replace to display data module
            alert(response.data.error);
          }
        }, function errorCallback(response) {});
    $("#loading").css("display", "none");
  };

  $scope.downgrade = function() {
    $("#loading").css("display", "block");
    headers =  {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    };
    $http.post('/308Vikings/UserAccount/downgrade', {}, {headers})
      .then(
        function successCallback(response) {
          if (response.status == 200 && response.data.success) {
            $scope.getUser();
            $scope.resetForm();
          } else {
            //replace to display data module
            alert(response.data.error);
          }
        }, function errorCallback(response) {});
    $("#loading").css("display", "none");
  };

});
