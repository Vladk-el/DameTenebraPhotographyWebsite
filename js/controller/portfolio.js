(function () {
    'use strict';
    
    angular.module('portfolioService', [])
    
    .controller('PortfolioController', function ($scope, $http, $location, $log) {
        //$log.info('PortfolioController');

        $scope.categories = [];

        $http.get('php/services/category/categories.php?display=true')
            .success(function (data, status, headers, config) {
                $scope.categories = data;
                //$log.debug(data);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });

        $scope.go = function (path) {
            $location.path(path);
        }
        
    });
    
})(angular);