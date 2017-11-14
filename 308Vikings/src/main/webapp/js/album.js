/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
jQuery(document).ready(function ($) {
    getAllAlbum();
    getSessionUser();
});

function getAllAlbum() {
    console.log("Calling login Ajax function...");
    
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings/Album/getAllAlbums",
        data: "",
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            console.log(data['albums'][0]['id']);
            getAlbum(data['albums'][0]['id']);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}


function getAlbum(id) {
    console.log("Calling login Ajax function...");
    
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings//Album/" + id,
        data: "",
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

function getSessionUser() {
    console.log("Calling login Ajax function...");
    
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/308Vikings//UserAccount/getSessionUser",
        data: "",
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

//var app = angular.module('myApp', []);
//app.controller('getSession', function($scope, $http) {
//    $http({
//        method : "GET",
//        url : "/308Vikings//UserAccount/getSessionUser"
//    }).then(function mySuccess(response) {
//        $scope.name = response.data;
//    }, function myError(response) {
//        $scope.name = response.statusText;
//    });
//});