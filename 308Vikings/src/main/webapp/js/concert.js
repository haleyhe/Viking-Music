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

app.controller("getDetailConcert", function ($scope, $http, $routeParams) {
    $('.pages').css("display","none");
    $("#loading").css("display", "block");

    $http({
      method: 'GET',
      url: '/308Vikings/Concert/getConcertPageDetails',
      headers: {'Content-Type': 'application/json'},
      params: {id: $routeParams.id}
    }).then(function successCallback(response) {
        $scope.concert = response.data;
    }, function errorCallback(response) {});

    $("#loading").css("display", "none");
    $('#indivConcertPage').show();
});

