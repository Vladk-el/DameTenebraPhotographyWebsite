(function(angular) {
    'use strict';
 
    angular.module('ngApp', ['ngRoute'])

    .controller('MainController', function($scope, $route, $routeParams, $location) {
        $scope.$route = $route;
        $scope.$location = $location;
        $scope.$routeParams = $routeParams
    })

    .config(function($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'includes/index.html',
                controller: 'MainController'
            });
    
        $locationProvider.html5Mode(true);
    });
    
    // see https://docs.angularjs.org/api/ngRoute/service/$route#example

})(window.angular);
