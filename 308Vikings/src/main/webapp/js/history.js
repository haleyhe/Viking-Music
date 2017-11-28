app.controller("historyController", function($scope, $http) {
  $scope.gethistory = function () {
    $('.pages').css("display", "none");
    $("#loading").css("display", "block");
    $http.get('/308Vikings/UserMusic/history')
      .then(
        function(response) {
          if (response.status == 200) {
            $scope.history = response.data;
            console.log($scope.history);
          } else {
            alert(response.statusText);
          }
            $('#historyPage').show();
            $("#loading").css("display", "none");
        }
      );
  };
  $scope.gethistory();
});
