/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
jQuery(document).ready(function ($) {
});

var app = angular.module('myApp', []);

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

app.controller("getDetailAlbum", function ($scope, $http) {
    console.log("called");
    $scope.getAlbumJson = function (event) {
    console.log(event);
    $http({
      method: 'GET',
      url: '/308Vikings/Album/getAlbumPageDetails',
      headers: {'Content-Type': 'application/json'},
      params: {id: event.target.id}
    }).then(function successCallback(response) {
      $scope.albumdata = response.data;
      console.log("SONG:" , response.data);
    }, function errorCallback(response) {});
  }
});

app.controller("getAllAlbum", function ($scope, $http) {
    $http({
      method: 'GET',
      url: '/308Vikings/Album/getAllAlbums',
    }).then(function successCallback(response) {
      $scope.data = response.data;
      console.log(response.data);
    }, function errorCallback(response) {});
});
