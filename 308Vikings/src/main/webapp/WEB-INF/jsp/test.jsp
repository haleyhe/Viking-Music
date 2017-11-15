<!doctype html>
<html>
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=0.5, user-scalable=no">
      <link href='http://fonts.googleapis.com/css?family=Julius Sans One:400;300' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css?family=Raleway:300,400" rel="stylesheet" type='text/css'>
          <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
          <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>

          <body>
            <div ng-app="myApp" ng-controller="myCtrl">
              <a href="#" id="0U6Z6EVDwVMqwmr2zEcH4L" ng-click='getAlbumJson($event)'> Click here </a> <br>

              <h2> Song List </h2>
              <table border="1px">
                <tr ng-repeat="song in data.album.songs">
                  <td>{{ song.name }}</td>
                  <td ng-repeat="artist in song.artists">{{artist.name}} </td>
                  <td>{{ song.album.name }}</td>
                  <td>{{ song.duration }}</td>
                </tr>
              </table>

              <h2> JSON Dump</h2>
              {{ data }}
            </div>

            <script>
              var app = angular.module("myApp", []);
              app.controller("myCtrl", function ($scope, $http) {
                $scope.getAlbumJson = function (event) {
                  console.log(event);
                  $http({
                    method: 'GET',
                    url: '/308Vikings/Album/getAlbumPageDetails',
                    headers: {'Content-Type': 'application/json'},
                    params: {id: event.target.id}
                  }).then(function successCallback(response) {
                    $scope.data = response.data;
                  }, function errorCallback(response) {});
                }
              });

            </script>
          </body>

        </html>
