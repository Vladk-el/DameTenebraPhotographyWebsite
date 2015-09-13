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
    
        .controller('PortfolioController', function ($scope, $http) {
            console.log('PortfolioController');
        
            $scope.categories = [];
        
            $http.get('php/services/categories.php')
            .success(function (data, status, headers, config) {
                $scope.categories = data;
                console.log(data);
            })
            .error(function (data, status, headers, config) {
                console.log(status);
                console.log("Error occured");
            });
            
            // @Deprecated ==> make request from server, not client
            $scope.getLastPhotoForCategory = function (category_id) {
                $http({ url : 'php/services/last_photo_by_category.php?category=' + category_id})
                .then(function (response)  {
                    return response['photo_link'];
                });
            }
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
    
})(window.angular);
