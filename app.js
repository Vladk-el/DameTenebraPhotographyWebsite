(function (angular) {
    'use strict';
 
    angular.module('ngApp', ['ngRoute'])

        .controller('MainController', function ($scope, $route, $routeParams, $location) {
            $scope.$route = $route;
            $scope.$location = $location;
            $scope.$routeParams = $routeParams;
        })
    
        .controller('IndexController', function ($scope) {
            console.log('IndexController');
        })
    
        .controller('PortfolioController', function ($scope) {
            console.log('PortfolioController');
        })

        .config(function ($routeProvider, $locationProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'includes/index.html',
                    controller: 'IndexController'
                })
                .when('/portfolio', {
                    templateUrl: 'includes/portfolio.html',
                    controller: 'PortfolioController'
                });

            $locationProvider.html5Mode(true);
        });
    
    // see https://docs.angularjs.org/api/ngRoute/service/$route#example

})(window.angular);
